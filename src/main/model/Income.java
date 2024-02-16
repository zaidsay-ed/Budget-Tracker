package model;

public class Income extends Expense {
// Represent different types of cash inflow for an individual
    private String date;                // keep track of date of income
    private String description;         // Description of the income that was entered in
    private double amount;              // Amount that has been attained

    public Income(String date, String description, double amount) {
        super(date, description, amount);
        this.date = date;
        this.description = description;
        this.amount = amount;
    }

    @Override
    public double getAmount() {
        return amount;
    }

}
