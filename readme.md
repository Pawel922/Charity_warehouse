# Charity_warehouse

## About

Try to imagine that you have some goods like old furniture, clothes or toys and you do not need them. 
First, you would not like to throw them into garbage dump, because they can be helpful for someone. 
Secondly, you do not want to put them into containers which are outside home, because you are not sure what would happen with them. 
Charity_warehouse is the application which was designed to support and manage this kind of volunteering. You have to do only 4 steps: 

1. Choose goods which you want to leave,
2. Pack them into plastic bags,
3. Choose institution which you want to support,
4. Order courier to pick up bags.

## Quick configuration
Below you find steps to run application:
1. Download files from GitHub.
2. In file *application.properties* set your own properties **user**, **url**(default name for database is *charity_donation*),
	and **password** to have access to your database.
    ```
    spring.datasource.url=jdbc:mysql://localhost:3306/charity_donation?serverTimezone=UTC
    spring.datasource.username=""
    spring.datasource.password=""
    ```
3. Create a new database. You can use code below.
    ```
    create database charity_donation
    character set utf8mb4
    collate utf8mb4_unicode_ci;
    ```
    Instead using *charity_donation* name, you can use your own name, but you have to change this line in file *application.properties* properly:
    ```
    spring.datasource.url=jdbc:mysql://localhost:3306/charity_donation?serverTimezone=UTC
    ```
4. Create connection with your local server. 
5. After first running, tables for every entity should be automatically created. Run below code in your database console:
    ```
    INSERT INTO roles (id, name) VALUES (NULL, 'ROLE_USER');
    ```
6. Configure your mail server properties in **aplication.properties** file. Below you find example for Gmail SMTP Server:
    ```
    spring.mail.host=smtp.gmail.com
    spring.mail.port=587
    spring.mail.username=username
    spring.mail.password=password
    spring.mail.properties.mail.smtp.auth=true
    spring.mail.properties.mail.smtp.starttls.enable=true
    ```
7. Put your email address which from you will send messages for users in **EmailSenderImpl** class:
    ```
    helper.setFrom("");
    ```
8. Enjoy using application!
