# CS203 G1-T2 Marketplace App Backend
For more information, our project documentation can be found on this <a href="https://docs.google.com/document/d/1CESnTTPve_ZE6EioaL1GUpCG75fNIAIBd19TlhVWrRQ/edit#">link</a>.


## Get Started (Setting Up Locally)

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

### Run Spring Boot application in the terminal
```
mvn spring-boot:run
```

### Run the following SQL insert statements in MySQL Workbench (only needs to be done once)
```
INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');
```

### Contact
If you encounter any issues, feel free to email us at austen.leow.2021@scis.smu.edu.sg