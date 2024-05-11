package com.example.carrent.employee;

import jakarta.persistence.*;

@Entity
@Table(name = "\"Employee\"")
public class Employee {

    @Id
    @SequenceGenerator(name = "employee_sequence", sequenceName = "employee_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_sequence")
    @Column(name = "\"employeeId\"")
    private int employeeId;

    @Column(name = "\"employeeName\"")
    private String employeeName;

    @Column(name = "\"employeeSurname\"")
    private String employeeSurname;
    public Employee() {
    }

    public Employee(int brandId, String employeeName, String employeeSurname) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeSurname = employeeSurname;
    }

    public int getEmployeeId() {
        return this.employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return this.employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeSurname() {
        return this.employeeSurname;
    }

    public void setEmployeeSurname(String employeeSurname) {
        this.employeeSurname = employeeSurname;
    }

    @Override
    public String toString() {
        return "{" +
                " brandId='" + getEmployeeId() + "'" +
                ", brandName='" + getEmployeeName() + "'" +
                ", brandName='" + getEmployeeSurname() + "'" +
                "}";
    }

}
