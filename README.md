# Pressing Management
Generic Pressing Management system for small and medium size businesses

## Main developers: **`Team SmartFinance`**

## Database configuration (file: application.properties)
* Create a database by default we use 'pressing'
* spring.datasource.url=jdbc:mysql://localhost:3306/{databasename}
* spring.datasource.username={database username}
* spring.datasource.password={database password}
* spring.datasource.data-username={database username}
* spring.datasource.data-password={database password}

**N.B**: Just having an empty database called 'pressing' is enough. 
Default db username: root
Default db password: mysql 

## Demo Login
* Username: Admin
* Password: password

## What works:
* Authentication and authorization
* View all users
* Find users by id
* Create and update  users
* View all customers
* Find customer by id
* Create and update customer
* View categories
* Find category by id
* Create and update category
* View all expenditures
* Find expenditure by id
* Create and update expenditure record
* View all payment methods
* Find payment method by id
* Create and update payment method
* View all cleaning materials
* Find cleaning material by id
* Create and update cleaning material
* Find item by name
* Find cleaning material by name
* Find category by name
* Find customer by email
* View all reservation made by customers for an item
* Find a reservation by customer name or by item name
* Find reservation by due date it by deposit date
* Get all payments made by a customer for n item
* View all roles and permission
* De-activation of any record in all accounts
* View user's roles
* View permissions belong to a role

## What doesn't:
* Unit test
* Automatic run of SQL script



## IMPROVEMENTS:

* Unit tests
* Optimization techniques
