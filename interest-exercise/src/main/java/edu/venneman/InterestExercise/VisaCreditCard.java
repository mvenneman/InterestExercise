package edu.venneman.InterestExercise;

import java.math.BigDecimal;

public class VisaCreditCard extends CreditCard {
	private static final BigDecimal VISA_INTEREST_RATE = new BigDecimal(0.1);
	
	public VisaCreditCard(long cardId) {
		super(cardId, VISA_INTEREST_RATE);
	}

	@Override
	void setBalancePastDue(BigDecimal balancePastDue) {
		super.balancePastDue = balancePastDue;
	}
}
