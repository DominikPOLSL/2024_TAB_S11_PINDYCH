package com.example.carrent.employee;

import jakarta.persistence.*;

@Entity
@Table(name = "\"Employee\"")
public class Employee {

    @Id
    @SequenceGenerator(name = "employee_sequence", sequenceName = "employee_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_sequence")
    @Column(name = "\"employeeId\"")
    private int id;

    @Column(name = "\"name\"")
    private String name;

    @Column(name = "\"surname\"")
    private String surname;

    @Column(name = "\"login\"")
    private String login;

    @Column(name = "\"password\"")
    private String password;

    @Column(name = "\"roletype\"")
    private String roleType;

    public Employee() {
    }

    public Employee(int employeeId, String employeeName, String employeeSurname, String employeeLogin,
            String employeePassword, String roleType) {
        this.id = employeeId;
        this.name = employeeName;
        this.surname = employeeSurname;
        this.login = employeeLogin;
        this.password = employeePassword;
        this.roleType = roleType;
    }

    public Employee(String employeeName, String employeeSurname, String employeeLogin, String employeePassword,
            String roleType) {
        this.name = employeeName;
        this.surname = employeeSurname;
        this.login = employeeLogin;
        this.password = employeePassword;
        this.roleType = roleType;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String employeeLogin) {
        this.login = employeeLogin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String employeePassword) {
        this.password = employeePassword;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int employeeId) {
        this.id = employeeId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String employeeName) {
        this.name = employeeName;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String employeeSurname) {
        this.surname = employeeSurname;
    }

    @Override
    public String toString() {
        return "{" +
                " brandId='" + getId() + "'" +
                ", brandName='" + getName() + "'" +
                ", brandName='" + getSurname() + "'" +
                "}";
    }

}
