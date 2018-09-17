package edu.gcu.bootcamp.java.william.palowski.gcucreditunion;

public class Saving extends Account{

		private double serviceFee;  //set to $25.00 in the main class
		private double annualInterestRate; // set to .06 in the main class. only used in calculating end of month balance in savings
		private double minBalance; // set to $200.00 in the main class
		
		public double getServiceFee() {
			return serviceFee;
		}
		public void setServiceFee(double serviceFee) {
			this.serviceFee = serviceFee;
		}
		public double getAnnualInterestRate() {
			return annualInterestRate;
		}
		public void setAnnualInterestRate(double annualInterestRate) {
			this.annualInterestRate = annualInterestRate;
		}
		public double getMinBalance() {
			return minBalance;
		}
		public void setMinBalance(double minBalance) {
			this.minBalance = minBalance;
		}
/**
 *  		
 * @param balance
 * @param account
 */
		public Saving(double balance, String account) {
			this.balance = balance;
			this.account = account;
		}
		
		
}
