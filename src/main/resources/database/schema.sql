CREATE TABLE IF NOT EXISTS account(id INT PRIMARY KEY AUTO_INCREMENT, ts TIMESTAMP AS CURRENT_TIMESTAMP(), name VARCHAR UNIQUE, balance DECIMAL );

CREATE TABLE IF NOT EXISTS payee(id INT PRIMARY KEY AUTO_INCREMENT, ts TIMESTAMP AS CURRENT_TIMESTAMP(), name VARCHAR UNIQUE, balance DECIMAL );