# Credit Module Backend Application

## Prerequisites

* Java 17
* Docker

## Getting started

### How to run

### Locally
After the project is built with maven, it can be run locally with using LoanServiceApplication.java.


### Docker
After the __loan-container__ module is successfully built, it creates the docker image itself.

The following command can be used to run application via Docker.
```bash
docker run --name loanService -p 8181:8181 -d com.credit.module/loan.service:1.0.0-SNAPSHOT
```

### HINTS
- Credit Module uses inmemory H2 Database to work.
- A file __(data.sql)__ was created to insert sample customer data into the Customer table. The module will automatically perform these operations when it runs.
- When building the __loan-container__ module, the Java version of the project and the Java version used by Maven must be the same wich 
- If you want to disable security, the following dependency in the loan-container module can be removed.
```bash
        <dependency>
            <groupId>com.credit.module</groupId>
            <artifactId>loan-security</artifactId>
        </dependency>
```

## Project Structure

The project was implemented in accordance with the microservice structure by applying clean and hexagonal architectures. Some advanteges;
* Isolation between layers
* Better fault isolation
* It became easier to add different technologies to services
* Independent development between modules


#### Project Modules
* __loan-container:__ that is the root of the all api modules and creates Docker image So includes the main method.
* __loan-application:__ This is where the end user interacts with the module.
* __loan-dataaccess:__ responsible for data operations
* __loan-domain:__ includes core business logic, and consist of 2 modules
  * __loan-application-service:__ Service layer that provides external communication of the core module and business management.
  * __loan-domain-core:__ This module must be stable throughout its lifetime, because it contains core business. Therefore, there is no dependency on any third-party module. For example, builder methods were written manually.
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