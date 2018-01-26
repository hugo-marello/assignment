package com.n26.assignment.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CircularTimeArrayTest {
	private CircularTimeArray<Integer> circularArray;
	private Long millis;
	private LocalDateTime date;
	
	@Before
	public void init() {
		circularArray = new CircularTimeArray<>(10, 60);
		date = LocalDateTime.of(2018, 1, 1, 10, 10, 0);// 01/01/2018 10:10:00.000
		millis = date.toEpochSecond(ZoneOffset.UTC)*1000;
	}
	
	@Test
	public void mustCheckForExistingPrediction() {
		assertFalse(circularArray.hasPredictionFor(millis));
		//circularArray.
	}
}
