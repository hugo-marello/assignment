package com.n26.assignment.api;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.n26.assignment.dto.TransactionDto;
import com.n26.assignment.exception.BadRequestException;
import com.n26.assignment.service.StatisticsService;

public class StatisticsApiTest {

	@Mock
	private StatisticsService service;
	private StatisticsApi api;
	private TransactionDto dto;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		dto = new TransactionDto();
		api = new StatisticsApi(service);
	}

	@Test
	public void mustRetrieveStatistics() {
		api.getStatistics();
		verify(service,times(1)).retrieve();
	}
	
	@Test(expected=BadRequestException.class)
	public void mustReportBadEntries() {
		api.newTransaction(dto);
	}
}
