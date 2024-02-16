package ui;

import model.Earnings;
import model.Expenditures;
import model.Expense;
import model.Income;

import java.util.Scanner;

public class NewStatement {

    private static Earnings earning = new Earnings();
    private static Expenditures expenses = new Expenditures();

    public NewStatement() {
    }

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

    public static void viewBalance() {
        double diff = earning.getTotalEarnings() - expenses.getTotalExpense();
        System.out.println("Balance : " + diff);
    }

    public static void printCashflow() {
        System.out.println("Cash Inflow :");
        System.out.println("");
        System.out.println(" Date\t\t\t Description\t\t\t Amount");
        System.out.println("************************************************");
        printEarnings();
        System.out.println("************************************************");
        System.out.println("");
        System.out.println("Cash Outflow :");
        System.out.println("");
        System.out.println(" Date\t\t\t Description\t\t\t Amount");
        System.out.println("************************************************");
        printExpenses();
        System.out.println("************************************************");
        System.out.println("");
        System.out.println("************************************************");
        viewBalance();
        System.out.println("************************************************");
    }

    public static void printEarnings() {
        for (int i = 0; i < earning.getNumItems(); i++) {
            System.out.println(earning.getEarning(i).getStatement());
        }
    }

    public static void printExpenses() {
        for (int i = 0; i < expenses.getNumItems(); i++) {
            System.out.println(expenses.getExpense(i).getStatement());
        }
    }

}