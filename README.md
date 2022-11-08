# CS203 G1-T2 Marketplace App Backend
For more information, our project documentation can be found on this <a href="https://docs.google.com/document/d/1CESnTTPve_ZE6EioaL1GUpCG75fNIAIBd19TlhVWrRQ/edit#">link</a>.


## Get Started (Setting Up Locally)

### Run the following SQL statement in MySQL Workbench (only needs to be done once)
```
CREATE SCHEMA cs203dbv1 ;
```

### Configure Spring Datasource, JPA, App properties
Open `src/main/resources/application.properties`.
Enter your MySQL Workbench credentials into the fields `spring.datasource.username=` and `spring.datasource.password=`.

```properties
spring.datasource.url= jdbc:mysql://localhost:3306/cs203db
spring.datasource.username= root
spring.datasource.password= password

spring.jpa.properties.hibernate.dialect= org.hibernate.dialect.MySQL5InnoDBDialect
spring.jpa.hibernate.ddl-auto= update

# App Properties
cs203g1t2.app.jwtSecret= cs203g1t2SecretKey
cs203g1t2.app.jwtExpirationMs= 3600000
cs203g1t2.app.jwtRefreshExpirationMs= 86400000
```

### Run Spring Boot Application in the terminal (to create the database tables)
```
mvn spring-boot:run
```

### After running the application for the first time, terminate the application by entering the following command (only needs to be done once)
```
Control + c
```

### Run the following SQL INSERT Statements in MySQL Workbench (only needs to be done once)
```
INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');
```

### Run the following SQL INSERT Statements in MySQL Workbench (only needs to be done once) - to store some dummy items in the database created
```
INSERT INTO item (id, brand, description, expiry_date, item_name, price, quantity, type, url, carbon, originalprice, location) VALUES ('1', 'Marketside', 'Juicy Yellow Watermelon', '21-12-2022', 'Yellow Watermelon', '2.50', '100', 'Fruit', 'https://images.heb.com/is/image/HEBGrocery/000320934', '0.50', '5.00', 'Fairprice Bukit Merah Central');
INSERT INTO item (id, brand, description, expiry_date, item_name, price, quantity, type, url, carbon, originalprice, location) VALUES ('2','Fiji','Quench your thirst with this','12-11-2022','Fiji Water','1.00','50','Drinks','https://m.media-amazon.com/images/I/71Czo7JaViL._AC_SY879_.jpg','2.50','4.00','FairPrice Northpoint City');
INSERT INTO item (id, brand, description, expiry_date, item_name, price, quantity, type, url, carbon, originalprice, location) VALUES ('3','Sunsweet','Tasty Prunes','10-11-2022','Dried Pitted Prunes','2.40','50','Healthy Snacks','https://5.imimg.com/data5/SELLER/Default/2020/9/DC/PG/WK/2935824/sunsweet-prunes-500x500.PNG','2.00','4.30','Giant Supermarket - Redhill');
INSERT INTO item (id, brand, description, expiry_date, item_name, price, quantity, type, url, carbon, originalprice, location) VALUES ('4','Nescafe','Do you need stay awake?','11-11-2022','3-In-1 Coffee','3.60','50','Drinks','https://m.media-amazon.com/images/I/81jJ3KsaFuL.jpg','4.50','10.00','Sheng Siong Supermarket @ Dawson');
INSERT INTO item (id, brand, description, expiry_date, item_name, price, quantity, type, url, carbon, originalprice, location) VALUES ('5','Lays Chips','Crunchy Chips','20-11-2022','Sour Cream Chips','1.90','50','Snacks','https://vendorcontentportal.s3.ap-southeast-1.amazonaws.com/product/5006421_01_5006421.jpg','1.50','3.10','Valu$ - Tiong Bahru Plaza');
INSERT INTO item (id, brand, description, expiry_date, item_name, price, quantity, type, url, carbon, originalprice, location) VALUES ('6','Ruffles','Face Of Ruffes','02-12-2022','Sour Cream & Onion Flavored Potato Chips','1.80','50','Snacks','https://www.ruffles.com/sites/ruffles.com/files/2019-06/SOUR%20CREAM%20%26%20ONION.png','1.50','3.00','FairPrice Finest Artra');
INSERT INTO item (id, brand, description, expiry_date, item_name, price, quantity, type, url, carbon, originalprice, location) VALUES ('7','Marketside','Small carrots','09-11-2022','Baby Carrots','2.30','50','Vegetable','https://i5.walmartimages.com/asr/a92791be-24f5-4466-8b8b-dd0adf97d706_3.9e4ebf22aea099a343a03a82ac93be29.jpeg?odnHeight=612&odnWidth=612&odnBg=FFFFFF','0.50','4.20','Kranji Countryside Farmers Market');
```

### Run the following SQL INSERT Statements in MySQL Workbench (only needs to be done once) - To store some dummy users in the database created
```
INSERT INTO users (id, answer, carbonsaved, email, moneysaved, password, username) VALUES ('1', 'chocolate cake', '21.20', 'adam@gmail.com', '36.69', '$2a$10$n8wzJU1Q/yD1CISnEfb08eYHKgx2IjifDRYiaLSQZlFSWNN8O7NNe', 'adam');
INSERT INTO users (id, answer, carbonsaved, email, moneysaved, password, username) VALUES ('2', 'carbonara', '11.10', 'jasjyot@gmail.com', '23.22', '$2a$10$adhYMdA9yvqRqMpdLyScJOQxNokAvh2GbVFdtN0FvBeDRL2iE3q1.', 'jasjyot');
INSERT INTO users (id, answer, carbonsaved, email, moneysaved, password, username) VALUES ('3', 'sashimi', '14.66', 'rish@gmail.com', '27.30', '$2a$10$DB/kM97SiDu1pmKIeqo4ZOZTMZzCGxRlpMM9pNtlYwXXmPGdKMIFa', 'rish');
INSERT INTO users (id, answer, carbonsaved, email, moneysaved, password, username) VALUES ('4', 'sweets', '8.65', 'alibinsalleh@gmail.com', '20.05', '$2a$10$.TZRUi599khxxjefNycGVucTcaNidXs8.fPcWBotscc7vQ8b420rm', 'alilovestoeat');
INSERT INTO users (id, answer, carbonsaved, email, moneysaved, password, username) VALUES ('5', 'ice', '7.55', 'garylim@gmail.com', '19.50', '$2a$10$iIlSbcLB2a2N8ETEFagMYuXO7f6vU8nwhMMhtyJzzxJ2xKKv29D3y', 'garytanjj');
```


### The database has now been configured. You can now run the Spring Boot Application anytime with the following command
```
mvn spring-boot:run
```

### To run the client-side (Frontend) of our application on your localhost, you can follow the steps as listed in the link below
https://github.com/CS203-G1-T2/frontend#readme

### Using our application
To test and play around with the application, I recommend using our interface at http://localhost:3000 after following the previous step above. Alternatively, you may choose to test our endpoints using RestClient.http.

### Contact
If you encounter any issues, feel free to contact us at austen.leow.2021@smu.edu.sg.
