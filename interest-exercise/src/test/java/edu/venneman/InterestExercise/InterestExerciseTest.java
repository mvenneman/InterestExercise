package edu.venneman.InterestExercise;

import java.math.BigDecimal;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class InterestExerciseTest {
	//Hard-coded interest rates for testing
	//Might pull current interest rates from database via service in production system
	private static final BigDecimal VISA_INTRST_RT = new BigDecimal(0.10);
	private static final BigDecimal MC_INTRST_RT = new BigDecimal(0.05);
	private static final BigDecimal DISC_INTRST_RT = new BigDecimal(0.01);

	@Test
	/**
	 * 1 person has 1 wallet and 3 cards (1 Visa, 1 MC, 1 Discover)
	 * Each Card has a balance of $100
	 * 
	 * The interest for the Visa should be 10% of $100 or $10
	 * The interest for the MC should be 5% of $100 or $5
	 * The interest for the Discover should be 1% of $100 or $1
	 * 
	 * The interest for the Person should be the sum or $16
	 */
	public void onePersonOneWalletThreeCards() {
		Person person = new Person(1L);
		
		Wallet wallet = new Wallet(1L);

		CreditCard visa = new CreditCardImpl(1L, CreditCardType.VISA, VISA_INTRST_RT);
		visa.setBalancePastDue(new BigDecimal(100));
		
		CreditCard mastercard = new CreditCardImpl(2L, CreditCardType.MASTERCARD, MC_INTRST_RT);
		mastercard.setBalancePastDue(new BigDecimal(100));
		
		CreditCard discover = new CreditCardImpl(3L, CreditCardType.DISCOVER, DISC_INTRST_RT);
		discover.setBalancePastDue(new BigDecimal(100));
		
		wallet.addCreditCard(visa);
		wallet.addCreditCard(mastercard);
		wallet.addCreditCard(discover);

		person.addWallet(wallet);
		
		//The Visa with ID# 1 should have $10 in interest
		CreditCard card = person.wallets.get(0).getCreditCards().stream().filter(c->c.getCardId() == 1L).findFirst().get();
		assertEquals(0, new BigDecimal(10.00).compareTo(card.calculateCurrentInterestOwed()));
		
		//The MasterCard with ID# 2 should have $5 in interest
		card = person.wallets.get(0).getCreditCards().stream().filter(c->c.getCardId() == 2L).findFirst().get();
		assertEquals(0, new BigDecimal(5.00).compareTo(card.calculateCurrentInterestOwed()));
		
		//The Discover with ID# 3 should have $1 in interest
		card = person.wallets.get(0).getCreditCards().stream().filter(c->c.getCardId() == 3L).findFirst().get();
		assertEquals(0, new BigDecimal(1.00).compareTo(card.calculateCurrentInterestOwed()));
		
		//The person should have the sum interest of $16
		assertEquals(0, new BigDecimal(16.00).compareTo(person.calculateCurrentInterestOwed()));
	}

	@Test
	/**
	 * 1 person has 2 wallets
	 * Wallet 1 has a Visa and Discover
	 * Wallet 2 has a MC
	 * Each card has $100 balance
	 * 
	 * The interest for Wallet 1 should be 10% of $100 (Visa) plus 1% of $100 (Discover) or $11
	 * The interest for Wallet 2 should be 5% of $100 (MasterCard) or $5
	 * 
	 * The interest for the Person should be the sum or $16
	 */
	public void onePersonTwoWallets() {
		Person person = new Person(1L);
		
		Wallet wallet1 = new Wallet(1L);
		Wallet wallet2 = new Wallet(2L);

		CreditCard visa = new CreditCardImpl(1L, CreditCardType.VISA, VISA_INTRST_RT);
		visa.setBalancePastDue(new BigDecimal(100));
		
		CreditCard discover = new CreditCardImpl(2L, CreditCardType.DISCOVER, DISC_INTRST_RT);
		discover.setBalancePastDue(new BigDecimal(100));
		
		wallet1.addCreditCard(visa);
		wallet1.addCreditCard(discover);
		
		person.addWallet(wallet1);

		CreditCard mastercard = new CreditCardImpl(3L, CreditCardType.MASTERCARD, MC_INTRST_RT);
		mastercard.setBalancePastDue(new BigDecimal(100));
		
		wallet2.addCreditCard(mastercard);

		person.addWallet(wallet2);
		
		//Wallet 1 (ID# 1) should have an interest of $11
		Wallet wallet = person.wallets.stream().filter(w->w.getWalletId() == 1L).findFirst().get();
		assertEquals(0, new BigDecimal(11.00).compareTo(wallet.calculateCurrentInterestOwed()));

		//Wallet 2 (ID# 2) should have an interest of $5
		wallet = person.wallets.stream().filter(w->w.getWalletId() == 2L).findFirst().get();
		assertEquals(0, new BigDecimal(5.00).compareTo(wallet.calculateCurrentInterestOwed()));
		
		//The person should have the sum interest of $16
		assertEquals(0, new BigDecimal(16.00).compareTo(person.calculateCurrentInterestOwed()));
	}

	@Test
	/**
	 * 2 people have 1 wallet each
	 * Person 1 has 1 wallet with 3 cards (1 MC, 1 Visa, 1 Discover)
	 * Person 2 has 1 wallet with 2 cards (1 Visa, 1 MC)
	 * All cards in all wallets for both people have a $100 balance
	 * 
	 * The interest for Person 1 should be $16.00
	 * The interest for Person 2 should be $15.00
	 * 
	 * Since each person has only the one wallet, 
	 * the interest for the Wallet should be the same as the total for the person.
	 */
	public void twoPeopleOneWalletEach() {
		Person person1 = new Person(1L);
		Person person2 = new Person(2L);
		
		Wallet wallet1 = new Wallet(1L);
		Wallet wallet2 = new Wallet(2L);

		CreditCard mastercard1 = new CreditCardImpl(1L, CreditCardType.MASTERCARD, MC_INTRST_RT);
		mastercard1.setBalancePastDue(new BigDecimal(100));
		
		CreditCard visa1 = new CreditCardImpl(2L, CreditCardType.VISA, VISA_INTRST_RT);
		visa1.setBalancePastDue(new BigDecimal(100));
		
		CreditCard discover = new CreditCardImpl(3L, CreditCardType.DISCOVER, DISC_INTRST_RT);
		discover.setBalancePastDue(new BigDecimal(100));
		
		wallet1.addCreditCard(mastercard1);
		wallet1.addCreditCard(visa1);
		wallet1.addCreditCard(discover);
		
		person1.addWallet(wallet1);

		CreditCard visa2 = new CreditCardImpl(4L, CreditCardType.VISA, VISA_INTRST_RT);
		visa2.setBalancePastDue(new BigDecimal(100));
		
		CreditCard mastercard2 = new CreditCardImpl(5L, CreditCardType.MASTERCARD, MC_INTRST_RT);
		mastercard2.setBalancePastDue(new BigDecimal(100));
		
		wallet2.addCreditCard(visa2);
		wallet2.addCreditCard(mastercard2);

		person2.addWallet(wallet2);
		
		//Wallet 1 (ID# 1) should have an interest of $16
		Wallet wallet = person1.wallets.stream().filter(w->w.getWalletId() == 1L).findFirst().get();
		assertEquals(0, new BigDecimal(16.00).compareTo(wallet.calculateCurrentInterestOwed()));

		//Wallet 2 (ID# 2) should have an interest of $15
		wallet = person2.wallets.stream().filter(w->w.getWalletId() == 2L).findFirst().get();
		assertEquals(0, new BigDecimal(15.00).compareTo(wallet.calculateCurrentInterestOwed()));

		//Person 1 should have an interest of $16
		assertEquals(0, new BigDecimal(16.00).compareTo(person1.calculateCurrentInterestOwed()));

		//Person 2 should have an interest of $15
		assertEquals(0, new BigDecimal(15.00).compareTo(person2.calculateCurrentInterestOwed()));
	}
}
