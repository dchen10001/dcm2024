package com.nice.dcm.simulation.generation;

import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.statistics.distribution.PoissonDistribution;

import com.nice.dcm.simulation.generation.output.VolumePair;
import com.nice.dcm.simulation.generation.output.VolumePairImpl;
import com.nice.dcm.simulation.generation.output.VolumePairRange;
import com.nice.dcm.simulation.generation.output.VolumePairRangeImpl;

public class AbstractTimeSeriesGeneratorImpl implements TimeSeriesGenerator {
	private final UniformRandomProvider uniformRandomProvider;
	
	protected AbstractTimeSeriesGeneratorImpl() {
		this(null);
	}
	
	protected AbstractTimeSeriesGeneratorImpl(Long seed) {
		this.uniformRandomProvider = UniformRandomProviderFactory.createKiss(seed);
	}

	protected UniformRandomProvider getUniformRandomProvider() {
		return uniformRandomProvider;
	}
	
	@Override
	public VolumePairRange getVolumePairRange(double mean) {
		PoissonDistribution generator = getPoissonDistribution(mean);		
		int volume = (int) mean;

		double accumulatedProbability = generator.probability(volume);
		VolumePair lowerBound = new VolumePairImpl(volume, accumulatedProbability);
		volume++;
		double p = generator.probability(volume);
		accumulatedProbability += p;
		VolumePair upperBound = new VolumePairImpl(volume, p);
		
		return new VolumePairRangeImpl(accumulatedProbability, lowerBound, upperBound);
	}
	
	@Override
	public VolumePairRange getVolumePairRange(double mean, double probability) {
		if (probability <= 0d || probability > 1d) {
            throw new IllegalArgumentException("probability must be in the range (0, 1].");
		}
		
		PoissonDistribution generator = getPoissonDistribution(mean);		
		int volume = (int) mean;
		double accumulatedProbability = generator.probability(volume);
		VolumePair lowerBound = new VolumePairImpl(volume, accumulatedProbability);
		
		double p = 0;
		while (accumulatedProbability < probability) {
			volume++;
			p = generator.probability(volume);
			accumulatedProbability += p;
			if (p <= 0.0001d) {
				break;
			}
		}
		
		if(volume == 0) {
			volume = 1;
			p = generator.probability(volume);
			accumulatedProbability += p;
		}
		
		VolumePair upperBound = (lowerBound.getVolume() == volume) ? null : new VolumePairImpl(volume, p);		
		return new VolumePairRangeImpl(accumulatedProbability, lowerBound, upperBound);
	}
	
	public double getPoissonPossibility(double mean, int volume) {
		if(volume < 0) {
			throw new IllegalArgumentException("volume is less than 0.");
		}
		PoissonDistribution generator = getPoissonDistribution(mean);
		return generator.probability(volume);
	}
	
	public double getPoissonPossibility(double mean, int lowerVolume, int upperVolume) {
		if(lowerVolume < 0) {
			throw new IllegalArgumentException("lowerVolume must be great than 0.");
		}

		if(lowerVolume > upperVolume) {
			throw new IllegalArgumentException("upperVolume must be great than lowerVolume.");
		}
		PoissonDistribution generator = getPoissonDistribution(mean);
		return generator.cumulativeProbability(upperVolume) - generator.cumulativeProbability(lowerVolume - 1);
	}
	
	private PoissonDistribution getPoissonDistribution(double mean) {
		if(mean <= 0) {
			throw new IllegalArgumentException("mean must be great than 0.");
		}
		return PoissonDistribution.of(mean);
	}
}
