package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistance.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Expenditures implements Writable {
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


    // REQUIRES: expenses.size > 0
    // EFFECTS: gets individual expenses from list
    public Expense getExpense(int i) {
        return expenses.get(i);
    }

    // EFFECTS: returns an unmodifiable list of expenses in this workroom
    public List<Expense> getExpenses() {
        return Collections.unmodifiableList(expenses);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("expenses", expendituresToJson());
        return json;
    }

    // EFFECTS: returns expenses in this cash flow as a JSON array
    private JSONArray expendituresToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Expense exp : expenses) {
            jsonArray.put(exp.toJson());
        }
        return jsonArray;
    }
}