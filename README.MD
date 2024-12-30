# Credit Module Backend Application

## Prerequisites

* Java 17
* Postgresql

## Getting started

### How to run

Credit Module needs postgresql to work. After the project is built with maven, it can be run locally with using LoanServiceApplication.java.

An __init-schema.sql__ file was created to insert sample data into the Customer table. The module will automatically perform these operations when it runs.

The following command can be used to run postgresql via Docker.
```bash
docker run --name myPostgresDb -p 5432:5432 -e POSTGRES_USER=sa -e POSTGRES_PASSWORD=password -e POSTGRES_DB=postgresDB -d postgres
```

## Project Structure

The project was implemented in accordance with the microservice structure by applying clean and hexagonal architectures. Some advanteges;
* Isolation between layers
* Better fault isolation
* It became easier to add different technologies to services
* Independent development between modules


#### Project Modules
* __loan-container:__ that is the root of the all api modules. So includes the main method.
* __loan-application:__ This is where the end user interacts with the module.
* __loan-dataaccess:__ responsible for data operations
* __loan-domain:__ includes core business logic, and consist of 2 modules (loan-application-service and loan-domain-core)
* __loan-security:__ includes authentication
* __common:__ includes base classes for project



![Diagram.png](docs%2FDiagram.png)


#### EndPoints

For authorization use following username and password.
```bash
username: testUser
password: password
```

##### /loans
```bash
{
    "customerId": "123e4567-e89b-42d3-a456-556642440000",
    "amount": 100,
    "interestRate": 0.5,
    "numberOfInstalments": 6
}
```
##### /loans/{customerID}
```bash
http://localhost:8181/loans/123e4567-e89b-42d3-a456-556642440000
```

##### /loans/installment/{loanId}
```bash
http://localhost:8181/loans/installment/9fea8e58-0c4e-4f96-81ef-dc85f88fdf74
```

##### /loans/pay
```bash
{
    "loanId": "9fea8e58-0c4e-4f96-81ef-dc85f88fdf74",
    "amount": "400"
}
```