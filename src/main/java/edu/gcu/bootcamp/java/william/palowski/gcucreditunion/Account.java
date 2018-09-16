package edu.gcu.bootcamp.java.william.palowski.gcucreditunion;

public class Account {
	
	protected double balance;
	protected String account;
	
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
/*
 * This withdraw method will work for both the saving class and the checking class.  See the checking class for an override method of the 
 * same name.
 */
	public void doWithdraw(double amount) {
		double newBalance = this.balance - amount;
		this.setBalance(newBalance);
		
	}
	
/*
 * The doDeposit method works for both the saving class and the checking class
 */
	public void doDeposit(double amount) {
		double newBalance = this.balance + amount;
		this.setBalance(newBalance);
		
	}

}
