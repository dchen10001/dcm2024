package com.nice.saas.dcm.yaml;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class YamlParser {
    public static final String MODEL_PATH_WITH_DISTRIBUTION_RULES = "simulation/modelWithDistributionRules.yaml";

    public static void main(String[] args) {
	YamlParser yamlParser = new YamlParser();
	List<Map<String, Object>> input = yamlParser.buildInput(MODEL_PATH_WITH_DISTRIBUTION_RULES);
	System.out.println(input);
    }
    
    public List<Map<String, Object>> buildInput() {
	return buildInput(MODEL_PATH_WITH_DISTRIBUTION_RULES);
    }
    
    public List<Map<String, Object>> buildInput(String resource) {
	InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(resource);
	return buildInput(resourceAsStream);
    }
    
    public List<Map<String, Object>> buildInput(InputStream resourceAsStream) {
        LoaderOptions options = new LoaderOptions();
        options.setAllowDuplicateKeys(true);
        Yaml yaml = new Yaml(options);
        try {
            List<Map<String, Object>> result = new ArrayList<>();
            List<?> list = yaml.loadAs(resourceAsStream, List.class);
            processList(result, list);
            return cleanup(result);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
    
    private void processList(List<Map<String, Object>> result, List<?> list) {
        for (Object obj : list) {
            if (obj instanceof Map map) {
                processMap(result, map);
            } else {
                System.out.println("Going through list and got type: " + obj.getClass().getSimpleName() + " What should I do now!!!!");
            }
        }
    }
    
    private void processMap(List<Map<String, Object>> result, Map<String, Object> map) {
	Set<String> keys = map.keySet();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            if (key.equalsIgnoreCase("include")) {
        	System.out.println("Doesn't support inlcude yet");
            } else {
                result.add(Map.of(key, entry.getValue()));
            }
        }
    } 

    private List<Map<String, Object>> cleanup(List<Map<String, Object>> result) {
        Map<String, List<Map<String, Object>>>temp = new HashMap<>();
        for (Map<String, Object> list : result) {
            for (Map.Entry<String,Object> entry: list.entrySet()) {
                List<Map<String, Object>> data = temp.computeIfAbsent(entry.getKey(), key -> new ArrayList<>());
                removeDuplicates(entry.getKey(), data, entry.getValue());
                data.add(Map.of(entry.getKey(), entry.getValue()));
            }
        }

        return temp.values().stream()
                .flatMap(Collection::stream)
                .toList();
    }
    
    private void removeDuplicates(String key, List<Map<String, Object>> data, Object value) {
        if ("entity".equalsIgnoreCase(key)) {
            removeDuplicate(data, (Map<String, Object>) value, "type");
        } else if ("Start Event".equalsIgnoreCase(key)) {
            data.clear(); //can only have one start event
        } else {
            removeDuplicate(data, (Map<String, Object>) value, "name");
        }
    }  
    
    private void removeDuplicate(List<Map<String, Object>> data, Map<String, Object> value, String nameField) {
        String newNameObject = getNewNameObject(value, nameField);

        if (newNameObject != null) {
            List<Object> toDelete = new ArrayList<>();
            for(Map<String, Object> dataMap : data) {
                recordDuplicatesInDataMaps(nameField, newNameObject, toDelete, dataMap);

            }
            if (!toDelete.isEmpty()) {
                toDelete.forEach(data::remove);
            }
        }
    }   
    
    private static String getNewNameObject(Map<String, Object> value, String nameField) {
        String newNameObject = null;
        for (Map.Entry<String, Object> entry : value.entrySet()) {
            if (nameField.equalsIgnoreCase(entry.getKey())) {
                newNameObject = (String)entry.getValue();
                break;
            }
        }
        return newNameObject;
    }    
    
    private static void recordDuplicatesInDataMaps(String nameField, String newNameObject, List<Object> toDelete, Map<String, Object> dataMap) {
        Collection<Object> collection = dataMap.values();
        for (Object datum : collection) {
            for (String targetKey : ((Map<String, Object>)datum).keySet()) {
                if (recordedDuplicates(nameField, newNameObject, toDelete, dataMap, (Map<String, Object>) datum, targetKey))
                    break;
            }
        }
    }

    private static boolean recordedDuplicates(String nameField, String newNameObject, List<Object> toDelete, Map<String, Object> dataMap, Map<String, Object> datum, String targetKey) {
        if (nameField.equalsIgnoreCase(targetKey)) {
            String thisName = (String) datum.get(targetKey);
            if (newNameObject.equalsIgnoreCase(thisName)) {
                toDelete.add(dataMap);
            }
            return true;
        }
        return false;
    }    
}
