package model;

public class Expense implements MonetaryType {
// Represents different forms of cash outflow incurred by an individual
    private String date;                // keep track of date of expense
    private String description;         // Description of the expense that was incurred
    private double amount;              // amount of expense incurred

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


    @Override
    public String getStatement() {
        return date + "\t\t\t " + description + "\t\t\t " + amount;
    }




}
