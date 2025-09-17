CREATE TABLE contacts (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          email VARCHAR(255) NOT NULL,
                          phone_number VARCHAR(255) NOT NULL,
                          employees VARCHAR(255) NOT NULL,
                          coc_number VARCHAR(255) NOT NULL,
                          iban_number VARCHAR(255) NOT NULL,
                          description TEXT NOT NULL,
                          budget DOUBLE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;