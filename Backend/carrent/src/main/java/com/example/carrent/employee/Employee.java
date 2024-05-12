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

    @Column(name = "\"name\"")
    private String employeeName;

    @Column(name = "\"surname\"")
    private String employeeSurname;

    @Column(name = "\"login\"")
    private String employeeLogin;

    @Column(name = "\"password\"")
    private String employeePassword;

    @Column(name = "\"roletype\"")
    private String roleType;
    public Employee() {
    }

    public Employee(int employeeId, String employeeName, String employeeSurname, String employeeLogin, String employeePassword, String roleType) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeSurname = employeeSurname;
        this.employeeLogin = employeeLogin;
        this.employeePassword = employeePassword;
        this.roleType = roleType;
    }
    public Employee(String employeeName, String employeeSurname, String employeeLogin, String employeePassword, String roleType) {
        this.employeeName = employeeName;
        this.employeeSurname = employeeSurname;
        this.employeeLogin = employeeLogin;
        this.employeePassword = employeePassword;
        this.roleType = roleType;
    }

    public String getEmployeeLogin() {
        return employeeLogin;
    }

    public void setEmployeeLogin(String employeeLogin) {
        this.employeeLogin = employeeLogin;
    }

    public String getEmployeePassword() {
        return employeePassword;
    }

    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
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
