package com.example.carrent.model;

import jakarta.persistence.*;
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

    public Model() {}

    public Model(String modelName, int brandId) {
        this.modelName = modelName;
        this.brandId = brandId;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Model)) return false;
        Model model = (Model) o;
        return modelId == model.modelId && brandId == model.brandId && Objects.equals(modelName, model.modelName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modelId, modelName, brandId);
    }

    @Override
    public String toString() {
        return "Model{" +
                "modelId=" + modelId +
                ", modelName='" + modelName + '\'' +
                ", brandId=" + brandId +
                '}';
    }
}
