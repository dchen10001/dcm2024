package com.nice.dcm.sample.generation;

import java.util.Map;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Factory class to create TimeSeriesGenerator instances.
 * 
 */
public final class TimeSeriesGeneratorFactory {
	private static final Logger logger = LoggerFactory.getLogger(TimeSeriesGeneratorFactory.class);
	
	private static TimeSeriesGeneratorFactory instance = new TimeSeriesGeneratorFactory();

	private final Map<GeneratorType, TimeSeriesGenerator> services;
	
	protected TimeSeriesGeneratorFactory() {
		ServiceLoader<TimeSeriesGenerator> serviceLoader = ServiceLoader.load(TimeSeriesGenerator.class);
		services = serviceLoader.stream().collect(Collectors.toMap(k -> k.get().getType(), v -> v.get(),
				(a, b) -> {
					logger.warn("Duplicate generator type found: {} and {}", a.getClass().getSimpleName(), b.getClass().getSimpleName());
					if (a.getClass().getSimpleName().contains(a.getType().getName())) {
						return a;
					} else {
						return b;
					}
				}
		));
	}
	
	public static TimeSeriesGeneratorFactory getInstance() {
		return instance;
	}
	
	/**
	 * Returns the TimeSeriesGenerator instance for the specified type.
	 * 
	 * @param type the type of the generator
	 * @return the generator instance
	 */
	public TimeSeriesGenerator getGenerator(GeneratorType type) {
		if (type == null) {
			return null;
		}
		return services.get(type);
	}
	
	
	public TimeSeriesGenerator getGenerator(String generatorType) {
		return this.getGenerator(GeneratorType.fromName(generatorType));
	}
	
	public static void main(String[] args) {
		TimeSeriesGeneratorFactory factory = TimeSeriesGeneratorFactory.getInstance();
		TimeSeriesGenerator generator = factory.getGenerator("Random");
		System.out.println(generator.getType());
	}
}
