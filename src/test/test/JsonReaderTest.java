package test;

import model.Earnings;
import model.Expenditures;
import model.Expense;
import model.Income;
import org.junit.jupiter.api.Test;
import persistance.JsonReader;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest{
    @Test
    void testReaderNonExistentFileExp() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Expenditures exp = reader.readExpenses();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderNonExistentFileInc() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Earnings earn = reader.readEarnings();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyExp() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyExpenditures.json");
        try {
            Expenditures exp = reader.readExpenses();
            assertEquals(0, exp.getNumItems());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderEmptyEarn() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyEarnings.json");
        try {
            Earnings earn = reader.readEarnings();
            assertEquals(0, earn.getNumItems());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralExp() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralExpenses.json");
        try {
            Expenditures exp = reader.readExpenses();
            List<Expense> expenses = exp.getExpenses();
            assertEquals(2, expenses.size());
            checkExpense("20/01/2000", "Grocery", 100.0, expenses.get(0));
            checkExpense("25/01/2000", "Rent", 300.0, expenses.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralInc() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralEarnings.json");
        try {
            Earnings inc = reader.readEarnings();
            List<Income> earnings = inc.getEarnings();
            assertEquals(2, earnings.size());
            checkExpense("20/01/2000", "Salary", 100.0, earnings.get(0));
            checkExpense("25/01/2000", "Stock", 300.0, earnings.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
