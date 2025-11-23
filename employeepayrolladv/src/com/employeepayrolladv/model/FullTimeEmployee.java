package com.employeepayrolladv.model;

 

 
public class FullTimeEmployee extends Employee {
    private final double basicSalary;
    private final double bonus;
    private final double deductions;

    public FullTimeEmployee(int id, String name, double basicSalary, double bonus, double deductions) {
        super(id, name);
        if (basicSalary < 0) throw new IllegalArgumentException("basicSalary cannot be negative");
        if (bonus < 0) throw new IllegalArgumentException("bonus cannot be negative");
        if (deductions < 0) throw new IllegalArgumentException("deductions cannot be negative");
        this.basicSalary = basicSalary;
        this.bonus = bonus;
        this.deductions = deductions;
    }

    public double getBasicSalary() { return basicSalary; }
    public double getBonus() { return bonus; }
    public double getDeductions() { return deductions; }

    @Override
    public double calculateNetPay() {
        return basicSalary + bonus - deductions;
    }

    @Override
    public String toString() {
        return "FullTimeEmployee{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", basicSalary=" + basicSalary +
                ", bonus=" + bonus +
                ", deductions=" + deductions +
                ", netPay=" + String.format("%.2f", calculateNetPay()) +
                '}';
    }
}
