💳 Secure Banking System with AI Fraud Detection
🚀 Overview

A full-stack intelligent banking system that integrates a Java-based GUI, Python Flask API, and Machine Learning-based fraud detection to provide secure, real-time transaction monitoring.

The system detects suspicious transactions using behavioral anomaly detection and adds an extra security layer through OTP verification.

✨ Key Features
🏦 Core Banking Features

Deposit and withdrawal functionality

Real-time balance checking

Transaction history (last 5 transactions)

Oracle Database integration for persistent storage

🤖 AI Fraud Detection

Real-time fraud detection using Isolation Forest algorithm

Detects anomalies based on:

Transaction amount

Transaction type (Deposit/Withdraw)

Transaction frequency

Withdrawal patterns

Configurable contamination factor for sensitivity tuning

🔐 Security Layer

OTP-based verification for suspicious transactions

Prevents unauthorized high-value transactions

Simulated SMS verification system (demo-ready)

📊 Data Visualization

Graphical analysis using bar charts

Displays:

Total deposits vs withdrawals

Built using JFreeChart

🌐 API Integration

Java application communicates with Python backend via REST API

Real-time fraud status response

JSON-based communication

🧠 System Architecture
Java GUI (Swing)
        ↓
Flask API (Python)
        ↓
Machine Learning Model (Isolation Forest)
        ↓
Oracle Database (Storage)
⚡ How It Works

User performs a transaction via the GUI

Data is sent to the Flask API

The ML model analyzes transaction behavior

If anomaly is detected:

OTP verification is triggered

If verified:

Transaction is stored in the database

User can view transaction history and analytics

🛠️ Tech Stack
Frontend (GUI)

Java Swing

Custom UI (Dark theme)

Backend

Python Flask (REST API)

Machine Learning

Scikit-learn

Isolation Forest (Anomaly Detection)

Database

Oracle Database Express Edition (XE)

JDBC (Java Database Connectivity)

Visualization

JFreeChart

Integration

HTTPURLConnection (Java ↔ Python API)

🗄️ Database Configuration

This project uses Oracle Database Express Edition (XE).

Steps to Run:

Install Oracle Database

Execute database.sql

Update database credentials in the code

JDBC Driver Setup

Ensure ojdbc8.jar is added while compiling and running:

javac -cp ".;ojdbc8.jar" BankingGUIPro.java  
java -cp ".;ojdbc8.jar" BankingGUIPro
📂 Project Structure
📁 Java/
   └── BankingGUIPro.java

📁 Python/
   ├── app.py
   ├── transactions.csv
   └── requirements.txt

📁 Database/
   └── Pre-configured tables
🚀 Setup Instructions
1. Run Python API
pip install -r requirements.txt
python app.py
2. Compile Java Application
javac -cp ".;jfreechart.jar;jcommon.jar;ojdbc8.jar" BankingGUIPro.java
3. Run Java Application
java -cp ".;jfreechart.jar;jcommon.jar;ojdbc8.jar" BankingGUIPro
🧪 Features Demonstration
Normal Transaction

Processed instantly

Marked as Safe

Suspicious Transaction

Flagged by ML model

OTP verification required

🔮 Future Enhancements

Real SMS OTP integration

Cloud database deployment

User authentication system

Advanced ML models

Web-based user interface

💡 Highlights

Combines Software Engineering + Data Science + Security

Demonstrates a real-world fintech application

Built with a scalable and modular architecture

👨‍💻 Author

Developed as part of an academic project focusing on:

Innovation in FinTech

AI-driven security systems
