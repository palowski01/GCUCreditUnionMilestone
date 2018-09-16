/*
 * Bank Application created by William Palowski
 */

package edu.gcu.bootcamp.java.william.palowski.gcucreditunion;

import java.util.Scanner;

public class Bank {
	static Scanner input = new Scanner(System.in);
	private String name;
	
	public static void main(String[] args) {
		
		Bank gcu = new Bank("GCU Credit Union");
		
		Checking billChecking = new Checking(5000.00, "991773");
		Saving billSaving = new Saving(5000.00, "191923");
		billChecking.setOverDraft(45.00);
		billSaving.setMinBalance(200);
		billSaving.setAnnualInterestRate(.06);
		billSaving.setServiceFee(25);
		gcu.displayMenu(billChecking, billSaving);
		
	}
	
	public Bank(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	/*
	 * Creates menu for the Banking application
	 */
	private void displayMenu(Checking checking, Saving saving) {
		int option = 0;
		//The do loop allows you to stay in the menu until a 9 is entered to exit the application
		do {
			System.out.println("=========================================");
			System.out.println("               MAIN MENU                 ");
			System.out.println("            " + this.name + "              ");
			System.out.println("=========================================");
			System.out.println("Pick an option:");
			System.out.println("-----------------------------------------");
			System.out.println("1: : Deposit to Checking");
			System.out.println("2: : Deposit to Savings");
			System.out.println("3: : Write a Check");
			System.out.println("4: : Withdraw from Savings");
			System.out.println("5: : Get Account Balance");
			System.out.println("6: : Monthly Statement"); //calculate service fee and interest earned
			System.out.println("-----------------------------------------");
			System.out.println("9: : Exit");
			option = input.nextInt();
			System.out.println();
			this.actionMenu(option, checking, saving);
		}while(option != 9);
		
	}
	
/*
 * The action menu allows takes an input from the main menu and sends you to the proper action.  Each appropriate method is called
 * depending on the option you choose.
 */
	private void actionMenu(int opt, Checking checking, Saving saving) {
		switch(opt) {
			case 1: this.displayDepositChecking(checking);
				break;
			case 2: this.displayDepositSaving(saving);
				break;
			case 3: this.displayWithdrawChecking(checking);
				break;
			case 4: this.displayWithdrawSavings(saving);
				break;
			case 5: this.displayBalanceScreen(checking, saving);
				break;
			case 6: this.doEndOfMonth(checking, saving);
				break;
			case 9: this.displayExitScreen();
				break;
		}
	}
	
/*
 * Calculates the end of month totals for each account to include any service fees or overdraft fees.	
 */
	private void doEndOfMonth(Checking checking, Saving saving) {

		double interestRatePerMonth = (saving.getAnnualInterestRate()/12); //holds interest rate per month for ease of calcualtion
		double interestOnAccount = saving.balance * interestRatePerMonth;
		
		System.out.println("Calculate end of month items");
		System.out.printf("Savings account #%s has a balance of $%.2f", saving.account, saving.balance);
		
		if (saving.balance < saving.getMinBalance()) {
			System.out.printf("Your account is below the minimum balance of $%.2f", saving.getMinBalance());
			System.out.printf("You are being charged a service fee of $%.2f", saving.getServiceFee());
			double balanceMinusServiceFee = saving.getBalance() - saving.getServiceFee();
			System.out.printf("Your new balance is $%.2f", balanceMinusServiceFee);
			saving.setBalance(balanceMinusServiceFee +(balanceMinusServiceFee * interestRatePerMonth));
		}
		else {
			saving.setBalance(saving.getBalance() + (saving.getBalance() * interestRatePerMonth));
		}
		System.out.println();
		System.out.printf("Your savings account balance plus $%.2f of interest for the month is $%.2f", interestOnAccount, saving.getBalance());
		System.out.println();
		System.out.printf("Checking account #%s has a balance of $%.2f", checking.account, checking.balance);
		System.out.println();
		if (checking.balance < 0) {
			System.out.printf("Your account is below 0. you were assessed a $%.2f overdraft fee.", checking.getOverDraft());
		}
		System.out.println();
	}
	
/*
 * Displayed exit screen when 9 is pressed	
 */
	private void displayExitScreen() {
		System.out.println("=========================================");
		System.out.println("      THANK YOU FOR BANKING WITH         ");
		System.out.println("            " + this.name + "              ");
		System.out.println("=========================================");
	}
	
/*
 * Displays the balance on both accounts	
 */
	private void displayBalanceScreen(Checking checking, Saving saving) {
		
		System.out.printf("For checking account #%s your balance is $%.2f", checking.account, checking.balance);
		System.out.println();
		System.out.printf("For savings account #%s your balance is $%.2f", saving.account, saving.balance);
		System.out.println();
	}

/*
 * Displays the withdraw from savings screen and asks the amount to withdraw.  Then accesses the doWithdraw method in Account
 */
	private void displayWithdrawSavings(Saving saving) {

		System.out.println("WITHDRAW FROM SAVINGS ACCOUNT #" + saving.account);
		System.out.printf("You will have a $%.2f service fee if balance is below $%.2f at the end of the month", 
				saving.getServiceFee(), saving.getMinBalance());
		System.out.println();
		System.out.println("Your savings account balance is $" + saving.balance);
		System.out.print("How much to you want to withdraw: $");
		double withdraw = input.nextDouble();
		System.out.println();
		saving.doWithdraw(withdraw);
		
	}
	
/*
 * Displays withdraw from checking menu.  Will ask for amount to withdraw and then either goto doWithdraw in Account or doWithdraw
 * in Checking depending on the balance in the account.  Will also notify you if there is an overdraft fee assessed.
 */
	private void displayWithdrawChecking(Checking checking) {
		
		System.out.println("WITHDRAW FROM CHECKING ACCOUNT #" + checking.account);
		System.out.println("Your checking account balance is $" + checking.balance);
		System.out.printf("You will have a $%.2f overdraft fee if check amount is greater than balance", checking.getOverDraft());
		System.out.println();
		System.out.print("How much to withdraw: $");
		double withdraw = input.nextDouble();
		System.out.println();
		checking.doWithdraw(withdraw);
		if (checking.getBalance() < 0) {
			System.out.printf("OVERDRAFT NOTICE: $%.2f fee assessed!", checking.getOverDraft());
			System.out.println();
		}
		
	}

/*
 * Displays deposit into savings screen and asks for amount to deposit.  Then calls the doDeposit method in Account class
 */
	private void displayDepositSaving(Saving saving) {
		
		System.out.println("DEPOSIT INTO SAVINGS ACCT #" + saving.account);
		System.out.printf("Your savings balance is $%.2f", saving.balance);
		System.out.println();
		System.out.print("How much do you want to deposit: $");
		double deposit = input.nextDouble();
		System.out.println();
		saving.doDeposit(deposit);
		
	}

/*
 * Displays deposit into checking screen and asks for amount to deposit.  Then calls the doDeposit method in Account Class
 */
	private void displayDepositChecking(Checking checking) {
		
		System.out.println("DEPOSIT INTO CHEKCING ACCT #" + checking.account);
		System.out.printf("Your checking balance is $%.2f", checking.balance);
		System.out.println();
		System.out.print("How much do you want to deposit: $");
		double deposit = input.nextDouble();
		System.out.println();
		checking.doDeposit(deposit);
		
	}

}
