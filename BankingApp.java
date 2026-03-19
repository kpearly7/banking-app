import java.sql.*;
import java.util.Scanner;

public class BankingApp {

    static final String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
    static final String username = "bank_user";
    static final String password = "bank123";

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n==== BANKING SYSTEM ====");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Transaction History");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> deposit();
                case 2 -> withdraw();
                case 3 -> checkBalance();
                case 4 -> transactionHistory();
                case 5 -> {
                    System.out.println("Thank you!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    static void deposit() {
        try (Connection con = DriverManager.getConnection(url, username, password)) {

            System.out.print("Enter Account ID: ");
            int accountId = sc.nextInt();

            System.out.print("Enter Amount: ");
            double amount = sc.nextDouble();

            String sql = "INSERT INTO transactions VALUES (transaction_seq.NEXTVAL, ?, 'DEPOSIT', ?, SYSDATE)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, accountId);
            ps.setDouble(2, amount);

            boolean fraud = FraudAPI.checkFraud(amount, "DEPOSIT");

if (fraud) {
    System.out.println("⚠ FRAUD DETECTED — Transaction Blocked!");
    return;
}

ps.executeUpdate();
System.out.println("✅ Deposit Successful!");
        } catch (SQLException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    static void withdraw() {
        try (Connection con = DriverManager.getConnection(url, username, password)) {

            System.out.print("Enter Account ID: ");
            int accountId = sc.nextInt();

            System.out.print("Enter Amount: ");
            double amount = sc.nextDouble();

            String sql = "INSERT INTO transactions VALUES (transaction_seq.NEXTVAL, ?, 'WITHDRAW', ?, SYSDATE)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, accountId);
            ps.setDouble(2, amount);

            boolean fraud = FraudAPI.checkFraud(amount, "WITHDRAW");

if (fraud) {
    System.out.println("⚠ FRAUD DETECTED — Transaction Blocked!");
    return;
}

ps.executeUpdate();
System.out.println("✅ Withdrawal Successful!");
        } catch (SQLException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    static void checkBalance() {
        try (Connection con = DriverManager.getConnection(url, username, password)) {

            System.out.print("Enter Account ID: ");
            int accountId = sc.nextInt();

            String sql = "SELECT balance FROM accounts WHERE account_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, accountId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("💰 Current Balance: " + rs.getDouble("balance"));
            } else {
                System.out.println("Account not found!");
            }

        } catch (SQLException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    static void transactionHistory() {
        try (Connection con = DriverManager.getConnection(url, username, password)) {

            System.out.print("Enter Account ID: ");
            int accountId = sc.nextInt();

            String sql = "SELECT * FROM transactions WHERE account_id = ? ORDER BY transaction_date DESC";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, accountId);

            ResultSet rs = ps.executeQuery();

            System.out.println("\n--- Transaction History ---");
            while (rs.next()) {
                System.out.println(
                        "ID: " + rs.getInt("transaction_id") +
                        " | Type: " + rs.getString("transaction_type") +
                        " | Amount: " + rs.getDouble("amount") +
                        " | Date: " + rs.getDate("transaction_date")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}