package persistance;

import model.Earnings;
import model.Expenditures;
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
    public String readFile() throws IOException {
        StringBuilder content = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> content.append(s));
        }

        return content.toString();
    }


    public Expenditures parseExpenditures(JSONObject jsonObject) {

    }
}
