package test;

import model.Earnings;
import model.Expenditures;
import model.Expense;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpendituresTest {

    private Expenditures expenses;
    private Expense e1;
    private Expense e2;
    private Expense e3;

    @BeforeEach
    void runBefore(){
        expenses = new Expenditures();
        e1 = new Expense("24/07/2005" , "Food", 1000);
        e2 = new Expense("04/08/2005" , "Clothes", 2000);
        e3 = new Expense("10/12/2005" , "Rent", 3000);
    }

    @Test
    void testEarning(){
        assertTrue(expenses.isEmpty());
        expenses.addExpense(e1);
        assertEquals(1000, expenses.getTotalExpense());
        assertFalse(expenses.isEmpty());
        assertEquals(1, expenses.getNumItems());
        expenses.addExpense(e2);
        assertEquals(3000, expenses.getTotalExpense());
        assertEquals(2, expenses.getNumItems());
        expenses.addExpense(e3);
        assertEquals(6000, expenses.getTotalExpense());
    }

}
