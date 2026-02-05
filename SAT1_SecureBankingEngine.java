class SecureBankingEngine {

    public static void main(String[] args) {
        try {
            BankAccount sa = new SavingsAccount(1001, "Alice", 5000);
            BankAccount ca = new CurrentAccount(2001, "Bob", 10000);

            sa.deposit(2000);
            sa.deposit(1500, "ONLINE");

            sa.withdraw(1000);
            sa.withdraw(500, "ATM");

            ca.deposit(5000);
            ca.withdraw(12000, "BUSINESS PAYMENT");

            System.out.println("Savings Balance: " + sa.getBalance());
            System.out.println("Current Balance: " + ca.getBalance());

            sa.withdraw(100000);

        } catch (InvalidAmountException e) {
            System.out.println("Checked Exception: " + e.getMessage());
        } catch (InsufficientBalanceException e) {
            System.out.println("Runtime Exception: " + e.getMessage());
        } finally {
            System.out.println("Transaction cycle completed.");
        }
    }
}

interface Transaction {
    void deposit(double amt) throws InvalidAmountException;
    void deposit(double amt, String mode) throws InvalidAmountException;
    void withdraw(double amt);
    void withdraw(double amt, String remarks);
}

abstract class BankAccount implements Transaction {

    private long accountNumber;
    private String accountHolder;
    private double balance;

    BankAccount(long accNo, String name, double bal) throws InvalidAmountException {
        if (bal < 0) {
            throw new InvalidAmountException("Initial balance cannot be negative");
        }
        accountNumber = accNo;
        accountHolder = name;
        balance = bal;
    }

    protected void setBalance(double amt) {
        balance = amt;
    }

    public double getBalance() {
        return balance;
    }

    protected void validateAmount(double amt) throws InvalidAmountException {
        if (amt <= 0) {
            throw new InvalidAmountException("Amount must be greater than zero");
        }
    }

    public void deposit(double amt) throws InvalidAmountException {
        validateAmount(amt);
        setBalance(getBalance() + amt);
    }

    public void deposit(double amt, String mode) throws InvalidAmountException {
        validateAmount(amt);
        setBalance(getBalance() + amt);
    }

    public void withdraw(double amt) {
        withdraw(amt, "NO REMARKS");
    }

    public abstract void withdraw(double amt, String remarks);
}

class SavingsAccount extends BankAccount {

    SavingsAccount(long accNo, String name, double bal) throws InvalidAmountException {
        super(accNo, name, bal);
    }

    public void withdraw(double amt, String remarks) {
        if (amt <= 0) {
            throw new InvalidAmountException("Invalid withdrawal amount");
        }
        if (amt > getBalance()) {
            throw new InsufficientBalanceException("Savings account insufficient balance");
        }
        setBalance(getBalance() - amt);
    }
}

class CurrentAccount extends BankAccount {

    private final double overdraftLimit = 5000;

    CurrentAccount(long accNo, String name, double bal) throws InvalidAmountException {
        super(accNo, name, bal);
    }

    public void withdraw(double amt, String remarks) {
        if (amt <= 0) {
            throw new InvalidAmountException("Invalid withdrawal amount");
        }
        if (amt > getBalance() + overdraftLimit) {
            throw new InsufficientBalanceException("Current account overdraft limit exceeded");
        }
        setBalance(getBalance() - amt);
    }
}

class InvalidAmountException extends Exception {
    InvalidAmountException(String msg) {
        super(msg);
    }
}

class InsufficientBalanceException extends RuntimeException {
    InsufficientBalanceException(String msg) {
        super(msg);
    }
}
