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
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    public VehicleCarGiver() {
    }

    public VehicleCarGiver(int giverId, String name, String surname) {
        this.id = giverId;
        this.name = name;
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    @GeneratedValue
    public void setId(int giverId) {
        this.id = giverId;
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
        if (this == o)
            return true;
        if (!(o instanceof VehicleCarGiver))
            return false;
        VehicleCarGiver that = (VehicleCarGiver) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(surname, that.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname);
    }

    @Override
    public String toString() {
        return "VehicleCarGiver{" +
                "giverId=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
