package edu.venneman.InterestExercise;

import java.math.BigDecimal;

public class MasterCardCreditCard extends CreditCard {
	private static final BigDecimal MASTERCARD_INTEREST_RATE = new BigDecimal(0.05);
	
	public MasterCardCreditCard(long cardId) {
		super(cardId, MASTERCARD_INTEREST_RATE);
	}

	@Override
	void setBalancePastDue(BigDecimal balancePastDue) {
		super.balancePastDue = balancePastDue;
	}
}
