# Travel Expense Manager

## Overview

The Travel Expense Manager is a Java-based application that helps users manage their travel expenses. Users can add trips, track expenses for each trip, and compare the total expenses against the initial budget.

## Features

- Add and delete trips
- Add and delete expenses for each trip
- Display total expenses and remaining budget
- Check if the expenses are within the initial budget

## Project Structure

- `src/model/`: Contains the data model classes such as `Trip`, `User`, `Expense`, and `BudgetComparator`.
- `src/gui/`: Contains the graphical user interface classes such as `MainWindow`, `TripPanel`, `ExpensePanel`, and `BudgetPanel`.

## Classes

### `Trip`

Represents a trip with a unique ID, destination, user, and a list of expenses.

### `User`

Represents a user with a name and email.

### `Expense`

Represents an expense with a description and amount.

### `BudgetComparator`

Compares the total expenses of a trip with the initial budget.

### `MainWindow`

The main window of the application that contains the trip panel, expense panel, and budget panel.

### `TripPanel`

Allows users to add and delete trips and displays the details of the selected trip.

### `ExpensePanel`

Allows users to add and delete expenses for the selected trip.

### `BudgetPanel`

Displays the total expenses, remaining budget, and budget status for the selected trip.

## How to Run

1. Clone the repository.
2. Open the project in IntelliJ IDEA.
3. Run the `MainWindow` class.

## Example Usage

1. Add a new trip by entering the destination, user name, user email, and initial budget.
2. Select the trip from the list to view its details.
3. Add expenses to the selected trip.
4. View the total expenses, remaining budget, and budget status in the budget panel.

## Requirements

- Java 8 or higher
- IntelliJ IDEA

## License

This project is licensed under the MIT License.

## Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/lucas-aww/TravelExpenseManager.git
   
## Contributors
- Lucas Kocheida