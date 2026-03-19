-- ========================
-- ACCOUNTS TABLE
-- ========================
CREATE TABLE accounts (
    ACCOUNT_ID NUMBER PRIMARY KEY,
    CUSTOMER_ID NUMBER,
    BALANCE NUMBER(12,2),
    ACCOUNT_TYPE VARCHAR2(20)
);

-- Sample Data
INSERT INTO accounts VALUES (101, 1, 5000.00, 'SAVINGS');
INSERT INTO accounts VALUES (102, 2, 8000.00, 'CURRENT');


-- ========================
-- CUSTOMERS TABLE
-- ========================
CREATE TABLE customers (
    CUSTOMER_ID NUMBER PRIMARY KEY,
    NAME VARCHAR2(100) NOT NULL,
    EMAIL VARCHAR2(100),
    PHONE VARCHAR2(15),
    ADDRESS VARCHAR2(200)
);

-- Sample Data
INSERT INTO customers VALUES (1, 'John Doe', 'john@example.com', '9876543210', 'Hyderabad');
INSERT INTO customers VALUES (2, 'Alice Smith', 'alice@example.com', '9123456780', 'Chennai');


-- ========================
-- TRANSACTIONS TABLE
-- ========================
CREATE TABLE transactions (
    TRANSACTION_ID NUMBER PRIMARY KEY,
    ACCOUNT_ID NUMBER,
    TRANSACTION_TYPE VARCHAR2(20),
    AMOUNT NUMBER(12,2),
    TRANSACTION_DATE DATE
);

-- Sample Data
INSERT INTO transactions VALUES (1001, 101, 'DEPOSIT', 2000.00, SYSDATE);
INSERT INTO transactions VALUES (1002, 102, 'WITHDRAW', 1500.00, SYSDATE);