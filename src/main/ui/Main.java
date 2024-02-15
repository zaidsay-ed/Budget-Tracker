package ui;


import java.util.Scanner;

public class Main {
//Budget Tracker to track expenses and income.

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n ************ Budget Tracker Menu ************");
            printMenu();
            int c = sc.nextInt();
            switch (c) {
                case 1: NewStatement.addStatement(sc, "Earning");
                        break;
                case 2: NewStatement.addStatement(sc, "Expense");
                        break;
                case 3: NewStatement.viewBalance();
                        break;
                case 4: NewStatement.printCashflow();
                        break;
                case 5: System.out.println("Have a Good Day!!");
                        System.exit(0);
            }
        }
    }



    private static void printMenu() {
        System.out.println("1. Add Earnings");
        System.out.println("2. Add Expenses");
        System.out.println("3. View Balance");
        System.out.println("4. View Cash Flow");
        System.out.println("5. Exit ");
        System.out.println("Enter the number corresponding to your choice");
    }
}
