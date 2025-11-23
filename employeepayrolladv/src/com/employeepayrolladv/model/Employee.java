package com.employeepayrolladv.model;

 
import java.util.Objects;

public abstract class Employee {
    private final int id;
    private final String name;

    protected Employee(int id, String name) {
        if (id <= 0) throw new IllegalArgumentException("id must be positive");
        this.id = id;
        this.name = Objects.requireNonNull(name, "name cannot be null").trim();
        if (this.name.isEmpty()) throw new IllegalArgumentException("name cannot be empty");
    }

    public int getId() { return id; }
    public String getName() { return name; }

    /**
     * Implemented by subclasses to compute net pay.
     */
    public abstract double calculateNetPay();

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
