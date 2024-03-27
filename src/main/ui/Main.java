package ui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
//Budget Tracker to track expenses and income.

    private JFrame frame;
    private JPanel panel;
    private JButton addEarningsButton;
    private JButton addExpensesButton;
    private JButton viewBalanceButton;
    private JButton viewCashFlowButton;
    private JButton saveRecordButton;
    private JButton loadRecordButton;
    private JButton exitButton;

    public Main() {

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Budget Tracker");
        //frame.pack();
        frame.setSize(470,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        addEarningsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Add Earnings clicked");
            }
        }
        );

    }


    public static void main(String[] args) {
        new Main();
        Scanner sc = new Scanner(System.in);
        new NewStatement();
        while (true) {
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
