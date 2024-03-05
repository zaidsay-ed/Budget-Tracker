package test;

import model.Expense;
import model.Income;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkExpense(String date, String description, Double amt, Expense exp) {
        assertEquals(date, exp.getDate());
        assertEquals(description, exp.getDescription());
        assertEquals(amt, exp.getAmount());
    }

    protected void checkIncome(String date, String description, Double amt, Income inc) {
        assertEquals(date, inc.getDate());
        assertEquals(description, inc.getDescription());
        assertEquals(amt, inc.getAmount());
    }
}
