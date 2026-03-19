import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class FraudAPI {

    public static boolean checkFraud(double amount, String type) {

        try {

            URL url = new URL("http://127.0.0.1:5000/check_fraud");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String jsonInput =
            "{ \"amount\": " + amount +
            ", \"type\": \"" + type +
            "\", \"frequency\": 2, \"withdraw_count\": 1 }";

            OutputStream os = conn.getOutputStream();
            os.write(jsonInput.getBytes());
            os.flush();

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream())
            );

            String response;

            while ((response = br.readLine()) != null) {

                if (response.contains("Fraud")) {
                    return true;
                }
            }

        } catch (Exception e) {
            System.out.println("Fraud check failed.");
        }

        return false;
    }
}