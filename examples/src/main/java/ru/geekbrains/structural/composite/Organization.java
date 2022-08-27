package ru.geekbrains.structural.composite;

import java.util.ArrayList;
import java.util.List;

public class Organization {

    private final List<Employee> employees;

    public Organization() {
        employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee){
        employees.add(employee);
    }

    public double getNetSalary() {
        return employees.stream().mapToDouble(Employee::getSalary).sum();
    }
}
