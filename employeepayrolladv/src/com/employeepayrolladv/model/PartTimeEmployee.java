package com.employeepayrolladv.model;
 

public class PartTimeEmployee extends Employee {
    private final double hourlyRate;
    private final double hoursWorked;
    private final double deductions;

    public PartTimeEmployee(int id, String name, double hourlyRate, double hoursWorked, double deductions) {
        super(id, name);
        if (hourlyRate < 0) throw new IllegalArgumentException("hourlyRate cannot be negative");
        if (hoursWorked < 0) throw new IllegalArgumentException("hoursWorked cannot be negative");
        if (deductions < 0) throw new IllegalArgumentException("deductions cannot be negative");
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
        this.deductions = deductions;
    }

    public double getHourlyRate() { return hourlyRate; }
    public double getHoursWorked() { return hoursWorked; }
    public double getDeductions() { return deductions; }

    @Override
    public double calculateNetPay() {
        return (hourlyRate * hoursWorked) - deductions;
    }

    @Override
    public String toString() {
        return "PartTimeEmployee{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", hourlyRate=" + hourlyRate +
                ", hoursWorked=" + hoursWorked +
                ", deductions=" + deductions +
                ", netPay=" + String.format("%.2f", calculateNetPay()) +
                '}';
    }
}
