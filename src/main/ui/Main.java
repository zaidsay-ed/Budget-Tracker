package ui;


import java.util.Scanner;

public class Main {
//Budget Tracker to track expenses and income.


    public Main() {
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        NewStatement ns = new NewStatement();
        while (true) {
            printMenu();
            int c = sc.nextInt();
            switch (c) {
                case 1: ns.addStatement(sc, "Earning");
                break;
                case 2: NewStatement.addStatement(sc, "Expense");
                break;
                case 3: NewStatement.viewBalance();
                break;
                case 4: NewStatement.printCashflow();
                break;
                case 5: NewStatement.saveStatement();
                break;
                case 6: NewStatement.loadStatement();
                break;
                case 7: System.out.println("Have a Good Day!!");
                        System.exit(0);
            }
        }
    }


    //EFFECTS: adds Menu to the Main class
    private static void printMenu() {
        System.out.println("\n ************ Budget Tracker Menu ************");
        System.out.println("Please List the correct corresponding Number");
        System.out.println("1. Add Earnings");
        System.out.println("2. Add Expenses");
        System.out.println("3. View Balance");
        System.out.println("4. View Cash Flow");
        System.out.println("5. Save Record");
        System.out.println("6. Load Record");
        System.out.println("7. Exit ");
        System.out.println("Enter the number corresponding to your choice");
    }
}
