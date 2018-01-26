package com.n26.assignment.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.n26.assignment.dto.StatisticsDto;
import com.n26.assignment.dto.TransactionDto;
import com.n26.assignment.exception.BadRequestException;
import com.n26.assignment.service.StatisticsService;

@RestController
public class StatisticsApi {

	private StatisticsService service;

	public StatisticsApi(StatisticsService service) {
		this.service = service;
	}
	
	@RequestMapping(path = "transactions", method = RequestMethod.POST)
	public void newTransaction(@RequestBody TransactionDto transaction) {
		if (transaction == null || transaction.getAmount() == null || transaction.getTimestamp() == null) {
			throw new BadRequestException();
		}

		service.add(transaction);
	}

	@RequestMapping(path = "statistics", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<StatisticsDto> getStatistics() {
		return ResponseEntity.ok(service.retrieve());
	}

}