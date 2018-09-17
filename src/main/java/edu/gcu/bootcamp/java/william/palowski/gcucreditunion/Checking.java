package edu.gcu.bootcamp.java.william.palowski.gcucreditunion;

public class Checking extends Account{

	private double overDraft; //holds overdraft fee

	public double getOverDraft() {
		return overDraft;
	}

	public void setOverDraft(double overDraft) {
		this.overDraft = overDraft;
	}

/*
 * 	(non-Javadoc)
 * @see edu.gcu.bootcamp.java.william.palowski.gcucreditunion.Account#doWithdraw(double)
 * 
 * this method is only used when the balance in the checking account is below 0.  this will assess the overdraft charge to the account.
 */

	public void doWithdraw(double amount) {
		
		double newBalance = this.balance - amount;
		
		if (newBalance < 0) {
			newBalance = newBalance - overDraft;
			this.setBalance(newBalance);
		}
		else {
			this.setBalance(newBalance);
		}
	}
/**
 * 	
 * @param balance
 * @param account
 */
	public Checking(double balance, String account) {
		this.balance = balance;
		this.account = account;
	}

}
