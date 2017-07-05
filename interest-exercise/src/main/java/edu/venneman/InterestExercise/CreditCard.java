package edu.venneman.InterestExercise;

import java.math.BigDecimal;
import java.math.RoundingMode;

abstract class CreditCard implements InterestBearer {
	protected long cardId;
	protected CreditCardType cardType;
	protected BigDecimal interestRate;
	protected BigDecimal balancePastDue;
	
	protected CreditCard(long cardId, CreditCardType cardType, BigDecimal interestRate) {
		this.cardId = cardId;
		this.interestRate = interestRate;
		this.balancePastDue = BigDecimal.ZERO;
	}
	
	public long getCardId() {
		return this.cardId;
	}
	
	public CreditCardType getCardType() {
		return this.cardType;
	}

	public BigDecimal getInterestRate() {
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
