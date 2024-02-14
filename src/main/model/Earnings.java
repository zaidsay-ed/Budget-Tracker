package model;

import java.util.ArrayList;
import java.util.LinkedList;

public class Earnings {
    //Creates list of earnings to track
    private ArrayList<Income> earnings; // list of earnings


    Earnings() {
        earnings = new ArrayList<Income>();
    }


    // MODIFIES: this
    // EFFECTS: adds earning to the end of the list.
    public void addEarning(Income i) {
        earnings.add(i);
    }


    // REQUIRES: list is not empty
    // MODIFIES: this
    // EFFECTS: removes the Earning at the index i of the list and returns it
    public Income getNextRequest(int i) {
        return earnings.get(i);
    }
}
