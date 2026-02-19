# 💰 Budget Tracker

A personal finance application that allows users to track their **income, expenses, and purse balance** in a simple and intuitive way.  
The goal of this project is to help individuals understand their cash flow, budget effectively, and avoid overspending.

This project was built as a personal initiative to combine my interest in **finance** with **software development**, and it is designed to grow as my technical skill set evolves.

---

## 🎯 Motivation

Managing personal finances is a common challenge, especially for individuals who earn regularly but want better visibility into their spending habits.

This application provides:
- A structured way to record income and expenses
- Real-time visibility into remaining balance
- Persistent storage of financial data for future review

By building this project, I aimed to create a practical tool that I would personally use while strengthening my skills in application design, data persistence, and clean coding practices.

---

## 🚀 Features

- Track income and expenses
- Automatically update purse (balance) after each transaction
- View current balance at any point in time
- Save financial statements for future use
- Load previously saved data and continue budgeting
- Simple and user-friendly interface

---

## 🧑‍💻 User Stories

- As a user, I want a platform to track my expenses and view my purse balance  
- As a user, I want to input my income and see how it affects my balance  
- As a user, I want to input my expenses and see how it affects my balance  
- As a user, I want to view my purse balance after each activity  
- As a user, I want to save my income and expense records  
- As a user, I want to load previously saved records and review my budget  

---

## 🛠️ Technologies Used

- **Java**
- **JSwing** for the graphical user interface
- **JSON** for data persistence
- **JUnit** for unit testing

---

## 🧠 Application Design

- Income and expense entries are recorded as transactions
- Each transaction updates the user's purse balance
- Data is persisted using JSON files, allowing users to:
  - Save their financial data
  - Load previous sessions and continue tracking
- The application follows modular design principles to keep logic, UI, and data handling separate

---

## ▶️ How to Run

```bash
git clone https://github.com/yourusername/budget-tracker.git
cd budget-tracker

javac Main.java
java Main
