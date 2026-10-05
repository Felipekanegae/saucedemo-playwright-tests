# SauceDemo Playwright Test Automation

Web test automation project for the SauceDemo application using Playwright with Java.

The project was developed to practice and demonstrate automated testing with Playwright, applying Page Object Model, BDD with Cucumber, test data management with Apache POI, and reusable test automation components.

## Technologies

- Java 11
- Playwright
- Cucumber
- JUnit
- Maven
- Apache POI
- PicoContainer

## Project Structure

The project follows the Page Object Model (POM) pattern and separates the automation into different domains:

- Login
- Products
- Cart
- Checkout

Test data is stored in an Excel file and loaded according to the test case being executed.

## Test Scenarios

The project contains 15 automated test scenarios covering:

- Successful login
- Locked user validation
- Login without required credentials
- Logout
- Product sorting by name
- Product sorting by price
- Add product to cart
- Remove product from cart
- Continue shopping
- Checkout information
- Required checkout information validation
- Checkout overview
- Successful purchase

## Running the Tests

To run the complete regression suite:

```bash
mvn test "-Dtest=LoginTest,ProductsTest,CheckoutTest"
