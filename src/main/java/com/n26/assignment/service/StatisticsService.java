package com.n26.assignment.service;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.n26.assignment.dto.StatisticsDto;
import com.n26.assignment.dto.TransactionDto;
import com.n26.assignment.exception.NoContentException;
import com.n26.assignment.util.CircularTimeArray;
import com.n26.assignment.util.TimeStructure;

@Service
@Scope
public class StatisticsService {
	public final static long TIME_TO_LIVE = 60000;
	private CircularTimeArray<StatisticsDto> predictionsArray = new CircularTimeArray<StatisticsDto>(2, 60);
	
	
	public StatisticsDto retrieve(Long currentTime) {
		if(predictionsArray.hasPredictionFor(currentTime)) {
			return predictionsArray.getPrediction(currentTime);
		}
		return new StatisticsDto();
	}
	
	public void add(TransactionDto transaction) {
    	if ( isOnTime(transaction.getTimestamp()) ){
    		//TODO
    	} else {
    		throw new NoContentException();
    	}
	}
	
    public boolean isOnTime(long value) {
    	Instant currentTime = Instant.now();
    	Instant limitTime = currentTime.minusMillis(StatisticsService.TIME_TO_LIVE);
    	Instant transactionTime = Instant.ofEpochMilli(value);
    	
    	return transactionTime.isAfter(limitTime) && transactionTime.isBefore(currentTime);
    }
    
    private Long makeKey(Long time) {
    	return time/ 60000; //this will remove the seconds
    }
}
