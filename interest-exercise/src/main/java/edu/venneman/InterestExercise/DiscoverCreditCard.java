package edu.venneman.InterestExercise;

import java.math.BigDecimal;

public class DiscoverCreditCard extends CreditCard {
	private static final BigDecimal DISCOVER_INTEREST_RATE = new BigDecimal(0.01);
	
	public DiscoverCreditCard(long cardId) {
		super(cardId, DISCOVER_INTEREST_RATE);
	}

	@Override
	void setBalancePastDue(BigDecimal balancePastDue) {
		super.balancePastDue = balancePastDue;
	}
}
