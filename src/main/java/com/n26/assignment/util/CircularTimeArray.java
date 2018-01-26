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
	
	public boolean hasPredictionFor(Long timestamp) {
		for(TimeStructure<T> structure : predictions) {
			if(structure.hasKey(timestamp)) {
				return true;
			}
		}
		return false;
	}
	
	public TimeStructure<T> getPrediction(Long timestamp) {
		for(TimeStructure<T> structure : predictions) {
			if(structure.hasKey(timestamp)) {
				return structure;
			}
		}
		return null;
	}
	
//	public void addPrediction(Long timestamp) {
//		Stream<T> result;
//		if(hasPredictionFor(timestamp)) {
//			TimeStructure<T> prediction = getPrediction(timestamp);
//			int startIndex = prediction.getIndexOf(timestamp);
//			result = prediction.slice(startIndex, null);
//		}
//	}
}
