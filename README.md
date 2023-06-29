# PersonalFinance
PersonalFinance is a comprehensive finance management application developed using a Spring Boot backend and a React front end. It allows users to track, manage, and visualize their income, expenses, and investments. The application incorporates a Quartz scheduler for monthly updates and also provides detailed monthly summary reports.

## Features
+ User Registration and Login.
+ Management of incomes, expenses, and investments.
+ Monthly updates to the investments using Quartz scheduler.
+ Detailed monthly summary reports.

## Technologies
### Backend
+ Java
+ Spring Boot
+ Hibernate
+ Quartz Scheduler

### Frontend
+ React.js
+ PrimeReact
+ CSS

## Application Structure
The application is divided into two main parts:

### Backend
+ User: Handles user-related functionalities like registration and login.
+ Income: Responsible for tracking and managing the user's incomes.
+ Expense: Manages the user's expenses.
+ Investment: Handles the user's investments. It uses Quartz scheduler to automatically update the investments once every month.
+ Report: Generates detailed monthly reports of income, expense, and investment.
+ Each of these classes has a corresponding Service and Controller.

### Frontend
The frontend comprises several components:

+ Login/Register Page: Handles user registration and login.
+ Navbar: Helps navigate between different pages.
+ Incomes Page: Allows the user to manage incomes.
+ Expenses Page: Enables the user to manage expenses.
+ Investments Page: Lets the user manage their investments.
+ Monthly Summary Page: Displays a summary of the user's income, expenses, and investments for the month.

## Getting Started

+ Clone the repository

Use the following command to clone the repo:  git clone <repository-link>

### Backend setup

+ Install the required dependencies.
+ Navigate to the root directory of the project and run the application using the following command: mvn spring-boot:run

### Frontend setup

+ Navigate to the frontend directory and install the required dependencies using: npm install
+ To start the application, run: npm start



