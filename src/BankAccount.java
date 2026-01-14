/**
 * BankAccount.java
 * Represents a bank account with account number, holder name, and balance.
 * Demonstrates encapsulation with private fields and public getter/setter methods.
 * 
 * @author Banking System
 * @version 1.0
 */
public class BankAccount {
    
    // Private fields for encapsulation
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    
    /**
     * Default constructor - creates an account with zero balance
     */
    public BankAccount() {
        this.balance = 0.0;
    }
    
    /**
     * Parameterized constructor - creates an account with specified details
     * 
     * @param accountNumber    Unique account identifier
     * @param accountHolderName Name of the account holder
     */
    public BankAccount(String accountNumber, String accountHolderName) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = 0.0;
    }
    
    /**
     * Parameterized constructor with initial balance
     * 
     * @param accountNumber     Unique account identifier
     * @param accountHolderName Name of the account holder
     * @param initialBalance    Initial deposit amount
     */
    public BankAccount(String accountNumber, String accountHolderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance >= 0 ? initialBalance : 0.0;
    }
    
    // ==================== Getter Methods ====================
    
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public String getAccountHolderName() {
        return accountHolderName;
    }
    
    public double getBalance() {
        return balance;
    }
    
    // ==================== Setter Methods ====================
    
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    
    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }
    
    // ==================== Banking Operations ====================
    
    /**
     * Deposits money into the account
     * @param amount The amount to deposit
     * @return true if deposit is successful, false otherwise
     */
    public boolean deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            return true;
        }
        return false;
    }
    
    /**
     * Withdraws money from the account with balance validation
     * @param amount The amount to withdraw
     * @return true if withdrawal is successful, false if insufficient balance
     */
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= this.balance) {
            this.balance -= amount;
            return true;
        }
        return false;
    }
    
    @Override
    public String toString() {
        return String.format(
            "\n+--------------------------------------------+\n" +
            "|           ACCOUNT DETAILS                  |\n" +
            "+--------------------------------------------+\n" +
            "|  Account Number  : %-22s |\n" +
            "|  Account Holder  : %-22s |\n" +
            "|  Current Balance : $%-21.2f |\n" +
            "+--------------------------------------------+",
            accountNumber, accountHolderName, balance
        );
    }
}
