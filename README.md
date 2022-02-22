<!-- ABOUT THE PROJECT --> 

## About Creating Credit Card Demo Application 

 > This application provides a set of REST APIs to create a new credit card entry and to fetch all existing credit cards in the system, from the MySql database.



<!-- ABOUT THE PROJECT --> 
## Built with 
- Java Spring Framework : Spring Boot
- Programming Language : Java
- Web Server : Apache Tomcat
- Build Tool : Maven
 

<!-- Prerequisites --> 
## Prerequisites 

> Please note windows machines cannot be used for running the iOS app

Windows:

- Spring Tool Suite 4: https://spring.io/tools
- MySql Workbench 8: https://dev.mysql.com/downloads/mysql/


<!-- USAGE EXAMPLES --> 

## Usage 

#### API: /creditcards/add This is a POST api which is used to create a new Credit Card Account. 
#### Consumes
```yaml
JSON Payload:
{
"name" : "AAAA" "cardNumber": "XXXXXXXXXXXXXXXX",
"limit" : Z
}
```

-A is a alphame
X is an numeric characters of card number, Z is the digits of initial balance.
Intial balance will be be 0 at the begining.

#### Produces
```yaml
JSON:
{
    "userId": "YYYYYYYYYYYYYYYYYYYYYYYYY",
    "firstName": "AAAA",
    "lastName": "BBBB",
    "balance": 0
}
```
- userId ia a generated unique public user Id
- A is alphabets of first name
- B is alphabets of last name
- With a minimum balance of 0 in the created user entry.


```
```yaml
API: /accounts/getAll
This is a GET api which is used to get all the Credit Card Accounts.
Produces: JSON
List of Credit Card Accounts:
[
{
"userId": "AAAAAAA",
"firstName": "XXXXXXXXXXXXXXX",
"lastName": Y,
"balance": ZZZZZZ
},
{
"userId": "AAAAAAA",
"firstName": "XXXXXXXXXXXXXXX",
"lastName": Y,
"balance": ZZZZZZ
}
]
where A is unique generated public user Id, X is alphabets in name, Y is the alphabets in last name, Z is the digits of initial balance.
```

###### Validations:

Request must have name, card number and limit. If violated, Bad Request.
Credit Card Number can only have digits. If violated, BadRequest.
The minimum balance in the account is 0. It will be taken 0 by default.
Credit card number can have maximum 19 digits. If violated, BadRequest.
Credit card number will be validated against Luhn algorithm. If violated, BadRequest.
