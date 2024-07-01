CREATE TABLE tbCategory (
                            categoryId SERIAL PRIMARY KEY,
                            categoryName VARCHAR(255) NOT NULL
);

CREATE TABLE tbProduct (
                           productId SERIAL PRIMARY KEY,
                           productName VARCHAR(255) NOT NULL,
                           image VARCHAR(255),
                           description TEXT,
                           price DECIMAL(10, 2) NOT NULL,
                           stockQTY INT NOT NULL,
                           categoryId INT,
                           FOREIGN KEY (categoryId) REFERENCES tbCategory(categoryId)
);

CREATE TABLE tbUser (
                        userId SERIAL PRIMARY KEY,
                        username VARCHAR(255) NOT NULL,
                        phoneNumber VARCHAR(20),
                        password VARCHAR(255) NOT NULL
);

CREATE TABLE tbOrder (
                         orderId SERIAL PRIMARY KEY,
                         userId INT,
                         FOREIGN KEY (userId) REFERENCES tbUser(userId),
                         orderDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         totalAmount DOUBLE PRECISION,
                         isPaid BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE tbOrderItem (
                             orderItemId SERIAL PRIMARY KEY,
                             orderId INT,
                             FOREIGN KEY (orderId) REFERENCES tbOrder(orderId),
                             productId INT,
                             FOREIGN KEY (productId) REFERENCES tbProduct(productId),
                             quantity INT
);
