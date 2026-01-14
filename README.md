# Simple Banking System

A console-based banking application built with **Core Java** demonstrating **Object-Oriented Programming (OOP)** principles.

## Features

- Create Account - Create a new bank account with holder name and optional initial deposit
- Deposit Money - Deposit funds into an existing account
- Withdraw Money - Withdraw funds with balance validation
- Check Balance - View current account balance
- View Account Details - Display complete account information
- Safe Exit - Gracefully exit the application

## Project Structure

```
Banking console/
├── src/
│   ├── BankAccount.java        # Entity class
│   ├── BankService.java        # Service class
│   └── BankingApplication.java # Main application
└── README.md
```

## OOP Concepts Demonstrated

1. **Encapsulation** - Private fields with public getters/setters
2. **Classes & Objects** - Three separate classes with distinct responsibilities
3. **Constructors** - Default and parameterized constructors
4. **Methods** - Instance methods for banking operations
5. **Collections** - HashMap for account storage

## How to Run

```bash
cd "Banking console/src"
javac *.java
java BankingApplication
```

## Author

Created for academic Java project demonstration.
