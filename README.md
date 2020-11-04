# TransactionsSpringbootServer
Simple and dummy REST Springboot server for banking transactions (ING Tech Romania homework) based on bezkoder github example.
Technologies used: Java, Springboot, MongoDB, Jwt auth, Maven

## Prerequisite (no docker version)
Install mongoDB client
Create role db:

```bash
use clark_db;
db.roles.insertMany([
   { name: "ROLE_USER" },
   { name: "ROLE_MODERATOR" },
   { name: "ROLE_ADMIN" },
   { name: "ROLE_GLASSES" },
])
```


## Installation

Use maven to build and run the project:

```bash
mvn spring-boot:run
```

## Usage
In order to test the provided API you can import and run ING_API_endpoints.json file in POSTMAN
```
SIGNUP:
POST /api/auth/signup
eg body content: 
{	"username":"costin15",
	"email":"costin@ing.com",
	"roles":["user"],
	"password":"dummyPass"
}

SIGNIN:
POST /api/auth/signin
eg body content:
{
	"username":"costincostea2",
	"password":"coco123"
}

DELETE ACCOUNT: 
DELETE /api/test/account
auth: bearer token (provided from signin)
header: username


RETRIEVE TRANSACTIONS
GET /api/test/transactions 
auth: bearer token (provided from signin)
query params: senderUsername (String), startDate(yyyy-MM-dd), endDate (yyyy-MM-dd)

REGISTER A NEW TRANSACTION
POST /api/test/transactions
auth:bearer token (provided from signin)
eg body content:
{
    "id":"1213334",
    "date":"2020-10-29",
    "senderUser":"costin15",
    "destinationUser":"costin",
    "amount":90.2
}

```

## TODO:
ADD International currency, Implement docker build pipeline, Comment code, Write some tests for API.


## Contributing
Thanks https://github.com/bezkoder/spring-boot-security-jwt-auth-mongodb. 

## License
[MIT](https://choosealicense.com/licenses/mit/)
