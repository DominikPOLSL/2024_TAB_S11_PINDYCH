package com.example.carrent.brand;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    public List<Brand> getBrands() {
        return brandRepository.findAll();

    }

    public Brand addNewBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    public boolean existById(int id) {
        return brandRepository.existsById(id);
    }

    public void deleteBrandById(int id) {
        brandRepository.deleteById(id);
    }

    public Brand findById(int id) {
        return brandRepository.findById(id).orElse(null);
    }

}
