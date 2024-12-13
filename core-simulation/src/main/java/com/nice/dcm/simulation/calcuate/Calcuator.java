package com.nice.dcm.simulation.calcuate;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Calcuator {
	
	/**
	 * Adjust the remaining time for the contacts based on the number of contacts
	 * remainingTimes[0] is the remaining time for the first contact
	 * remainingTimes[1] is the remaining time for the second contact and so on
	 * 
	 * Condition: remainingTimes should be sorted in ascending order, 
	 * and remainingTimes[i] < remainingTimes[i+1]
	 * @param remainingTimes
	 * @return
	 */
	public double[] adjustRemainingTime(double... remainingTimes) {
		double[] adjustedRemainingTimes = new double[remainingTimes.length];

		int numOfContacts = remainingTimes.length;
		
		double previousAdjustedRemaining = 0;
		double previousRemaining = 0;
		for (int i = 0; i < remainingTimes.length; i++) {
			adjustedRemainingTimes[i] = previousAdjustedRemaining 
					+ (remainingTimes[i] - previousRemaining) * getMultipier(numOfContacts);

			//store the current value for the next iteration
			previousAdjustedRemaining = adjustedRemainingTimes[i];
			previousRemaining = remainingTimes[i];
			
			//reduce the number of contacts by 1
			numOfContacts--;
		}
		return adjustedRemainingTimes;
	}
	
	
	/**
	 * Adjust the remaining time for the contacts based on the number of contacts
	 * remainingTimes[0] is the remaining time for the first contact
	 * remainingTimes[1] is the remaining time for the second contact and so on
	 * 
	 * Condition: remainingTimes should be sorted in ascending order, 
	 * and remainingTimes[i] <= remainingTimes[i+1]
	 * @param remainingTimes
	 * @return
	 */
	public double[] adjustRemainingTimeEx(double... remainingTimes) {
		double[] adjustedRemainingTimes = new double[remainingTimes.length];

		int numOfContacts = remainingTimes.length;
		
		double previousAdjustedRemaining = 0;
		double previousRemaining = 0;
		for (int i = 0; i < remainingTimes.length; i++) {
			if (previousRemaining == remainingTimes[i]) {
				//the remaining time is the same as the previous one
				adjustedRemainingTimes[i] = previousAdjustedRemaining;
			} else {
				adjustedRemainingTimes[i] = previousAdjustedRemaining 
						+ (remainingTimes[i] - previousRemaining) * getMultipier(numOfContacts);

				//store the current value for the next iteration
				previousAdjustedRemaining = adjustedRemainingTimes[i];
				previousRemaining = remainingTimes[i];
			}
			
			//reduce the number of contacts by 1
			numOfContacts--;
		}
		return adjustedRemainingTimes;
	}
	
	
	public static double getMultipier(int numOfContacts) {
		return 1.0 + 0.1 * (numOfContacts - 1);
	}
}
