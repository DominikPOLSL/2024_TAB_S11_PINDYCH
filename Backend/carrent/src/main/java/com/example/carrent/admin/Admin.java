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
    private int adminId;

    @Column(name = "\"name\"")
    private String adminName;

    @Column(name = "\"surname\"")
    private String adminSurname;

    @Column(name = "\"login\"")
    private String adminLogin;

    @Column(name = "\"password\"")
    private String adminPassword;


    public Admin() {
    }

    public Admin(int adminId, String adminName, String adminSurname, String adminLogin, String adminPassword) {
        this.adminId = adminId;
        this.adminName = adminName;
        this.adminSurname = adminSurname;
        this.adminLogin = adminLogin;
        this.adminPassword = adminPassword;
    }

    public int getAdminId() {
        return this.adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return this.adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminSurname() {
        return this.adminSurname;
    }

    public void setAdminSurname(String adminSurname) {
        this.adminSurname = adminSurname;
    }

    public String getAdminLogin() {
        return this.adminLogin;
    }

    public void setAdminLogin(String adminLogin) {
        this.adminLogin = adminLogin;
    }

    public String getAdminPassword() {
        return this.adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public Admin adminId(int adminId) {
        setAdminId(adminId);
        return this;
    }

    public Admin adminName(String adminName) {
        setAdminName(adminName);
        return this;
    }

    public Admin adminSurname(String adminSurname) {
        setAdminSurname(adminSurname);
        return this;
    }

    public Admin adminLogin(String adminLogin) {
        setAdminLogin(adminLogin);
        return this;
    }

    public Admin adminPassword(String adminPassword) {
        setAdminPassword(adminPassword);
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
        return adminId == admin.adminId && Objects.equals(adminName, admin.adminName) && Objects.equals(adminSurname, admin.adminSurname) && Objects.equals(adminLogin, admin.adminLogin) && Objects.equals(adminPassword, admin.adminPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adminId, adminName, adminSurname, adminLogin, adminPassword);
    }

    @Override
    public String toString() {
        return "{" +
            " adminId='" + getAdminId() + "'" +
            ", adminName='" + getAdminName() + "'" +
            ", adminSurname='" + getAdminSurname() + "'" +
            ", adminLogin='" + getAdminLogin() + "'" +
            ", adminPassword='" + getAdminPassword() + "'" +
            "}";
    }
    
}
