package com.example.carrent.employee;

import jakarta.persistence.*;

/**
 * Represents an employee entity in the car rental system.
 */
@Entity
@Table(name = "\"Employee\"")
public class Employee {

    /**
     * Unique identifier for the employee.
     */
    @Id
    @SequenceGenerator(name = "employee_sequence", sequenceName = "employee_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_sequence")
    @Column(name = "\"employeeId\"")
    private int id;

    /**
     * Name of the employee.
     */
    @Column(name = "\"name\"")
    private String name;

    /**
     * Surname of the employee.
     */
    @Column(name = "\"surname\"")
    private String surname;

    /**
     * Login username of the employee.
     */
    @Column(name = "\"login\"")
    private String login;

    /**
     * Password of the employee.
     */
    @Column(name = "\"password\"")
    private String password;

    /**
     * Role type of the employee.
     */
    @Column(name = "\"roletype\"")
    private String roleType;

    /**
     * Default constructor.
     */
    public Employee() {
    }

    /**
     * Parameterized constructor with employee ID.
     * 
     * @param employeeId       Unique identifier for the employee.
     * @param employeeName     Name of the employee.
     * @param employeeSurname  Surname of the employee.
     * @param employeeLogin    Login username of the employee.
     * @param employeePassword Password of the employee.
     * @param roleType         Role type of the employee.
     */
    public Employee(int employeeId, String employeeName, String employeeSurname, String employeeLogin,
            String employeePassword, String roleType) {
        this.id = employeeId;
        this.name = employeeName;
        this.surname = employeeSurname;
        this.login = employeeLogin;
        this.password = employeePassword;
        this.roleType = roleType;
    }

    /**
     * Parameterized constructor without employee ID.
     * 
     * @param employeeName     Name of the employee.
     * @param employeeSurname  Surname of the employee.
     * @param employeeLogin    Login username of the employee.
     * @param employeePassword Password of the employee.
     * @param roleType         Role type of the employee.
     */
    public Employee(String employeeName, String employeeSurname, String employeeLogin, String employeePassword,
            String roleType) {
        this.name = employeeName;
        this.surname = employeeSurname;
        this.login = employeeLogin;
        this.password = employeePassword;
        this.roleType = roleType;
    }

    /**
     * Gets the login username of the employee.
     * 
     * @return the login username of the employee.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets the login username of the employee.
     * 
     * @param employeeLogin the login username of the employee.
     */
    public void setLogin(String employeeLogin) {
        this.login = employeeLogin;
    }

    /**
     * Gets the password of the employee.
     * 
     * @return the password of the employee.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the employee.
     * 
     * @param employeePassword the password of the employee.
     */
    public void setPassword(String employeePassword) {
        this.password = employeePassword;
    }

    /**
     * Gets the role type of the employee.
     * 
     * @return the role type of the employee.
     */
    public String getRoleType() {
        return roleType;
    }

    /**
     * Sets the role type of the employee.
     * 
     * @param roleType the role type of the employee.
     */
    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    /**
     * Gets the unique identifier for the employee.
     * 
     * @return the unique identifier for the employee.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Sets the unique identifier for the employee.
     * 
     * @param employeeId the unique identifier for the employee.
     */
    public void setId(int employeeId) {
        this.id = employeeId;
    }

    /**
     * Gets the name of the employee.
     * 
     * @return the name of the employee.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the employee.
     * 
     * @param employeeName the name of the employee.
     */
    public void setName(String employeeName) {
        this.name = employeeName;
    }

    /**
     * Gets the surname of the employee.
     * 
     * @return the surname of the employee.
     */
    public String getSurname() {
        return this.surname;
    }

    /**
     * Sets the surname of the employee.
     * 
     * @param employeeSurname the surname of the employee.
     */
    public void setSurname(String employeeSurname) {
        this.surname = employeeSurname;
    }

    /**
     * Returns a string representation of the employee.
     * 
     * @return a string representation of the employee.
     */
    @Override
    public String toString() {
        return "{" +
                " brandId='" + getId() + "'" +
                ", brandName='" + getName() + "'" +
                ", brandName='" + getSurname() + "'" +
                "}";
    }
}
