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
	
	public void doWithdraw(double amount) {
		double newBalance = this.balance - amount;
		this.setBalance(newBalance);
		
	}
	
	public void doDeposit(double amount) {
		double newBalance = this.balance + amount;
		this.setBalance(newBalance);
		
	}

}
