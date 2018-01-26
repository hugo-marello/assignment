package com.n26.assignment.util;

public class CircularTimeArray<T>{
	
	private TimeStructure<T>[] predictions;
	
	@SuppressWarnings("unchecked")
	public CircularTimeArray(int predictionsNumber, int bucketsPerPrediction) {
		predictions = new TimeStructure[predictionsNumber];
		for(int i=0; i< predictionsNumber; i++) {
			predictions[i] = new TimeStructure<T>(bucketsPerPrediction);
		}
	}
	
	public boolean hasPredictionFor(Long timeStamp) {
		for(TimeStructure<T> structure : predictions) {
			if(structure.hasKey(timeStamp)) {
				return true;
			}
		}
		return false;
	}
	
	public T getPrediction(Long timeStamp) {
		for(TimeStructure<T> structure : predictions) {
			if(structure.hasKey(timeStamp)) {
				return structure.getValue(timeStamp);
			}
		}
		return null;
	}
}
