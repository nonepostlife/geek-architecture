package ru.geekbrains.structural.composite;

import java.util.List;

public abstract class Employee {
    private double salary;
    private String name;
    private List<String> roles;

    public Employee(double salary, String name) {
        this.salary = salary;
        this.name = name;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public double getSalary() {
        return salary;
    }

    public String getName() {
        return name;
    }

    public List<String> getRoles() {
        return roles;
    }
}
