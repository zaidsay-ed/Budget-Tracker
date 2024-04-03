package ui;


import model.Earnings;
import model.Expenditures;
import model.Expense;
import model.Income;
import persistance.JsonReader;
import persistance.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class NewStatement {
// prints new cash statement
    private static Earnings earning;
    private static Expenditures expenses;

    private static JsonWriter jsonWriterExp;
    private static JsonWriter jsonWriterInc;
    private static JsonReader jsonReaderExp;
    private static JsonReader jsonReaderInc;
    private static final String EXP_STORE = "./data/expenditures.json";

    private static final String INC_STORE = "./data/earnings.json";

    public NewStatement() {
        earning = new Earnings();
        expenses = new Expenditures();
        jsonWriterExp = new JsonWriter(EXP_STORE);
        jsonWriterInc = new JsonWriter(INC_STORE);
        jsonReaderExp = new JsonReader(EXP_STORE);
        jsonReaderInc = new JsonReader(INC_STORE);
    }

    //EFFECTS: adds cash flow statement to the ui
    public static void addStatement(Scanner sc, String s) {
        System.out.println("Enter Date (DD/MM/YYYY)");
        String date = sc.next();
        sc.nextLine();
        System.out.println("Enter " + s + " Description");
        String description = sc.nextLine();
        System.out.println("Enter Amount (in CAD $)");
        Double amount = sc.nextDouble();

        if (s.equals("Earning")) {
            earning.addEarning(new Income(date, description, amount));
            System.out.println("Earnings Added!!");
        } else {
            expenses.addExpense(new Expense(date, description, amount));
            System.out.println("Expense Added!!");
        }
    }

    //EFFECTS: adds view balance to the ui
    public static void viewBalance() {
        double diff = earning.getTotalEarnings() - expenses.getTotalExpense();
        System.out.println("Balance: " + diff);
    }


    //EFFECTS: adds cash flow to the ui
    public static void printCashflow() {
        System.out.println("Cash Inflow :");
        System.out.println("");
        System.out.println(" Date\t\t\t Description\t\t\t Amount");
        System.out.println("------------------------------------------------");
        printEarnings();
        System.out.println("------------------------------------------------");
        System.out.println("");
        System.out.println("");
        System.out.println("Cash Outflow :");
        System.out.println("");
        System.out.println(" Date\t\t\t Description\t\t\t Amount");
        System.out.println("------------------------------------------------");
        printExpenses();
        System.out.println("------------------------------------------------");
        System.out.println("");
        System.out.println("");
        viewBalance();
        System.out.println("_________________________________________________");
    }

    //EFFECTS: adds earnings to the ui
    public static void printEarnings() {
        for (int i = 0; i < earning.getNumItems(); i++) {
            System.out.println(earning.getEarning(i).getStatement());
        }
    }

    //EFFECTS: adds expenses to the ui
    public static void printExpenses() {
        for (int i = 0; i < expenses.getNumItems(); i++) {
            System.out.println(expenses.getExpense(i).getStatement());
        }
    }

    //EFFECTS: saves statement to file
    public static void saveStatement() {
        try {
            jsonWriterExp.open();
            jsonWriterInc.open();
            jsonWriterExp.writeExp(expenses);
            jsonWriterInc.writeInc(earning);
            jsonWriterExp.close();
            jsonWriterInc.close();
            System.out.println("Saved Budget Statements");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to files: " + EXP_STORE + ", " + INC_STORE);
        }
    }


    // EFFECTS: loads statement from file
    public static void loadStatement() {
        try {
            expenses = jsonReaderExp.readExpenses();
            earning = jsonReaderInc.readEarnings();
            System.out.println("Loaded Previous Statements");
        } catch (IOException e) {
            System.out.println("Unable to read from files: " + EXP_STORE + ", " + INC_STORE);
        }
    }
}