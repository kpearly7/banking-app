import pandas as pd
from sklearn.ensemble import IsolationForest

# Load dataset
data = pd.read_csv("transactions.csv")

print("Transaction Data:")
print(data.head())

# Convert transaction type
data['TYPE_CODE'] = data['TRANSACTION_TYPE'].map({'DEPOSIT':0,'WITHDRAW':1})

# Transaction frequency per account
data['TX_FREQUENCY'] = data.groupby('ACCOUNT_ID')['ACCOUNT_ID'].transform('count')

# Withdrawal count per account
data['WITHDRAW_COUNT'] = data[data['TRANSACTION_TYPE']=="WITHDRAW"].groupby('ACCOUNT_ID')['ACCOUNT_ID'].transform('count')
data['WITHDRAW_COUNT'] = data['WITHDRAW_COUNT'].fillna(0)

# Features
X = data[['AMOUNT','TYPE_CODE','TX_FREQUENCY','WITHDRAW_COUNT']]

# Train fraud detection model
model = IsolationForest(contamination=0.1)

model.fit(X)

# Predictions
data['fraud_flag'] = model.predict(X)

data['fraud_flag'] = data['fraud_flag'].map({1:"Normal",-1:"Fraud"})

print("\nFraud Detection Results:")
print(data)

print("\nNumber of Fraud Transactions:", (data['fraud_flag']=="Fraud").sum())
