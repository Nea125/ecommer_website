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

CREATE  TABLE  tbOrder(
       orderId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
       userId INT,
       FOREIGN KEY (userId) REFERENCES tbUser(userId),
       orderDate timestamp DEFAULT now(),
       totalAmount double,
       isPaid BIT NOT NULL DEFAULT 0
);

CREATE TABLE  tbOrderItem(
     orderItemId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
     orderId INT ,
     FOREIGN KEY (orderId) REFERENCES tbOrder(orderId),
     productId INT,FOREIGN KEY (productId) REFERENCES  tbproduct(productId),
     quantity int
)






