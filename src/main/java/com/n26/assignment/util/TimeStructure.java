package com.n26.assignment.util;

import java.util.ArrayList;
import java.util.List;

public class TimeStructure<T> {
	
	/** the key is a representation of date and time,
	 *  without the seconds **/
	private Long key;
	
	/** the bucket maps for the seconds, 
	 * number of buckets per second depends on the number of buckets */
	private List<T> buckets;
	
	private int numberOfBuckets;
	private int secondsPerBucket;
	
	public TimeStructure(int numberOfBuckets){
		this.numberOfBuckets = numberOfBuckets;
		this.buckets = new ArrayList<T>(numberOfBuckets);
		this.secondsPerBucket = 60/numberOfBuckets;
	}
	
	private static Long createKey(Long time) {
		return time / 60000;
	}
	public Long getKey() {
		return this.key;
	}
	
	public void setKey(Long time) {
		this.key = createKey(time);
	}

	public boolean hasKey(Long time) {
		return createKey(time).equals(this.key);
	}

	public T getValue(Long time) {
		int seconds = (time/1000) 
		int index = secondsPerBucket
		return buckets.get(bucketNumber);
	}
	
}
