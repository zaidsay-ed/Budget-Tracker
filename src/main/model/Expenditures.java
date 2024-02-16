package model;

import java.util.ArrayList;


public class Expenditures {
//Creates list of expenses to track
    private ArrayList<Expense> expenses;  // list of expenses

    public Expenditures() {
        expenses = new ArrayList<Expense>();
    }

    // MODIFIES: this
    // EFFECTS: adds expense to the end of the list.
    public void addExpense(Expense e) {
        expenses.add(e);
    }

    // EFFECTS: represents the number of expenses currently in the list.
    public int getNumItems() {
        return expenses.size();
    }

    // EFFECTS: true if the list is empty, false otherwise
    public boolean isEmpty() {
        return expenses.size() == 0;
    }

    // EFFECTS: gives total expenses in list
    public double getTotalExpense() {
        double sum = 0;
        for (Expense expense : expenses) {
            sum += expense.getAmount();
        }
        return sum;
    }

    // EFFECTS: prints statements for all expenses
    public void printExpenses() {
        for (Expense expense : expenses) {
            System.out.println(expense.getStatement());
        }
    }


    public Expense getExpense(int i) {
        return expenses.get(i);
    }

}