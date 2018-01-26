package com.n26.assignment.dto;

public class TransactionDto {

	private Double amount;

	private Long timestamp;
	
	public TransactionDto(Double amount, Long timestamp) {
		this.amount = amount;
		this.timestamp = timestamp;
	}

	public TransactionDto() {
		super();
	}
	
	@Override
	public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof TransactionDto)) {
            return false;
        }

        TransactionDto transaction = (TransactionDto) o;

        return transaction.amount.equals(amount) &&
        	transaction.timestamp.equals(timestamp);
	}
	
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + amount.hashCode();
        result = 31 * result + timestamp.hashCode();
        return result;
    }
    
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

}
