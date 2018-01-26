package com.n26.assignment.util;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Iterator;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

public class TimeStructureTest {

	private TimeStructure<Integer> structure;
	private Long millis;
	private LocalDateTime date;
	
	@Before
	public void init() {
		date = LocalDateTime.of(2018, 1, 1, 10, 10, 0);// 01/01/2018 10:10:00.000
		millis = date.toEpochSecond(ZoneOffset.UTC)*1000;
		structure = new TimeStructure<>(60, millis);
		structure.setValue(millis, 10);
		structure.setValue(millis+1001, 20);
		structure.setValue(millis+2011, 30);
	}
	
	@Test
	public void mustUseCorrectKey() {
		structure.setKey(millis + 1000);
		assertEquals(millis, structure.getKey());
		structure.setKey(millis + 59000);
		assertEquals(millis, structure.getKey());
		structure.setKey(millis + 60000);// must change if the minute has changed
		assertNotEquals(millis, structure.getKey());
	}
	
	@Test
	public void mustFindCorrectIndex() {
		int index = structure.getIndexOf(millis);
		assertEquals(0, index);
		index = structure.getIndexOf(millis + 999);
		assertEquals(0, index);
		index = structure.getIndexOf(millis + 1000);
		assertEquals(1, index);
		index = structure.getIndexOf(millis+ 59999);
		assertEquals(59, index);
		
		//another minute another key
		index = structure.getIndexOf(millis+ 60000);
		assertEquals(-1, index);
	}
	
	@Test
	public void mustSetAndGetCorrectValue() {
		assertEquals(Integer.valueOf(30), structure.getValue(millis+2000));
		assertEquals(Integer.valueOf(10), structure.getValue(millis+10));
		assertEquals(Integer.valueOf(20), structure.getValue(millis+1999));
	}
	
	@Test
	public void mustCheckFoKeys() {
		assertTrue(structure.hasKey(millis));
		assertTrue(structure.hasKey(millis+500));
		assertTrue(structure.hasKey(millis+59999));
		assertFalse(structure.hasKey(millis+60000));
	}
	
	@Test
	public void mustFetchPartialArray() {
		Stream<Integer> partial = structure.slice(null, null);
		assertEquals(60, partial.count());
		
		partial = structure.slice(0, 3);
		Iterator<Integer> iterator = partial.iterator();
		
		assertEquals(structure.getValue(millis), iterator.next());
		assertEquals(structure.getValue(millis+1000), iterator.next());
		assertEquals(structure.getValue(millis+2000), iterator.next());
		assertEquals(false, iterator.hasNext());
	}
}
