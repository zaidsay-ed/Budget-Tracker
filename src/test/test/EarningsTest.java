package test;

import model.Income;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.Earnings;

import static org.junit.jupiter.api.Assertions.*;

class EarningsTest {

    private Earnings earnings;
    private Income i1;
    private Income i2;
    private Income i3;

    @BeforeEach
    void runBefore(){
        earnings = new Earnings();
        i1 = new Income("24/07/2005" , "Salary", 1000);
        i2 = new Income("04/08/2005" , "Gambling", 2000);
        i3 = new Income("10/12/2005" , "Stocks", 3000);
    }

    @Test
    void testEarning(){
        assertTrue(earnings.isEmpty());
        earnings.addEarning(i1);
        assertEquals(1000, earnings.getTotalEarnings());
        assertFalse(earnings.isEmpty());
        assertEquals(1, earnings.getNumItems());
        earnings.addEarning(i2);
        assertEquals(3000, earnings.getTotalEarnings());
        assertEquals(2, earnings.getNumItems());
        earnings.addEarning(i3);
        assertEquals(6000, earnings.getTotalEarnings());
        assertEquals(i1, earnings.getEarning(0));
    }

}