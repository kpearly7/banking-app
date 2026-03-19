💳 Secure Banking System with AI Fraud Detection

🚀 Overview

A full-stack intelligent banking system that integrates a Java-based GUI, Python Flask API, and Machine Learning fraud detection to provide secure, real-time transaction monitoring.

This system detects suspicious transactions using behavioral anomaly detection and adds an extra layer of security through OTP verification.

---

✨ Key Features

🏦 Core Banking Features

* Deposit and Withdrawal functionality
* Real-time balance checking
* Transaction history (last 5 transactions)
* Oracle Database integration for persistent storage

---

 🤖 AI Fraud Detection

* Real-time fraud detection using **Isolation Forest algorithm**
* Detects anomalies based on:

  * Transaction amount
  * Transaction type (Deposit/Withdraw)
  * Transaction frequency
  * Withdrawal patterns
* Configurable contamination factor for sensitivity tuning

---

🔐 Security Layer

* OTP-based verification for suspicious transactions
* Prevents unauthorized high-value transactions
* Simulated SMS verification system (demo-ready)

---

📊 Data Visualization

* Graphical analysis using bar charts
* Displays:

  * Total deposits vs withdrawals
* Built using JFreeChart

---

🌐 API Integration

* Java application communicates with Python backend via REST API
* Real-time fraud status response
* JSON-based communication

---

🛠️ Tech Stack

 💻 Frontend (GUI)

* Java Swing
* Custom UI styling (Dark theme + modern layout)

---

⚙️ Backend

* Python Flask API
* RESTful architecture

---

 🤖 Machine Learning

* Scikit-learn
* Isolation Forest (Anomaly Detection)

---

🗄️ Database

* Oracle Database Express Edition (XE)
* JDBC (Java Database Connectivity)

---

📊 Visualization

* JFreeChart

---
🔗 Integration

* HTTPURLConnection (Java → Python API)

---

## 🧠 System Architecture

```
Java GUI (Swing)
        ↓
Flask API (Python)
        ↓
ML Model (Isolation Forest)
        ↓
Oracle Database (Storage)
```

---

⚡ How It Works

1. User performs a transaction via GUI
2. Data is sent to Flask API
3. ML model analyzes transaction behavior
4. If anomaly detected:

   * OTP verification is triggered
5. If verified:

   * Transaction is stored in database
6. User can view history + graph analytics

---

🗄️ Database Configuration

This project uses Oracle Database Express Edition (XE).

To run the application, ensure:

1. Install Oracle DB
2. Run database.sql
3. Update DB credentials in code

4. JDBC Driver

Make sure `ojdbc8.jar` is added while compiling and running:

javac -cp ".;ojdbc8.jar" BankingGUIPro.java  
java -cp ".;ojdbc8.jar" BankingGUIPro

---

## 📂 Project Structure


📁 Java/
   ├── BankingGUIPro.java

📁 Python/
   ├── app.py
   ├── transactions.csv
   ├── requirements.txt

📁 Database/
   ├── Pre-configured tables


🚀 Setup Instructions

🔹 Run Python API

🔹 Compile Java: javac -cp ".;jfreechart.jar;jcommon.jar;ojdbc8.jar" BankingGUIPro.java

🔹 Run Application: java -cp ".;jfreechart.jar;jcommon.jar;ojdbc8.jar" BankingGUIPro

---

 🧪 Features Demonstration

✅ Normal Transaction

* Processed instantly
* Marked as "Safe"

⚠️ Suspicious Transaction

* Flagged by ML model
* OTP verification required

---

🔮 Future Enhancements

* Real SMS OTP integration
* Cloud database deployment
* User authentication system
* Advanced ML models
* Web-based UI

---

💡 Highlights

* Combines Software Engineering + Data Science + Security
* Demonstrates real-world fintech application
* Built with scalable architecture

---

👨‍💻 Author

Developed as part of an academic project focusing on:

* Innovation in FinTech
* AI-driven security systems
