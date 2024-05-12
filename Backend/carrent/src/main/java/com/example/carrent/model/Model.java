package com.example.carrent.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "\"Model\"")
public class Model {

    @Id
    @SequenceGenerator(name = "model_sequence", sequenceName = "model_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "model_sequence")
    @Column(name = "\"modelId\"")
    private int modelId;

    @Column(name = "\"modelName\"")
    private String modelName;

    @Column(name = "\"brandId\"")
    private int brandId;

    public Model() {
    }

    public Model(int modelId, String modelName, int brandId) {
        this.modelId = modelId;
        this.modelName = modelName;
        this.brandId = brandId;
    }

    public Model( String modelName, int brandId) {
        this.modelName = modelName;
        this.brandId = brandId;
    }

    public int getModelId() {
        return this.modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return this.modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public int getBrandId() {
        return this.brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public Model modelId(int modelId) {
        setModelId(modelId);
        return this;
    }

    public Model modelName(String modelName) {
        setModelName(modelName);
        return this;
    }

    public Model brandId(int brandId) {
        setBrandId(brandId);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " modelId='" + getModelId() + "'" +
                ", modelName='" + getModelName() + "'" +
                ", brandId='" + getBrandId() + "'" +
                "}";
    }
}
