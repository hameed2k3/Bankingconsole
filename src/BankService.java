import java.util.HashMap;
import java.util.Map;

/**
 * BankService.java
 * Service class that manages all banking operations.
 * Uses HashMap to store accounts in memory for efficient lookup.
 * 
 * @author Banking System
 * @version 1.0
 */
public class BankService {
    
    // HashMap to store accounts with account number as key
    private Map<String, BankAccount> accounts;
    
    // Counter for generating unique account numbers
    private int accountCounter;
    
    /**
     * Constructor - initializes the accounts HashMap
     */
    public BankService() {
        this.accounts = new HashMap<>();
        this.accountCounter = 1000;
    }
    
    /**
     * Generates a unique account number
     */
    private String generateAccountNumber() {
        accountCounter++;
        return "ACC" + accountCounter;
    }
    
    /**
     * Creates a new bank account
     */
    public BankAccount createAccount(String holderName) {
        String accountNumber = generateAccountNumber();
        BankAccount newAccount = new BankAccount(accountNumber, holderName);
        accounts.put(accountNumber, newAccount);
        return newAccount;
    }
    
    /**
     * Creates a new bank account with initial deposit
     */
    public BankAccount createAccount(String holderName, double initialDeposit) {
        String accountNumber = generateAccountNumber();
        BankAccount newAccount = new BankAccount(accountNumber, holderName, initialDeposit);
        accounts.put(accountNumber, newAccount);
        return newAccount;
    }
    
    /**
     * Retrieves an account by account number
     */
    public BankAccount getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }
    
    /**
     * Checks if an account exists
     */
    public boolean accountExists(String accountNumber) {
        return accounts.containsKey(accountNumber);
    }
    
    /**
     * Deposits money into an account
     */
    public boolean deposit(String accountNumber, double amount) {
        BankAccount account = accounts.get(accountNumber);
        if (account != null) {
            return account.deposit(amount);
        }
        return false;
    }
    
    /**
     * Withdraws money from an account
     */
    public boolean withdraw(String accountNumber, double amount) {
        BankAccount account = accounts.get(accountNumber);
        if (account != null) {
            return account.withdraw(amount);
        }
        return false;
    }
    
    /**
     * Gets the balance of an account
     */
    public double getBalance(String accountNumber) {
        BankAccount account = accounts.get(accountNumber);
        if (account != null) {
            return account.getBalance();
        }
        return -1;
    }
    
    /**
     * Gets the total number of accounts
     */
    public int getTotalAccounts() {
        return accounts.size();
    }
}
