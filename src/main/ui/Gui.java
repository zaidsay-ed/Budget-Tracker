package ui;

import model.Earnings;
import model.Expenditures;
import model.Expense;
import model.Income;
import persistance.JsonReader;
import persistance.JsonWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;


public class Gui extends JFrame implements ActionListener {
// GUI for Budget Tracker
    private static Earnings earning;
    private static Expenditures expenses;
    private double balance;
    private static JsonWriter jsonWriterExp;
    private static JsonWriter jsonWriterInc;
    private static JsonReader jsonReaderExp;
    private static JsonReader jsonReaderInc;
    private static final String EXP_STORE = "./data/expenditures.json";

    private static final String INC_STORE = "./data/earnings.json";
    JPanel mainPanel;
    JPanel buttonPanel;
    JPanel logoPanel;
    Color goldColor = new Color(255, 215, 0);
    JButton addEarningsButton;
    JButton addExpensesButton;
    JButton viewBalanceButton;
    JButton viewCashFlowButton;
    JButton saveRecordButton;
    JButton loadRecordButton;

    // EFFECTS: runs the teller application
    public Gui() {
        super("Budget Tracker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 275));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(4, 6, 5, 6));

        panel();

        init();

        addBudget();
        viewBudget();
        memory();
        pack();
        getContentPane().add(mainPanel);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    private void panel() {
        mainPanel = new JPanel(new BorderLayout());
        buttonPanel = new JPanel(new FlowLayout());
        logoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        addButtons();
        addLogo();
        mainPanel.setLayout(new GridLayout(2, 3, 10, 10));
        buttonPanel.setBackground(Color.BLACK);
        mainPanel.setBackground(Color.BLACK);
        logoPanel.setBackground(Color.BLACK);
        mainPanel.add(logoPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
    }

    private void addLogo() {
        URL imageURL = getClass().getResource("coin.png");
        ImageIcon icon = new ImageIcon(imageURL);
        Image scaledImage = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel logoLabel = new JLabel(scaledIcon);
        add(logoLabel);
        logoPanel.add(logoLabel);
    }

    public static void main(String[] args) {
        new Gui();
    }

    private static void init() {
        earning = new Earnings();
        expenses = new Expenditures();
        jsonWriterExp = new JsonWriter(EXP_STORE);
        jsonWriterInc = new JsonWriter(INC_STORE);
        jsonReaderExp = new JsonReader(EXP_STORE);
        jsonReaderInc = new JsonReader(INC_STORE);
    }

    //EFFECTS: Functionality to add expense and earning buttons
    private void addBudget() {
        addEarningsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStatement(1);
            }
        });
        addExpensesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStatement(0);
            }
        });
    }

    //EFFECTS: Functionality to view balance and cashflow button
    private void viewBudget() {
        viewBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewBalance();
            }
        });
        viewCashFlowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printCashflow();
            }
        });
    }

    //EFFECTS: Functionality for save and load budget buttons
    private void memory() {
        saveRecordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveData();
            }
        });
        loadRecordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadData();
            }
        });
    }


    //EFFECTS: Method to add earnings and expenses
    private void addStatement(int i) {
        String date = JOptionPane.showInputDialog("Enter Date (DD/MM/YYYY):");
        String description = JOptionPane.showInputDialog("Enter Earning Description:");
        String amountStr = JOptionPane.showInputDialog("Enter Amount (in CAD $):");
        if (i == 1) {
            try {
                double amount = Double.parseDouble(amountStr);
                balance += amount;
                earning.addEarning(new Income(date, description, amount));
                JOptionPane.showMessageDialog(this, "Earnings Added Successfully!");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid amount! Please enter a valid number.");
            }
        }
        if (i == 0) {
            try {
                double amount = Double.parseDouble(amountStr);
                balance -= amount;
                expenses.addExpense(new Expense(date, description, amount));
                JOptionPane.showMessageDialog(this, "Expense Added Successfully!");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid amount! Please enter a valid number.");
            }
        }
    }

    //EFFECTS: Method to view balance
    private void viewBalance() {
        JOptionPane.showMessageDialog(this, "Current Balance: $" + balance,
                "Balance", JOptionPane.INFORMATION_MESSAGE);
    }

    //EFFECTS: Method to print cashflow
    private void printCashflow() {
        StringBuilder cashFlowMessage = new StringBuilder();

        cashFlowMessage.append("Cash Inflow:\n");
        cashFlowMessage.append(String.format("%-15s%-40s%-15s\n", "Date", "Description", "Amount"));
        cashFlowMessage.append("-------------------------------------------------------------------------"
                + "-----------\n");
        cashFlowMessage.append(printEarnings());
        cashFlowMessage.append("\n\nCash Outflow:\n");
        cashFlowMessage.append(String.format("%-15s%-40s%-15s\n", "Date", "Description", "Amount"));
        cashFlowMessage.append("-------------------------------------------------------------------------"
                + "-----------\n");
        cashFlowMessage.append(printExpenses());
        cashFlowMessage.append("\n\nBalance: $" + balance);
        cashFlowMessage.append("\n-------------------------------------------------------------------------"
                + "-----------\n");

        JTextArea textArea = new JTextArea(cashFlowMessage.toString());
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        textArea.setOpaque(true); // Make background color visible
        //textArea.setForeground(Color.WHITE); // Set text color

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        JOptionPane.showMessageDialog(this, scrollPane,
                "Cash Flow", JOptionPane.INFORMATION_MESSAGE);
    }

    //EFFECTS: Method to print all earnings stored
    private String printEarnings() {
        StringBuilder earningsMessage = new StringBuilder();
        for (int i = 0; i < earning.getNumItems(); i++) {
            earningsMessage.append(String.format("%-15s%-40s$%-15.2f\n", earning.getEarning(i).getDate(),
                    earning.getEarning(i).getDescription(), earning.getEarning(i).getAmount()));
        }
        return earningsMessage.toString();
    }

    //EFFECTS: Method to print all expenses stored
    private String printExpenses() {
        StringBuilder expensesMessage = new StringBuilder();
        for (int i = 0; i < expenses.getNumItems(); i++) {
            expensesMessage.append(String.format("%-15s%-40s$%-15.2f\n", expenses.getExpense(i).getDate(),
                    expenses.getExpense(i).getDescription(), expenses.getExpense(i).getAmount()));
        }
        return expensesMessage.toString();
    }

    //EFFECTS: Method to save data
    private void saveData() {
        try {
            jsonWriterExp.open();
            jsonWriterInc.open();
            jsonWriterExp.writeExp(expenses);
            jsonWriterInc.writeInc(earning);
            jsonWriterExp.close();
            jsonWriterInc.close();
            JOptionPane.showMessageDialog(this, "Data saved successfully!", "Save Data",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Unable to write to files: " + EXP_STORE + ", "
                    + INC_STORE, "Save Data Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //EFFECTS: Method to load data
    private void loadData() {
        try {
            expenses = jsonReaderExp.readExpenses();
            earning = jsonReaderInc.readEarnings();
            JOptionPane.showMessageDialog(this, "Data loaded successfully!", "Budget Tracker",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            System.out.println("Unable to read from files: " + EXP_STORE + ", " + INC_STORE);
        }
    }

    //EFFECTS: Method to initialize and add all buttons
    private void addButtons() {
        addEarningsButton = new JButton("Add Earnings");
        addExpensesButton = new JButton("Add Expenses");
        viewBalanceButton = new JButton("View Balance");
        viewCashFlowButton = new JButton("View Cash Flow");
        saveRecordButton = new JButton("Save Record");
        loadRecordButton = new JButton("Load Record");

        addEarningsButton.setBackground(goldColor);
        addExpensesButton.setBackground(goldColor);
        viewBalanceButton.setBackground(goldColor);
        viewCashFlowButton.setBackground(goldColor);
        saveRecordButton.setBackground(goldColor);
        loadRecordButton.setBackground(goldColor);

        buttonPanel.add(addEarningsButton);
        buttonPanel.add(addExpensesButton);
        buttonPanel.add(viewBalanceButton);
        buttonPanel.add(viewCashFlowButton);
        buttonPanel.add(saveRecordButton);
        buttonPanel.add(loadRecordButton);
    }

    //EFFECTS: Method to initialize abstract method
    @Override
    public void actionPerformed(ActionEvent e) {

    }

}

//citations : https://www.shutterstock.com/search/budgeting-logo
