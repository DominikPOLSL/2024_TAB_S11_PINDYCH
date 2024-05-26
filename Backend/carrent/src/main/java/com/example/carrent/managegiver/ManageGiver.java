package com.example.carrent.managegiver;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "\"ManageGiver\"")
public class ManageGiver {

    @Id
    @SequenceGenerator(name = "manage_sequence", sequenceName = "manage_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "manage_sequence")
    @Column(name = "\"manageId\"")
    private int manageId;

    @Column(name = "\"vehicleId\"")
    private int vehicleId;

    @Column(name = "\"carGiverId\"")
    private int carGiverId;

    @Column(name = "maintenance")
    private int maintenance;

    public ManageGiver() {}

    public ManageGiver(int manageId, int vehicleId, int carGiverId, int maintenance) {
        this.manageId = manageId;
        this.vehicleId = vehicleId;
        this.carGiverId = carGiverId;
        this.maintenance = maintenance;
    }

    public int getManageId() {
        return manageId;
    }

    public void setManageId(int manageId) {
        this.manageId = manageId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getCarGiverId() {
        return carGiverId;
    }

    public void setCarGiverId(int carGiverId) {
        this.carGiverId = carGiverId;
    }

    public int getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(int maintenance) {
        this.maintenance = maintenance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ManageGiver)) return false;
        ManageGiver that = (ManageGiver) o;
        return manageId == that.manageId &&
                vehicleId == that.vehicleId &&
                carGiverId == that.carGiverId &&
                maintenance == that.maintenance;
    }

    @Override
    public int hashCode() {
        return Objects.hash(manageId, vehicleId, carGiverId, maintenance);
    }

    @Override
    public String toString() {
        return "ManageGiver{" +
                "manageId=" + manageId +
                ", vehicleId=" + vehicleId +
                ", carGiverId=" + carGiverId +
                ", maintenance=" + maintenance +
                '}';
    }
}
