package edu.venneman.InterestExercise;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Person implements InterestBearer {
	private long personId;
	List<Wallet> wallets;
	
	public Person(long personId) {
		this.personId = personId;
	}
	
	public long getPersonId() {
		return personId;
	}
	
	public List<Wallet> getWallets() {
		return wallets;
	}

	public void addWallet(Wallet wallet) {
		if (this.wallets == null) {
			this.wallets = new ArrayList<Wallet>();
		}
		this.wallets.add(wallet);
	}

	@Override
	public BigDecimal calculateCurrentInterestOwed() {
		return this.wallets.stream().map(w->w.calculateCurrentInterestOwed()).reduce(BigDecimal.ZERO, BigDecimal::add);
	}
}
