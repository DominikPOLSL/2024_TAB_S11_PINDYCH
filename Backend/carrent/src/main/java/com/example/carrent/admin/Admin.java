package com.example.carrent.admin;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "\"Admin\"")
public class Admin {

    @Id
    @SequenceGenerator(name = "admin_sequence", sequenceName = "admin_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "admin_sequence")
    @Column(name = "\"adminId\"")
    private int id;

    @Column(name = "\"name\"")
    private String name;

    @Column(name = "\"surname\"")
    private String surname;

    @Column(name = "\"login\"")
    private String login;

    @Column(name = "\"password\"")
    private String password;

    public Admin() {
    }

    public Admin(int adminId, String adminName, String adminSurname, String adminLogin, String adminPassword) {
        this.id = adminId;
        this.name = adminName;
        this.surname = adminSurname;
        this.login = adminLogin;
        this.password = adminPassword;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int adminId) {
        this.id = adminId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String adminName) {
        this.name = adminName;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String adminSurname) {
        this.surname = adminSurname;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String adminLogin) {
        this.login = adminLogin;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String adminPassword) {
        this.password = adminPassword;
    }

    public Admin adminId(int adminId) {
        setId(adminId);
        return this;
    }

    public Admin adminName(String adminName) {
        setName(adminName);
        return this;
    }

    public Admin adminSurname(String adminSurname) {
        setSurname(adminSurname);
        return this;
    }

    public Admin adminLogin(String adminLogin) {
        setLogin(adminLogin);
        return this;
    }

    public Admin adminPassword(String adminPassword) {
        setPassword(adminPassword);
        return this;
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, login, password);
    }

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
