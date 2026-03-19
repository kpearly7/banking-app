import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BankDBConnection {

    public static void main(String[] args) {

        String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
        String username = "bank_user";
        String password = "bank123";

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("✅ Connected to Oracle Database Successfully!");
            con.close();
        } catch (SQLException e) {
            System.out.println("❌ Connection Failed");
            e.printStackTrace();
        }
    }
}