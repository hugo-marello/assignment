package com.n26.assignment.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.n26.assignment.dto.StatisticsDto;
import com.n26.assignment.dto.TransactionDto;
import com.n26.assignment.exception.NoContentException;

@Service
@Scope
public class StatisticsService {
	public final static long TIME_TO_LIVE = 60000;
	protected List<TransactionDto> cache = new ArrayList<TransactionDto>();

	public StatisticsDto retrieve() {
		StatisticsDto result = new StatisticsDto();
		Iterator<TransactionDto> iterator = cache.iterator();
		Instant currentTime = Instant.now();
		
		while (iterator.hasNext()) {
			TransactionDto transaction = iterator.next();
			if (isOnTime(transaction, currentTime)) {
				result.newEntry(transaction.getAmount());
			} else {
				iterator.remove();
			}
		}
		return result;
	}

	public void add(TransactionDto transaction) {
		if (isOnTime(transaction, Instant.now())) {
			// inserting in order
			int index;
			for (index = 0; index < cache.size()
					&& transaction.getTimestamp() > cache.get(index).getTimestamp(); index++) { }

			cache.add(index, transaction);
		} else {
			throw new NoContentException();
		}
	}

	private boolean isOnTime(TransactionDto value, Instant currentTime) {
		Instant limitTime = currentTime.minusMillis(StatisticsService.TIME_TO_LIVE);
		Instant transactionTime = Instant.ofEpochMilli(value.getTimestamp());

		return transactionTime.isAfter(limitTime) && transactionTime.isBefore(currentTime);
	}
}
