import java.util.Scanner;

/**
 * BankingApplication.java
 * Main class for the Console-Based Banking System.
 * Provides a menu-driven interface for all banking operations.
 * 
 * @author Banking System
 * @version 1.0
 */
public class BankingApplication {
    
    private BankService bankService;
    private Scanner scanner;
    private boolean isRunning;
    
    public BankingApplication() {
        this.bankService = new BankService();
        this.scanner = new Scanner(System.in);
        this.isRunning = true;
    }
    
    private void displayWelcomeBanner() {
        System.out.println("\n");
        System.out.println("+===============================================================+");
        System.out.println("|                                                               |");
        System.out.println("|              TRANSX SIMPLE BANKING SYSTEM                     |");
        System.out.println("|                      Version 1.0                              |");
        System.out.println("|                                                               |");
        System.out.println("+===============================================================+");
        System.out.println("\n        Welcome to TransX Banking System!");
        System.out.println("      Your trusted partner in financial management.\n");
    }
    
    private void displayMenu() {
        System.out.println("\n+-------------------------------------------+");
        System.out.println("|              MAIN MENU                    |");
        System.out.println("+-------------------------------------------+");
        System.out.println("|   1. Create New Account                   |");
        System.out.println("|   2. Deposit Money                        |");
        System.out.println("|   3. Withdraw Money                       |");
        System.out.println("|   4. Check Account Balance                |");
        System.out.println("|   5. View Account Details                 |");
        System.out.println("|   6. Exit Application                     |");
        System.out.println("+-------------------------------------------+");
        System.out.print("\n   Enter your choice (1-6): ");
    }
    
    private void displayDivider() {
        System.out.println("\n---------------------------------------------");
    }
    
    private void createAccount() {
        displayDivider();
        System.out.println("           CREATE NEW ACCOUNT");
        displayDivider();
        
        System.out.print("\n   Enter Account Holder Name: ");
        String holderName = scanner.nextLine().trim();
        
        if (holderName.isEmpty()) {
            System.out.println("\n   [ERROR] Account holder name cannot be empty!");
            return;
        }
        
        if (!holderName.matches("[a-zA-Z ]+")) {
            System.out.println("\n   [ERROR] Name should contain only letters and spaces!");
            return;
        }
        
        System.out.print("   Enter Initial Deposit (0 for no deposit): $");
        double initialDeposit = getValidAmount();
        
        if (initialDeposit < 0) {
            System.out.println("\n   [ERROR] Initial deposit cannot be negative!");
            return;
        }
        
        BankAccount newAccount;
        if (initialDeposit > 0) {
            newAccount = bankService.createAccount(holderName, initialDeposit);
        } else {
            newAccount = bankService.createAccount(holderName);
        }
        
        System.out.println("\n   [SUCCESS] Account Created Successfully!");
        System.out.println("   =========================================");
        System.out.println("   Account Number : " + newAccount.getAccountNumber());
        System.out.println("   Account Holder : " + newAccount.getAccountHolderName());
        System.out.println("   Initial Balance: $" + String.format("%.2f", newAccount.getBalance()));
        System.out.println("   =========================================");
        System.out.println("\n   [NOTE] Please save your account number for future transactions!");
    }
    
    private void depositMoney() {
        displayDivider();
        System.out.println("            DEPOSIT MONEY");
        displayDivider();
        
        System.out.print("\n   Enter Account Number: ");
        String accountNumber = scanner.nextLine().trim().toUpperCase();
        
        if (!bankService.accountExists(accountNumber)) {
            System.out.println("\n   [ERROR] Account not found! Please check the account number.");
            return;
        }
        
        System.out.print("   Enter Deposit Amount: $");
        double amount = getValidAmount();
        
        if (amount <= 0) {
            System.out.println("\n   [ERROR] Deposit amount must be greater than zero!");
            return;
        }
        
        double previousBalance = bankService.getBalance(accountNumber);
        if (bankService.deposit(accountNumber, amount)) {
            System.out.println("\n   [SUCCESS] Deposit Successful!");
            System.out.println("   =========================================");
            System.out.println("   Deposited Amount : $" + String.format("%.2f", amount));
            System.out.println("   Previous Balance : $" + String.format("%.2f", previousBalance));
            System.out.println("   New Balance      : $" + String.format("%.2f", bankService.getBalance(accountNumber)));
            System.out.println("   =========================================");
        } else {
            System.out.println("\n   [ERROR] Deposit failed! Please try again.");
        }
    }
    
