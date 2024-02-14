package model;

import java.util.ArrayList;


public class Expenditures {
//Creates list of expenses to track
    private ArrayList<Expense> expenses;  // list of expenses

    Expenditures() {
        expenses = new ArrayList<Expense>();
    }

    // MODIFIES: this
    // EFFECTS: adds expense to the end of the list.
    public void addExpense(Expense e) {
        expenses.add(e);
    }


    // REQUIRES: list is not empty
    // MODIFIES: this
    // EFFECTS: prints out all requests
    public void printRequest() {

    }



}