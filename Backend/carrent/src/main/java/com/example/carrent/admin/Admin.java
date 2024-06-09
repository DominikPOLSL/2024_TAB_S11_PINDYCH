package com.example.carrent.admin;

import jakarta.persistence.*;
import java.util.Objects;

/**
 * Represents an admin entity in the car rental system.
 */
@Entity
@Table(name = "\"Admin\"")
public class Admin {

    /**
     * Unique identifier for the admin.
     */
    @Id
    @SequenceGenerator(name = "admin_sequence", sequenceName = "admin_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "admin_sequence")
    @Column(name = "\"adminId\"")
    private int id;

    /**
     * Name of the admin.
     */
    @Column(name = "\"name\"")
    private String name;

    /**
     * Surname of the admin.
     */
    @Column(name = "\"surname\"")
    private String surname;

    /**
     * Login name of the admin.
     */
    @Column(name = "\"login\"")
    private String login;

    /**
     * Password for the admin.
     */
    @Column(name = "\"password\"")
    private String password;

    /**
     * Default constructor.
     */
    public Admin() {
    }

    /**
     * Parameterized constructor.
     * 
     * @param adminId       Unique identifier for the admin.
     * @param adminName     Name of the admin.
     * @param adminSurname  Surname of the admin.
     * @param adminLogin    Login name of the admin.
     * @param adminPassword Password for the admin.
     */
    public Admin(int adminId, String adminName, String adminSurname, String adminLogin, String adminPassword) {
        this.id = adminId;
        this.name = adminName;
        this.surname = adminSurname;
        this.login = adminLogin;
        this.password = adminPassword;
    }

    /**
     * Gets the admin's unique identifier.
     * 
     * @return the admin's unique identifier.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Sets the admin's unique identifier.
     * 
     * @param adminId the admin's unique identifier.
     */
    public void setId(int adminId) {
        this.id = adminId;
    }

    /**
     * Gets the name of the admin.
     * 
     * @return the name of the admin.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the admin.
     * 
     * @param adminName the name of the admin.
     */
    public void setName(String adminName) {
        this.name = adminName;
    }

    /**
     * Gets the surname of the admin.
     * 
     * @return the surname of the admin.
     */
    public String getSurname() {
        return this.surname;
    }

    /**
     * Sets the surname of the admin.
     * 
     * @param adminSurname the surname of the admin.
     */
    public void setSurname(String adminSurname) {
        this.surname = adminSurname;
    }

    /**
     * Gets the login name of the admin.
     * 
     * @return the login name of the admin.
     */
    public String getLogin() {
        return this.login;
    }

    /**
     * Sets the login name of the admin.
     * 
     * @param adminLogin the login name of the admin.
     */
    public void setLogin(String adminLogin) {
        this.login = adminLogin;
    }

    /**
     * Gets the password of the admin.
     * 
     * @return the password of the admin.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Sets the password of the admin.
     * 
     * @param adminPassword the password of the admin.
     */
    public void setPassword(String adminPassword) {
        this.password = adminPassword;
    }

    /**
     * Sets the admin's unique identifier.
     * 
     * @param adminId the admin's unique identifier.
     * @return the current admin instance.
     */
    public Admin adminId(int adminId) {
        setId(adminId);
        return this;
    }

    /**
     * Sets the name of the admin.
     * 
     * @param adminName the name of the admin.
     * @return the current admin instance.
     */
    public Admin adminName(String adminName) {
        setName(adminName);
        return this;
    }

    /**
     * Sets the surname of the admin.
     * 
     * @param adminSurname the surname of the admin.
     * @return the current admin instance.
     */
    public Admin adminSurname(String adminSurname) {
        setSurname(adminSurname);
        return this;
    }

    /**
     * Sets the login name of the admin.
     * 
     * @param adminLogin the login name of the admin.
     * @return the current admin instance.
     */
    public Admin adminLogin(String adminLogin) {
        setLogin(adminLogin);
        return this;
    }

    /**
     * Sets the password of the admin.
     * 
     * @param adminPassword the password of the admin.
     * @return the current admin instance.
     */
    public Admin adminPassword(String adminPassword) {
        setPassword(adminPassword);
        return this;
    }

    /**
     * Checks if this admin is equal to another object.
     * 
     * @param o the other object.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Admin)) {
            return false;
        }
        Admin admin = (Admin) o;
        return id == admin.id && Objects.equals(name, admin.name)
                && Objects.equals(surname, admin.surname) && Objects.equals(login, admin.login)
                && Objects.equals(password, admin.password);
    }

    /**
     * Computes the hash code of the admin.
     * 
     * @return the hash code of the admin.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, login, password);
    }

    /**
     * Returns a string representation of the admin.
     * 
     * @return a string representation of the admin.
     */
    @Override
    public String toString() {
        return "{" +
                " adminId='" + getId() + "'" +
                ", adminName='" + getName() + "'" +
                ", adminSurname='" + getSurname() + "'" +
                ", adminLogin='" + getLogin() + "'" +
                ", adminPassword='" + getPassword() + "'" +
                "}";
    }
}
