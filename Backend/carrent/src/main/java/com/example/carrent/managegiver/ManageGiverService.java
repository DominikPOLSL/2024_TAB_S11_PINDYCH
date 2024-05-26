package com.example.carrent.managegiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManageGiverService {

    private final ManageGiverRepository manageGiverRepository;

    @Autowired
    public ManageGiverService(ManageGiverRepository manageGiverRepository) {
        this.manageGiverRepository = manageGiverRepository;
    }

    public List<ManageGiver> getAllManageGivers() {
        return manageGiverRepository.findAll();
    }

    public void addManageGiver(ManageGiver manageGiver) {
        manageGiverRepository.save(manageGiver);
    }

    public Optional<ManageGiver> getManageGiverById(int id) {
        return manageGiverRepository.findById(id);
    }

    public void deleteManageGiver(int id) {
        manageGiverRepository.deleteById(id);
    }

    public void updateManageGiver(ManageGiver manageGiver) {
        manageGiverRepository.save(manageGiver);
    }
}
