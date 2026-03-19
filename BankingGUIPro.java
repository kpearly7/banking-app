import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.sql.*;
import java.net.*;
import java.io.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class BankingGUIPro {

    static final String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
    static final String username = "bank_user";
    static final String password = "bank123";

    public static void main(String[] args) {

        JFrame frame = new JFrame("Secure Banking System");
        frame.setSize(720,520);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(20,20,20));
        panel.setLayout(new GridBagLayout());
        panel.setBorder(new EmptyBorder(20,20,20,20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);

        JLabel title = new JLabel("Secure Banking Dashboard");
        title.setFont(new Font("Segoe UI",Font.BOLD,26));
        title.setForeground(new Color(0,255,180));
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridwidth=2;
        panel.add(title,gbc);

        gbc.gridwidth=1;

        JLabel accLabel = new JLabel("Account ID:");
        accLabel.setForeground(Color.WHITE);

        JTextField accField = new JTextField(12);
        styleField(accField);

        gbc.gridx=0; gbc.gridy=1;
        panel.add(accLabel,gbc);

        gbc.gridx=1;
        panel.add(accField,gbc);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setForeground(Color.WHITE);

        JTextField amountField = new JTextField(12);
        styleField(amountField);

        gbc.gridx=0; gbc.gridy=2;
        panel.add(amountLabel,gbc);

        gbc.gridx=1;
        panel.add(amountField,gbc);

        JButton depositBtn = createButton("Deposit",new Color(0,200,83));
        JButton withdrawBtn = createButton("Withdraw",new Color(255,82,82));
        JButton balanceBtn = createButton("Check Balance",new Color(41,121,255));
        JButton historyBtn = createButton("Transaction History",new Color(255,171,0));

        gbc.gridx=0; gbc.gridy=3;
        panel.add(depositBtn,gbc);

        gbc.gridx=1;
        panel.add(withdrawBtn,gbc);

        gbc.gridx=0; gbc.gridy=4;
        panel.add(balanceBtn,gbc);

        gbc.gridx=1;
        panel.add(historyBtn,gbc);

        JTextArea outputArea = new JTextArea(8,40);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Consolas",Font.PLAIN,14));
        outputArea.setBackground(new Color(30,30,30));
        outputArea.setForeground(Color.GREEN);
        outputArea.setBorder(new LineBorder(Color.GRAY));

        JScrollPane scrollPane = new JScrollPane(outputArea);

        gbc.gridx=0; gbc.gridy=5;
        gbc.gridwidth=2;
        panel.add(scrollPane,gbc);

        // DEPOSIT
        depositBtn.addActionListener(e -> {

            try(Connection con = DriverManager.getConnection(url,username,password)){

                int accountId = Integer.parseInt(accField.getText());
                double amount = Double.parseDouble(amountField.getText());

                String fraudResult = callFraudAPI(amount,"DEPOSIT");

                if(fraudResult.contains("Fraud")){

    String otp = generateOTP();

    JOptionPane.showMessageDialog(
        frame,
        "⚠ Suspicious Transaction Detected\n\nVerification Code sent to registered phone.\n\nDemo OTP: "+otp,
        "Security Verification",
        JOptionPane.WARNING_MESSAGE
    );

    String userOTP = JOptionPane.showInputDialog(
        frame,
        "Enter Verification Code:"
    );

    if(userOTP == null || !userOTP.equals(otp)){

        JOptionPane.showMessageDialog(
            frame,
            "Verification Failed\nTransaction Blocked",
            "Security Alert",
            JOptionPane.ERROR_MESSAGE
        );

        return;
    }

    JOptionPane.showMessageDialog(
        frame,
        "Verification Successful\nTransaction Approved",
        "Verified",
        JOptionPane.INFORMATION_MESSAGE
    );
}
                String sql = "INSERT INTO transactions VALUES (transaction_seq.NEXTVAL, ?, 'DEPOSIT', ?, SYSDATE)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1,accountId);
                ps.setDouble(2,amount);
                ps.executeUpdate();

                outputArea.setText("Deposit Successful\n"+fraudResult);

            }catch(Exception ex){
                outputArea.setText(ex.getMessage());
            }

        });

        // WITHDRAW
        withdrawBtn.addActionListener(e -> {

            int confirm = JOptionPane.showConfirmDialog(
            frame,
            "Confirm Withdrawal?",
            "Transaction Confirmation",
            JOptionPane.YES_NO_OPTION);

            if(confirm!=0) return;

            try(Connection con = DriverManager.getConnection(url,username,password)){

                int accountId = Integer.parseInt(accField.getText());
                double amount = Double.parseDouble(amountField.getText());

                String fraudResult = callFraudAPI(amount,"WITHDRAW");

                if(fraudResult.contains("Fraud")){

    String otp = generateOTP();

    JOptionPane.showMessageDialog(
        frame,
        "⚠ Suspicious Withdrawal Detected\n\n"
        +"Verification code sent to registered mobile number.\n"
        +"(Demo OTP: "+otp+")",
        "Security Verification",
        JOptionPane.WARNING_MESSAGE
    );

    String userOTP = JOptionPane.showInputDialog(
        frame,
        "Enter Verification Code:"
    );

    if(userOTP == null || !userOTP.equals(otp)){

        JOptionPane.showMessageDialog(
            frame,
            "Verification Failed\nWithdrawal Blocked",
            "Security Alert",
            JOptionPane.ERROR_MESSAGE
        );

        return;
    }

    JOptionPane.showMessageDialog(
        frame,
        "Verification Successful\nWithdrawal Approved",
        "Verified",
        JOptionPane.INFORMATION_MESSAGE
    );
}
                String sql = "INSERT INTO transactions VALUES (transaction_seq.NEXTVAL, ?, 'WITHDRAW', ?, SYSDATE)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1,accountId);
                ps.setDouble(2,amount);
                ps.executeUpdate();

                outputArea.setText("Withdrawal Successful\n"+fraudResult);

            }catch(Exception ex){
                outputArea.setText(ex.getMessage());
            }

        });

        // BALANCE
        balanceBtn.addActionListener(e -> {

            try(Connection con = DriverManager.getConnection(url,username,password)){

                int accountId = Integer.parseInt(accField.getText());

                PreparedStatement ps = con.prepareStatement(
                "SELECT balance FROM accounts WHERE account_id=?");

                ps.setInt(1,accountId);

                ResultSet rs = ps.executeQuery();

                if(rs.next()){
                    outputArea.setText("Current Balance: ₹ "+rs.getDouble("balance"));
                }

            }catch(Exception ex){
                outputArea.setText(ex.getMessage());
            }

        });

        // HISTORY
        historyBtn.addActionListener(e -> {
double totalDeposit = 0;
double totalWithdraw = 0;

            try(Connection con = DriverManager.getConnection(url,username,password)){

                int accountId = Integer.parseInt(accField.getText());

                PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM transactions WHERE account_id=? ORDER BY transaction_date DESC");

                ps.setInt(1,accountId);

                ResultSet rs = ps.executeQuery();

                StringBuilder history = new StringBuilder("Transaction History\n\n");

                while(rs.next()){

                  while(rs.next()){

    String type = rs.getString("transaction_type");
    double amount = rs.getDouble("amount");

    history.append(type)
    .append(" ₹")
    .append(amount)
    .append("\n");

    if(type.equals("DEPOSIT"))
        totalDeposit += amount;

    if(type.equals("WITHDRAW"))
        totalWithdraw += amount;
}
                }

                outputArea.setText(history.toString());
showGraph(totalDeposit,totalWithdraw);

            }catch(Exception ex){
                outputArea.setText(ex.getMessage());
            }

        });

        frame.add(panel);
        frame.setVisible(true);
    }

    static String callFraudAPI(double amount,String type){

    try{

        URL url = new URL("http://127.0.0.1:5000/check_fraud");

        HttpURLConnection conn=(HttpURLConnection)url.openConnection();

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type","application/json");
        conn.setDoOutput(true);

        String json=
        "{ \"amount\": "+amount+
        ", \"type\": \""+type+
        "\", \"frequency\": 2, \"withdraw_count\": 1 }";

        OutputStream os=conn.getOutputStream();
        os.write(json.getBytes());
        os.flush();

        BufferedReader br=new BufferedReader(
        new InputStreamReader(conn.getInputStream()));

        String response = br.readLine();

        if(response.contains("Fraud"))
            return "Fraudulent Transaction Detected";

        return "Security Check Passed";

    }catch(Exception e){
        return "Fraud API Error";
    }
}    static void styleField(JTextField f){

        f.setBackground(new Color(45,45,45));
        f.setForeground(Color.WHITE);
        f.setCaretColor(Color.WHITE);
        f.setBorder(new LineBorder(Color.GRAY));

    }

    static JButton createButton(String text,Color c){

        JButton b=new JButton(text);
        b.setBackground(c);
        b.setForeground(Color.WHITE);
        b.setFont(new Font("Segoe UI",Font.BOLD,14));
        b.setFocusPainted(false);
        b.setPreferredSize(new Dimension(180,35));

        return b;
    }
static void showGraph(double deposit,double withdraw){

    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    dataset.addValue(deposit,"Amount","Deposits");
    dataset.addValue(withdraw,"Amount","Withdrawals");

    JFreeChart chart = ChartFactory.createBarChart(
            "Spending Analysis",
            "Transaction Type",
            "Amount",
            dataset,
            PlotOrientation.VERTICAL,
            false,true,false);

    ChartFrame frame = new ChartFrame("Spending Graph",chart);

    frame.setSize(500,400);
    frame.setVisible(true);
}

static String generateOTP(){

    int otp = (int)(Math.random()*900000) + 100000;

    return String.valueOf(otp);
}
}