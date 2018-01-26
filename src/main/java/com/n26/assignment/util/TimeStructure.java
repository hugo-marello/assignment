package com.n26.assignment.util;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 
 * This class maps a specific minute from a date and time to buckets(value),
 * each bucket represent a range of seconds in that minute.
 * 
 * All the buckets together represent one whole minute, the same minute that is mapped here as key.
	 * Example: 
	 * 		Using 60 buckets to map the time 01/01/2018 14:00:01.0010,
	 *      each bucket would be a range of 1000 milliseconds(1 second)
	 *      bucket number 0 is mapping from '01/01/2018 14:00:00.000' to '01/01/2018 14:00:01.000'
	 *      bucket number 59 is mapping from '01/01/2018 14:00:54.000' to '01/01/2018 14:00:59.999'
 */
public class TimeStructure<T> {
	
	/** the key is a representation of date and time, without the seconds **/
	private Long key;
	
	/** each bucket map a range of milliseconds for the given key */
	private Object[] buckets;
	
	private int millisPerBucket;
	
	public TimeStructure(int numberOfBuckets){
		this.buckets = new Object[numberOfBuckets];
		this.millisPerBucket = 60000/numberOfBuckets;
	}
	
	public TimeStructure(int numberOfBuckets, Long time){
		this(numberOfBuckets);
		this.setKey(time);
	}
	
	private static Long createKey(Long time) {
		return (time / 60000) * 60000;
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

	public int getIndexOf(Long time) {
		if(hasKey(time)) {
			int secondsAndMilli = (int) (time % 60000);
			return secondsAndMilli/ this.millisPerBucket;
		}
		return -1;	
	}
	
	@SuppressWarnings("unchecked")
	public T getValue(Long time) {
		return (T) buckets[getIndexOf(time)];
	}
	
	public void setValue(Long time, T value) {
		buckets[getIndexOf(time)] = value;
	}

	@SuppressWarnings("unchecked")
	public Stream<T> slice(Integer begin, Integer end) {
		 if(begin == null) {
			 begin = 0;
		 }
		 if(end == null) {
			 end = buckets.length;
		 }
		return Arrays.stream(buckets).limit(end).skip(begin).map(obj -> (T)obj);
	}
}
