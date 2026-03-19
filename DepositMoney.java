import java.sql.*;
import java.util.Scanner;

public class DepositMoney {

    public static void main(String[] args) {

        String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
        String username = "bank_user";
        String password = "bank123";

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Account ID: ");
        int accountId = sc.nextInt();

        System.out.print("Enter Amount to Deposit: ");
        double amount = sc.nextDouble();

        try (Connection con = DriverManager.getConnection(url, username, password)) {

            String sql = "INSERT INTO transactions VALUES (transaction_seq.NEXTVAL, ?, 'DEPOSIT', ?, SYSDATE)";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, accountId);
            ps.setDouble(2, amount);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("✅ Deposit Successful!");
            }

        } catch (SQLException e) {
            System.out.println("❌ Transaction Failed");
            e.printStackTrace();
        }

        sc.close();
    }
}