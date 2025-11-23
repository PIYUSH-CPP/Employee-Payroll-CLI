package com.employeepayrolladv.app;

 

import com.employeepayrolladv.model.FullTimeEmployee;
import com.employeepayrolladv.model.PartTimeEmployee;
import com.employeepayrolladv.model.Employee;
import com.employeepayrolladv.service.PayrollService;
import com.employeepayrolladv.service.PayrollServiceImpl;

import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static final PayrollService payroll = new PayrollServiceImpl();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Simple Employee Payroll (Level 1) ===");
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Choice: ");
            switch (choice) {
                case 1 -> addFullTime();
                case 2 -> addPartTime();
                case 3 -> showAll();
                case 4 -> findById();
                case 5 -> showTotalPayroll();
                case 6 -> {
                    System.out.println("Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n1) Add Full-time Employee");
        System.out.println("2) Add Part-time Employee");
        System.out.println("3) Show All Payroll (detailed)");
        System.out.println("4) Find Employee by ID");
        System.out.println("5) Show Total Payroll (sum of net pays)");
        System.out.println("6) Exit");
    }

    private static void addFullTime() {
        try {
            int id = readInt("ID: ");
            String name = readLine("Name: ");
            double basic = readDouble("Basic Salary: ");
            double bonus = readDouble("Bonus: ");
            double deductions = readDouble("Deductions: ");
            Employee e = new FullTimeEmployee(id, name, basic, bonus, deductions);
            payroll.addEmployee(e);
            System.out.println("Added: " + e);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private static void addPartTime() {
        try {
            int id = readInt("ID: ");
            String name = readLine("Name: ");
            double hourly = readDouble("Hourly rate: ");
            double hours = readDouble("Hours worked: ");
            double deductions = readDouble("Deductions: ");
            Employee e = new PartTimeEmployee(id, name, hourly, hours, deductions);
            payroll.addEmployee(e);
            System.out.println("Added: " + e);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private static void showAll() {
        System.out.println("\n--- Payroll Report ---");
        payroll.listAll().forEach(System.out::println);
    }

    private static void findById() {
        int id = readInt("ID to search: ");
        Optional<Employee> opt = payroll.findById(id);
        opt.ifPresentOrElse(
                e -> System.out.println("Found: " + e + " | Net pay: " + String.format("%.2f", payroll.calculateNetPay(e))),
                () -> System.out.println("Employee not found with id: " + id)
        );
    }

    private static void showTotalPayroll() {
        double total = payroll.totalPayroll();
        System.out.printf("Total payroll (sum of net pays): %.2f%n", total);
    }

    // helpers
    private static int readInt(String prompt) {
        System.out.print(prompt);
        while (!sc.hasNextInt()) {
            sc.next(); // skip
            System.out.print("Please enter a valid integer: ");
        }
        return sc.nextInt();
    }

    private static double readDouble(String prompt) {
        System.out.print(prompt);
        while (!sc.hasNextDouble()) {
            sc.next();
            System.out.print("Please enter a valid number: ");
        }
        return sc.nextDouble();
    }

    private static String readLine(String prompt) {
        System.out.print(prompt);
        sc.nextLine(); // consume leftover newline
        return sc.nextLine();
    }
}
