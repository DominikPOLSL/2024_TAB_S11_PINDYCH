package com.example.carrent.vehiclecargiver;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "\"VehicleCarGiver\"")
public class VehicleCarGiver {

    @Id
    @SequenceGenerator(name = "giver_sequence", sequenceName = "giver_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "giver_sequence")
    @Column(name = "\"giverId\"")
    private int giverId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    public VehicleCarGiver() {}

    public VehicleCarGiver(int giverId, String name, String surname) {
        this.giverId = giverId;
        this.name = name;
        this.surname = surname;
    }

    public int getGiverId() {
        return giverId;
    }

    @GeneratedValue
    public void setGiverId(int giverId) {
        this.giverId = giverId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VehicleCarGiver)) return false;
        VehicleCarGiver that = (VehicleCarGiver) o;
        return giverId == that.giverId && Objects.equals(name, that.name) && Objects.equals(surname, that.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(giverId, name, surname);
    }

    @Override
    public String toString() {
        return "VehicleCarGiver{" +
                "giverId=" + giverId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
