package test;

import model.Earnings;
import model.Expenditures;
import model.Expense;
import model.Income;
import org.junit.jupiter.api.Test;
import persistance.JsonReader;
import persistance.JsonWriter;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Expenditures exp = new Expenditures();
            Earnings inc = new Earnings();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyExp() {
        try {
            Expenditures exp = new Expenditures();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyExpenses.json");
            writer.open();
            writer.writeExp(exp);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyExpenses.json");
            exp = reader.readExpenses();
            assertEquals(0, exp.getNumItems());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterEmptyInc() {
        try {
            Earnings inc = new Earnings();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyEarnings.json");
            writer.open();
            writer.writeInc(inc);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyEarnings.json");
            inc = reader.readEarnings();
            assertEquals(0, inc.getNumItems());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralExp() {
        try {
            Expenditures exp = new Expenditures();
            exp.addExpense(new Expense("20/01/2000", "Grocery", 100.0));
            exp.addExpense(new Expense("25/01/2000", "Rent", 300.0));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralExpenses.json");
            writer.open();
            writer.writeExp(exp);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralExpenses.json");
            exp = reader.readExpenses();
            List<Expense> expenses = exp.getExpenses();
            assertEquals(2, expenses.size());
            checkExpense("20/01/2000", "Grocery", 100.0, expenses.get(0));
            checkExpense("25/01/2000", "Rent", 300.0, expenses.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralInc() {
        try {
            Earnings inc = new Earnings();
            inc.addEarning(new Income("20/01/2000", "Salary", 100.0));
            inc.addEarning(new Income("25/01/2000", "Stock", 300.0));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralEarnings.json");
            writer.open();
            writer.writeInc(inc);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralEarnings.json");
            inc = reader.readEarnings();
            List<Income> earnings = inc.getEarnings();
            assertEquals(2, earnings.size());
            checkExpense("20/01/2000", "Salary", 100.0, earnings.get(0));
            checkExpense("25/01/2000", "Stock", 300.0, earnings.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
