CREATE TABLE IF NOT EXISTS account (
  id      INT PRIMARY KEY AUTO_INCREMENT,
  name    VARCHAR UNIQUE,
  balance DECIMAL
);