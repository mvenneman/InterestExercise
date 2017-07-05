package edu.venneman.InterestExercise;

import java.math.BigDecimal;

public class CreditCardImpl extends CreditCard {
	public CreditCardImpl(long cardId, CreditCardType cardType, BigDecimal interestRate) {
		super(cardId, cardType, interestRate);
	}

	@Override
	void setBalancePastDue(BigDecimal balancePastDue) {
		super.balancePastDue = balancePastDue;
	}
}
