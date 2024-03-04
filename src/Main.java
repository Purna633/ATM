import java.util.Scanner;

// User class
class User {
    private String userID;
    private int userPIN;
    private double accountBalance;

    // Constructor
    public User(String userID, int userPIN, double accountBalance) {
        this.userID = userID;
        this.userPIN = userPIN;
        this.accountBalance = accountBalance;
    }

    // Getters and setters
    public String getUserID() {
        return userID;
    }

    public int getUserPIN() {
        return userPIN;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }
}

// ATM class
class ATM {
    private User currentUser; // Current user logged in

    // Constructor
    public ATM() {
        // Initialize ATM
    }

    // Method for user authentication
    public boolean authenticateUser(String userID, int userPIN) {
        // Simulated authentication logic (replace with actual authentication)
        return currentUser != null && currentUser.getUserID().equals(userID) && currentUser.getUserPIN() == userPIN;
    }

    // Method to set current user
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    // Method for checking balance
    public void checkBalance() {
        if (currentUser != null) {
            System.out.println("Your current balance is: $" + currentUser.getAccountBalance());
        } else {
            System.out.println("Please log in first.");
        }
    }

    // Method for withdrawing money
    public void withdraw(double amount) {
        if (currentUser != null) {
            if (amount > 0 && amount <= currentUser.getAccountBalance()) {
                currentUser.setAccountBalance(currentUser.getAccountBalance() - amount);
                System.out.println("Withdrawal successful. Remaining balance: $" + currentUser.getAccountBalance());
            } else {
                System.out.println("Invalid amount or insufficient funds.");
            }
        } else {
            System.out.println("Please log in first.");
        }
    }

    // Method for depositing money
    public void deposit(double amount) {
        if (currentUser != null && amount > 0) {
            currentUser.setAccountBalance(currentUser.getAccountBalance() + amount);
            System.out.println("Deposit successful. New balance: $" + currentUser.getAccountBalance());
        } else {
            System.out.println("Invalid amount or not logged in.");
        }
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        // Create instances of User and ATM classes
        User user1 = new User("123456", 1234, 1000.00);
        ATM atm = new ATM();
        atm.setCurrentUser(user1);

        // Simulate ATM interface
        Scanner scanner = new Scanner(System.in);
        boolean isLoggedIn = false;

        while (!isLoggedIn) {
            System.out.print("Enter User ID: ");
            String userID = scanner.nextLine();
            System.out.print("Enter PIN: ");
            int userPIN = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            if (atm.authenticateUser(userID, userPIN)) {
                isLoggedIn = true;
                System.out.println("Login successful.");
            } else {
                System.out.println("Invalid credentials. Please try again.");
            }
        }

        // After successful login, provide ATM functionalities
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Deposit Money");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    atm.checkBalance();
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    atm.withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    atm.deposit(depositAmount);
                    break;
                case 4:
                    isRunning = false;
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select again.");
            }
        }

        scanner.close();
    }
}
