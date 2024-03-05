package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistance.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Earnings implements Writable {
    //Creates list of earnings to track
    private ArrayList<Income> earnings; // list of earnings


    public Earnings() {
        earnings = new ArrayList<Income>();
    }


    // EFFECTS: true if the list is empty, false otherwise
    public boolean isEmpty() {
        return earnings.size() == 0;
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


    // EFFECTS: gives total earnings in list
    public double getTotalEarnings() {
        double sum = 0;
        for (Income earning : earnings) {
            sum += earning.getAmount();
        }
        return sum;
    }

    // REQUIRES: earnings.size > 0
    // EFFECTS: gets individual earning from list
    public Income getEarning(int i) {
        return earnings.get(i);
    }

    // EFFECTS: returns an unmodifiable list of thingies in this workroom
    public List<Income> getEarning() {
        return Collections.unmodifiableList(earnings);
    }

    // makes it to json
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("earnings", earningsToJson());
        return json;
    }

    // EFFECTS: returns earnings in this cashflow as a JSON array
    private JSONArray earningsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Income i : earnings) {
            jsonArray.put(i.toJson());
        }
        return jsonArray;
    }
}
