from flask import Flask, request, jsonify
import pandas as pd
from sklearn.ensemble import IsolationForest

app = Flask(__name__)

# Load dataset
data = pd.read_csv("D:/IP/python/transactions.csv")

# Feature engineering
data['TYPE_CODE'] = data['TRANSACTION_TYPE'].map({'DEPOSIT':0,'WITHDRAW':1})
data['TX_FREQUENCY'] = data.groupby('ACCOUNT_ID')['ACCOUNT_ID'].transform('count')
data['WITHDRAW_COUNT'] = data[data['TRANSACTION_TYPE']=="WITHDRAW"].groupby('ACCOUNT_ID')['ACCOUNT_ID'].transform('count')
data['WITHDRAW_COUNT'] = data['WITHDRAW_COUNT'].fillna(0)

# Train model
X = data[['AMOUNT','TYPE_CODE','TX_FREQUENCY','WITHDRAW_COUNT']]
model = IsolationForest(contamination=0.02)
model.fit(X)


@app.route('/check_fraud', methods=['POST'])
def check_fraud():

    transaction = request.json

    amount = transaction['amount']
    type_code = 0 if transaction['type']=="DEPOSIT" else 1
    freq = transaction['frequency']
    withdraw_count = transaction['withdraw_count']

    test_data = [[amount,type_code,freq,withdraw_count]]

    prediction = model.predict(test_data)

    result = "Fraud" if prediction[0] == -1 else "Normal"

    return jsonify({"fraud_status": result})


if __name__ == '__main__':
    app.run(port=5000)