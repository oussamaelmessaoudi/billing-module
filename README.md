# Billing Module - Internship Challenge (AriMayi)

Submission for the Backend - Module de Facturation Challenge.
A REST API built with Java 17 and Spring Boot 3 to manage clients, create invoices and calculate totals.


## Project Objective
Provide a backend billing module that supports:
- Create and manage clients
- Create invoices with detailed lines
- Automatic calculationof totals (HT, TVA, TTC)
- Export a complete invoice as structured JSON

## Context
This module simulates a billing system for a project management platform similar to AriMayi.
Each client can recieve invoices for services. The API is designed for easy integration into a larger system.

## Features


### Client Management
- List all clients
- Create a client (name, email, SIRET, creation date)
- view client details
- Delete or update a client (admin only)

### Invoice Management
- List all invoices with filters (by date, client ...)
- Create an invoice linked to a client
- Add invoice lines (description, quantity, unit price HT, TVA rate)
- Auto calculate totals: HT, TVA, TTC

### Business Rules
- An invoice must have at least one line
- No field can be empty
- Allowed TVA rates : 0%, 5.5%, 10%, 20%

### Bonus
- Basic authentication with Spring Security (in-memory users and roles)
- Unit test for `InvoiceService.createInvoice()` with JUnit and Mockito
- Search endpoint to filter invoices by client or date


## Tech Stack
| Layer       | Technology         |
|-------------|--------------------|
| Language    | Java 17           |
| Framework   | Spring Boot 3.x   |
| Database    | H2 (in-memory) for test and PostgreSQL    |
| Security    | Spring Security   |
| Docs        | Swagger UI        |
| Testing     | JUnit 5 + Mockito |
| Architecture| MVC               |


## Project Structure

```
src/
 ├── main/java/com/example/billingmodule/
 │   ├── controller/
 │   ├── service/
 │   ├── entity/
 │   ├── dto/
 │   ├── mapper/
 │   ├── exception/
 │   ├── repository/
 │   └── config/
 └── resources/
 └── test/java/com/example/billingmodule/
```

## Author
Oussama ELMESSAOUDI

Backend Developer focused on clean architecture and validation logic.

## Contact 
For questions or feedback, reach me out through:

Email: oussamaelmessaoudi17@gmail.com

[LinkedIn](https://linkedin.com/in/usama-elmessaoudi)
