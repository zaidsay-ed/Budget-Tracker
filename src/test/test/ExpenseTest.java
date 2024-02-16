package test;

import model.Expense;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpenseTest {

    private Expense e1;
    private Expense e2;

    @BeforeEach
    void runBefore() {
        e1 = new Expense("10/12/2005" , "Rent", 3000);
        e2 = new Expense("04/08/2005" , "Clothes", 2000);
    }

    @Test
    void getterMethods() {
        assertEquals("Rent", e1.getDescription());
        assertEquals("Clothes", e2.getDescription());
        assertEquals("04/08/2005", e2.getDate());
        assertEquals("10/12/2005", e1.getDate());
        assertEquals(2000, e2.getAmount());
        assertEquals(3000, e1.getAmount());
        assertEquals("10/12/2005\t\t\t Rent\t\t\t 3000.0", e1.getStatement());
    }
}
