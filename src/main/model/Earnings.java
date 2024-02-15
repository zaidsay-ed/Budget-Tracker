package model;

import java.util.ArrayList;
import java.util.LinkedList;

public class Earnings {
    //Creates list of earnings to track
    private ArrayList<Income> earnings; // list of earnings


    public Earnings() {
        earnings = new ArrayList<Income>();
    }


    // MODIFIES: this
    // EFFECTS: adds earning to the end of the list.
    public void addEarning(Income i) {
        earnings.add(i);
    }


    // EFFECTS: represents the number of expenditures currently in the list.
    public int getNumItems() {
        return earnings.size();
    }

    // EFFECTS: true if the list is empty, false otherwise
    public boolean isEmpty() {
        return earnings.size() == 0;
    }

    // EFFECTS: gives total earnings in list
    public double getTotalEarnings() {
        int sum = 0;
        for (Income earning : earnings) {
            sum += earning.getAmount();
        }
        return sum;
    }

    // EFFECTS: prints statements for all earnings
    public void printEarnings() {
        for (Income earning : earnings) {
            System.out.println(earning.getStatement());
        }
    }
}
