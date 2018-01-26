package com.n26.assignment.service;

import static org.junit.Assert.assertEquals;

import java.time.Instant;

import org.junit.Before;
import org.junit.Test;

import com.n26.assignment.dto.StatisticsDto;
import com.n26.assignment.dto.TransactionDto;
import com.n26.assignment.exception.NoContentException;

public class StatisticsServiceTest {

	private StatisticsService service;
	private Long millis;
	
	@Before
	public void init() {
		service = new StatisticsService();
		millis = Instant.now().toEpochMilli();
		
		service.add(new TransactionDto(1D, millis-300));
		service.add(new TransactionDto(4D, millis));
		service.add(new TransactionDto(2D, millis-200));
		service.add(new TransactionDto(3D, millis-100));
	}
	
	@Test
	public void mustInsertInOrder() {
		TransactionDto transaction = new TransactionDto(0D, millis-500);
		service.add(transaction);
		assertEquals(transaction, service.cache.get(0));
	}
	
	@Test(expected=NoContentException.class)
	public void mustDiscardFutureDates() {
		service.add(new TransactionDto(0D, millis + 10000));
	}
	
	@Test(expected=NoContentException.class)
	public void mustDiscardExpiredDates() {
		service.add(new TransactionDto(0D, millis - 60000));
	}
	
	@Test
	public void mustRetriveCorrectStatistics() {
		StatisticsDto statistics = service.retrieve();
		
		assertEquals(Long.valueOf(4), statistics.getCount());
		assertEquals(Double.valueOf(4), statistics.getMax());
		assertEquals(Double.valueOf(1), statistics.getMin());
		assertEquals(Double.valueOf(10), statistics.getSum());
		assertEquals(Double.valueOf(2.5), statistics.getAvg());
	}
	
	@Test
	public void mustRetriveNonExpiredStatistics() {
		StatisticsService mockService = new StatisticsService() {
			@Override
			public void add(TransactionDto transaction) {
				cache.add(transaction);
			}
		};

		mockService.add(new TransactionDto(1D, millis-300));
		mockService.add(new TransactionDto(4D, millis));
		mockService.add(new TransactionDto(2D, millis-100000));
		mockService.add(new TransactionDto(3D, millis-60000));

		StatisticsDto statistics = mockService.retrieve();
		
		assertEquals(Long.valueOf(2), statistics.getCount());
		assertEquals(Double.valueOf(4), statistics.getMax());
		assertEquals(Double.valueOf(1), statistics.getMin());
		assertEquals(Double.valueOf(5), statistics.getSum());
		assertEquals(Double.valueOf(2.5), statistics.getAvg());
	}
}
