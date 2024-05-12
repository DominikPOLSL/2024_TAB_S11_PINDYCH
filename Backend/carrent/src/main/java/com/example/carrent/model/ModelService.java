package com.example.carrent.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelService {

    private static ModelRepository modelRepository;

    @Autowired
    public ModelService(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    public List<Model> getModels() {
        return modelRepository.findAll();
    }

    public Model addNewModel(Model model) {
        return modelRepository.save(model);
    }

    public boolean existById(int id) {
        return modelRepository.existsById(id);
    }

    public void deleteModelById(int id) {
        modelRepository.deleteById(id);
    }

    public Model findById(int id) {
        return modelRepository.findById(id).orElse(null);
    }

    public static List<Model> getModelsByBrandId(int id) {
        List<Model> list = new ArrayList<Model>();
        for(Model m : modelRepository.findAll())
        {
            if(m.getBrandId()==id)
            {
                list.add(m);
            }
        }
        return list;
    }
}
