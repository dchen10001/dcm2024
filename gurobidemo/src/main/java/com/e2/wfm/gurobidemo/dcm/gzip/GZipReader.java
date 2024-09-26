package com.e2.wfm.gurobidemo.dcm.gzip;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class GZipReader {

    public static void main(String[] args) {
        try {
            read();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void read() throws Exception {
        String sourcePath = "C:\\data\\tjx\\simulatorInput.gzip";
        //String sourcePath = "C:\\development\\dcm\\sources\\dcm2024\\gurobidemo\\src\\main\\resources\\simulatorInput.json";
        
        File file = new File(sourcePath);
        FileInputStream fis = new FileInputStream(file);
        InputStream input = null;
        if(isGZipped(fis)) {
            input = new GZIPInputStream(fis);
            System.out.println("GZipped file");
        } else {
            input = fis;
            System.out.println("Not GZipped file");
        }
        
        
        ObjectMapper mapper = createObjectMap();
        
        JsonNode jsonNode = mapper.readTree(input);
        
//        ValidationSimulatorInput validationSimulatorInput = 
//                mapper.readerFor(ValidationSimulatorInput.class).readValue(input);
        
        fis.close();
        input.close();
    }
    
    public static boolean isGZipped(InputStream in) {
        if (!in.markSupported()) {
            in = new BufferedInputStream(in);
        }
        in.mark(2);
        int magic = 0;
        try {
            magic = in.read() & 0xff | ((in.read() << 8) & 0xff00);
            in.reset();
        } catch (IOException e) {
            e.printStackTrace(System.err);
            return false;
        }
        return magic == GZIPInputStream.GZIP_MAGIC;
    }
    
    public static boolean isGZipped(File f) {
        int magic = 0;
        try {
         RandomAccessFile raf = new RandomAccessFile(f, "r");
         magic = raf.read() & 0xff | ((raf.read() << 8) & 0xff00);
         raf.close();
        } catch (Throwable e) {
         e.printStackTrace(System.err);
        }
        return magic == GZIPInputStream.GZIP_MAGIC;
       }
    
    public static void write() throws Exception {
     // Existing file path
        String file = "C:\\development\\dcm\\sources\\dcm2024\\gurobidemo\\src\\main\\resources\\simulatorInput.json";
       
        // Path where we want the compression of the file
        String gzipFile = "C:\\data\\tjx\\simulatorInput.gzip";
 
        // Reading the text file
        FileInputStream fis = new FileInputStream(file);
       
        // Creating the compressed file
        FileOutputStream fos
            = new FileOutputStream(gzipFile);
       
        // Object of Fileoutstream passed
        GZIPOutputStream gzipOS = new GZIPOutputStream(fos);
 
        byte[] buffer = new byte[1024];
 
        int len;
 
        // Writing the data to file until -1 reached(End of
        // file)
        while ((len = fis.read(buffer)) != -1) {
 
            gzipOS.write(buffer, 0, len);
        }
 
        // Closing the resources
        // using standard close() method
        gzipOS.close();
        fos.close();
        fis.close();
 
        // Display message on the console in order to
        // illustrate successful execution of the program
        System.out.println("File successfully compressed");
    }
    
    public static ObjectMapper createObjectMap() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.registerModule(new Jdk8Module());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        return objectMapper;
    }
}
