CREATE TABLE tbCategory (
                            categoryId INT NOT NULL AUTO_INCREMENT,
                            categoryName VARCHAR(255) NOT NULL,
                            PRIMARY KEY (categoryId)
);
CREATE TABLE tbProduct (
                           productId INT NOT NULL AUTO_INCREMENT,
                           productName VARCHAR(255) NOT NULL,
                           image VARCHAR(255),
                           description TEXT,
                           price DECIMAL(10, 2) NOT NULL,
                           stockQTY INT NOT NULL,
                           categoryId INT,
                           PRIMARY KEY (productId),
                           FOREIGN KEY (categoryId) REFERENCES tbCategory(categoryId)
);

CREATE TABLE tbUser (
                        userId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                        username VARCHAR(255) NOT NULL,
                        phoneNumber VARCHAR(20),
                        password VARCHAR(255) NOT NULL
);

