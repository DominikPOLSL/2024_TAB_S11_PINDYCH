package com.example.carrent.brand;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

/**
 * Represents a brand entity in the car rental system.
 */
@Entity
@Table(name = "\"Brand\"")
public class Brand {

    /**
     * Unique identifier for the brand.
     */
    @Id
    @SequenceGenerator(name = "brand_sequence", sequenceName = "brand_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "brand_sequence")
    @Column(name = "\"brandId\"")
    private int brandId;

    /**
     * Name of the brand.
     */
    @Column(name = "\"brandName\"")
    private String brandName;

    /**
     * Default constructor.
     */
    public Brand() {
    }

    /**
     * Parameterized constructor.
     * 
     * @param brandId   Unique identifier for the brand.
     * @param brandName Name of the brand.
     */
    public Brand(int brandId, String brandName) {
        this.brandId = brandId;
        this.brandName = brandName;
    }

    /**
     * Parameterized constructor without ID.
     * 
     * @param brandName Name of the brand.
     */
    public Brand(String brandName) {
        this.brandName = brandName;
    }

    /**
     * Gets the brand's unique identifier.
     * 
     * @return the brand's unique identifier.
     */
    public int getBrandId() {
        return this.brandId;
    }

    /**
     * Sets the brand's unique identifier.
     * 
     * @param brandId the brand's unique identifier.
     */
    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    /**
     * Gets the name of the brand.
     * 
     * @return the name of the brand.
     */
    public String getBrandName() {
        return this.brandName;
    }

    /**
     * Sets the name of the brand.
     * 
     * @param brandName the name of the brand.
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    /**
     * Returns a string representation of the brand.
     * 
     * @return a string representation of the brand.
     */
    @Override
    public String toString() {
        return "{" +
                " brandId='" + getBrandId() + "'" +
                ", brandName='" + getBrandName() + "'" +
                "}";
    }
}