    private void withdrawMoney() {
        displayDivider();
        System.out.println("           WITHDRAW MONEY");
        displayDivider();
        
        System.out.print("\n   Enter Account Number: ");
        String accountNumber = scanner.nextLine().trim().toUpperCase();
        
        if (!bankService.accountExists(accountNumber)) {
            System.out.println("\n   [ERROR] Account not found! Please check the account number.");
            return;
        }
        
        double currentBalance = bankService.getBalance(accountNumber);
        System.out.println("   Current Balance: $" + String.format("%.2f", currentBalance));
        
        System.out.print("   Enter Withdrawal Amount: $");
        double amount = getValidAmount();
        
        if (amount <= 0) {
            System.out.println("\n   [ERROR] Withdrawal amount must be greater than zero!");
            return;
        }
        
        if (amount > currentBalance) {
            System.out.println("\n   [ERROR] Insufficient balance!");
            System.out.println("   Your current balance is: $" + String.format("%.2f", currentBalance));
            return;
        }
        
        if (bankService.withdraw(accountNumber, amount)) {
            System.out.println("\n   [SUCCESS] Withdrawal Successful!");
            System.out.println("   =========================================");
            System.out.println("   Withdrawn Amount : $" + String.format("%.2f", amount));
            System.out.println("   Previous Balance : $" + String.format("%.2f", currentBalance));
            System.out.println("   New Balance      : $" + String.format("%.2f", bankService.getBalance(accountNumber)));
            System.out.println("   =========================================");
        } else {
            System.out.println("\n   [ERROR] Withdrawal failed! Please try again.");
        }
    }
    
    private void checkBalance() {
        displayDivider();
        System.out.println("          CHECK ACCOUNT BALANCE");
        displayDivider();
        
        System.out.print("\n   Enter Account Number: ");
        String accountNumber = scanner.nextLine().trim().toUpperCase();
        
        if (!bankService.accountExists(accountNumber)) {
            System.out.println("\n   [ERROR] Account not found! Please check the account number.");
            return;
        }
        
        double balance = bankService.getBalance(accountNumber);
        BankAccount account = bankService.getAccount(accountNumber);
        
        System.out.println("\n   +-------------------------------------+");
        System.out.println("   |         ACCOUNT BALANCE             |");
        System.out.println("   +-------------------------------------+");
        System.out.printf("   |  Account: %-25s |\n", accountNumber);
        System.out.printf("   |  Holder : %-25s |\n", account.getAccountHolderName());
        System.out.printf("   |  Balance: $%-24.2f |\n", balance);
        System.out.println("   +-------------------------------------+");
    }
    
    private void viewAccountDetails() {
        displayDivider();
        System.out.println("          VIEW ACCOUNT DETAILS");
        displayDivider();
        
        System.out.print("\n   Enter Account Number: ");
        String accountNumber = scanner.nextLine().trim().toUpperCase();
        
        BankAccount account = bankService.getAccount(accountNumber);
        
        if (account == null) {
            System.out.println("\n   [ERROR] Account not found! Please check the account number.");
            return;
        }
        
        System.out.println(account.toString());
    }
    
    private void exitApplication() {
        displayDivider();
        System.out.println("           EXIT APPLICATION");
        displayDivider();
        
        System.out.print("\n   Are you sure you want to exit? (Y/N): ");
        String choice = scanner.nextLine().trim().toUpperCase();
        
        if (choice.equals("Y") || choice.equals("YES")) {
            isRunning = false;
            System.out.println("\n+===============================================================+");
            System.out.println("|                                                               |");
            System.out.println("|         Thank you for using TransX Banking System!           |");
            System.out.println("|                                                               |");
            System.out.println("|            Your trust is our greatest asset.                 |");
            System.out.println("|                                                               |");
            System.out.println("|                    Have a great day!                         |");
            System.out.println("|                                                               |");
            System.out.println("+===============================================================+\n");
        } else {
            System.out.println("\n   [INFO] Exit cancelled. Returning to main menu...");
        }
    }
    
    private double getValidAmount() {
        double amount = 0;
        try {
            String input = scanner.nextLine().trim();
            amount = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            return -1;
        }
        return amount;
    }
    
    private int getMenuChoice() {
        int choice = 0;
        try {
            String input = scanner.nextLine().trim();
            choice = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
        return choice;
    }
    
    public void run() {
        displayWelcomeBanner();
        
        while (isRunning) {
            displayMenu();
            int choice = getMenuChoice();
            
            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    depositMoney();
                    break;
                case 3:
                    withdrawMoney();
                    break;
                case 4:
                    checkBalance();
                    break;
                case 5:
                    viewAccountDetails();
                    break;
                case 6:
                    exitApplication();
                    break;
                default:
                    System.out.println("\n   [ERROR] Invalid choice! Please enter a number between 1 and 6.");
                    break;
            }
            
            if (isRunning) {
                System.out.print("\n   Press Enter to continue...");
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }
    
    public static void main(String[] args) {
        BankingApplication app = new BankingApplication();
        app.run();
    }
}
