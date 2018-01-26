package com.n26.assignment.dto;

public class StatisticsDto {

	private Double sum = 0D;

	private Double max;

	private Double min;

	private Long count = 0L;

	public void newEntry(Double value) {
		this.count++;
		
		if(this.sum == null) {
			this.sum = value;
		} else {
			this.sum += value;
		}
		
		if( this.min == null || value < this.min) {
			this.min = value;
		}
		
		if( this.max == null || value > this.max) {
			this.max = value;
		}
	}

	public Double getSum() {
		return sum;
	}

	public Double getAvg() {
		return sum / count;
	}

	public Double getMax() {
		return max;
	}

	public Double getMin() {
		return min;
	}

	public Long getCount() {
		return count;
	}

}
