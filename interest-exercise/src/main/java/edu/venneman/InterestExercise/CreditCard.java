package edu.venneman.InterestExercise;

import java.math.BigDecimal;
import java.math.RoundingMode;

abstract class CreditCard implements InterestBearer {
	protected long cardId;
	protected BigDecimal interestRate;
	protected BigDecimal balancePastDue;
	
	protected CreditCard(long cardId, BigDecimal interestRate) {
		this.cardId = cardId;
		this.interestRate = interestRate;
		this.balancePastDue = BigDecimal.ZERO;
	}
	
	public long getCardId() {
		return this.cardId;
	}
	
	protected BigDecimal getInterestRate() {
		return interestRate;
	}

	public BigDecimal getBalancePastDue() {
		return balancePastDue;
	}

	abstract void setBalancePastDue(BigDecimal balancePastDue);

	@Override
	public BigDecimal calculateCurrentInterestOwed() {
		return balancePastDue.multiply(interestRate).setScale(2, RoundingMode.HALF_EVEN);
	}
}
