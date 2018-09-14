package edu.gcu.bootcamp.java.william.palowski.gcucreditunion;

public class Checking extends Account{
	
	private double overDraft;

	public double getOverDraft() {
		return overDraft;
	}

	public void setOverDraft(double overDraft) {
		this.overDraft = overDraft;
	}
	
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
	
	public Checking(double balance, String account) {
		this.balance = balance;
		this.account = account;
	}

}
