package persistance;

import model.Earnings;
import model.Expenditures;
import model.Expense;
import model.Income;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReader {
// reads budget tracker from JSON data stored in file

    private String source;


    //EFFECTS: reads from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads expenses from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Expenditures readExpenses() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseExpenditures(jsonObject);
    }

    // EFFECTS: reads income from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Earnings readEarnings() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseEarnings(jsonObject);
    }


    // EFFECTS: reads source file as string and returns it
    public String readFile(String source) throws IOException {
        StringBuilder content = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> content.append(s));
        }

        return content.toString();
    }


    public Expenditures parseExpenditures(JSONObject jsonObject) {
        Expenditures exp = new Expenditures();
        addExpenses(exp, jsonObject);
        return exp;
    }

    // MODIFIES: exp
    // EFFECTS: parses expenses from JSON object and adds them to expenditures
    private void addExpenses(Expenditures exp, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("expenses");
        for (Object json : jsonArray) {
            JSONObject nextExpense = (JSONObject) json;
            addExpense(exp, nextExpense);
        }
    }

    // MODIFIES: exp
    // EFFECTS: parses expense from JSON object and adds it to expenditures
    private void addExpense(Expenditures exp, JSONObject jsonObject) {
        String date = jsonObject.getString("date");
        String description = jsonObject.getString("description");
        Double amount = jsonObject.getDouble("amount");
        Expense ex = new Expense(date, description, amount);
        exp.addExpense(ex);
    }
    
    public Earnings parseEarnings(JSONObject jsonObject) {
        Earnings earn = new Earnings();
        addEarnings(earn, jsonObject);
        return earn;
    }

    // MODIFIES: earn
    // EFFECTS: parses Incomes from JSON object and adds them to earnings
    private void addEarnings(Earnings earn, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("earnings");
        for (Object json : jsonArray) {
            JSONObject nextEarning = (JSONObject) json;
            addIncome(earn, nextEarning);
        }
    }

    // MODIFIES: earn
    // EFFECTS: parses Income from JSON object and adds it to earnings
    private void addIncome(Earnings earn, JSONObject jsonObject) {
        String date = jsonObject.getString("date");
        String description = jsonObject.getString("description");
        Double amount = jsonObject.getDouble("amount");
        Income inc = new Income(date, description, amount);
        earn.addEarning(inc);
    }
    
}
