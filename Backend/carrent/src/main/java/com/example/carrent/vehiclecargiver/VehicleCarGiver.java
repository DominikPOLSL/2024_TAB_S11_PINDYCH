/**
 * @file VehicleCarGiver.java
 *  This file contains the VehicleCarGiver entity class.
 */

package com.example.carrent.vehiclecargiver;

import jakarta.persistence.*;
import java.util.Objects;

/**
 *  Entity class representing a Vehicle Car Giver.
 */
@Entity
@Table(name = "\"VehicleCarGiver\"")
public class VehicleCarGiver {

    /**
     *  Unique identifier for the Vehicle Car Giver.
     */
    @Id
    @SequenceGenerator(name = "giver_sequence", sequenceName = "giver_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "giver_sequence")
    @Column(name = "\"giverId\"")
    private int id;

    /**
     *  Name of the Vehicle Car Giver.
     */
    @Column(name = "name")
    private String name;

    /**
     *  Surname of the Vehicle Car Giver.
     */
    @Column(name = "surname")
    private String surname;

    /**
     *  Default constructor.
     */
    public VehicleCarGiver() {
    }

    /**
     *  Parameterized constructor.
     * @param giverId Unique identifier for the Vehicle Car Giver.
     * @param name Name of the Vehicle Car Giver.
     * @param surname Surname of the Vehicle Car Giver.
     */
    public VehicleCarGiver(int giverId, String name, String surname) {
        this.id = giverId;
        this.name = name;
        this.surname = surname;
    }

    /**
     *  Gets the unique identifier of the Vehicle Car Giver.
     * @return Unique identifier.
     */
    public int getId() {
        return id;
    }

    /**
     *  Sets the unique identifier of the Vehicle Car Giver.
     * @param giverId Unique identifier.
     */
    @GeneratedValue
    public void setId(int giverId) {
        this.id = giverId;
    }

    /**
     *  Gets the name of the Vehicle Car Giver.
     * @return Name.
     */
    public String getName() {
        return name;
    }

    /**
     *  Sets the name of the Vehicle Car Giver.
     * @param name Name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *  Gets the surname of the Vehicle Car Giver.
     * @return Surname.
     */
    public String getSurname() {
        return surname;
    }

    /**
     *  Sets the surname of the Vehicle Car Giver.
     * @param surname Surname.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     *  Checks if this object is equal to another object.
     * @param o Object to compare.
     * @return True if objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof VehicleCarGiver))
            return false;
        VehicleCarGiver that = (VehicleCarGiver) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(surname, that.surname);
    }

    /**
     *  Generates a hash code for this object.
     * @return Hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname);
    }

    /**
     *  Generates a string representation of this object.
     * @return String representation.
     */
    @Override
    public String toString() {
        return "VehicleCarGiver{" +
                "giverId=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
