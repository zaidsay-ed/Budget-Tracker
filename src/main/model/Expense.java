package model;

import org.json.JSONObject;
import persistance.Writable;

public class Expense implements MonetaryType, Writable {
// Represents different forms of cash outflow incurred by an individual
    private String date;                // keep track of date of expense
    private String description;         // Description of the expense that was incurred
    private double amount;              // amount of expense incurred

    //REQUIRES: amount > 0, date in format(dd/mm/yyyy)
    public Expense(String date, String description, double amount) {
        this.date = date;
        this.description = description;
        this.amount = amount;
    }

    @Override
    public String getDate() {
        return date;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    //EFFECTS: gets cash statement
    @Override
    public String getStatement() {
        return date + "\t\t\t " + description + "\t\t\t " + amount;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("date", date);
        json.put("description", description);
        json.put("amount", amount);
        return json;
    }
}
