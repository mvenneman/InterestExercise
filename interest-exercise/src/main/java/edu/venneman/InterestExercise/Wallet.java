package edu.venneman.InterestExercise;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Wallet implements InterestBearer {
	private long walletId;
	private List<CreditCard> creditCards;
	
	public Wallet(long walletId) {
		this.walletId = walletId;
	}
	
	public long getWalletId() {
		return walletId;
	}
	
	public List<CreditCard> getCreditCards() {
		return creditCards;
	}

	public void addCreditCard(CreditCard creditCard) {
		if (this.creditCards == null) {
			this.creditCards = new ArrayList<CreditCard>();
		}
		this.creditCards.add(creditCard);
	}

	@Override
	public BigDecimal calculateCurrentInterestOwed() {
		return this.creditCards.stream().map(w->w.calculateCurrentInterestOwed()).reduce(BigDecimal.ZERO, BigDecimal::add);
	}
}
