package com.example.carrent.managegiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/managegivers")
public class ManageGiverController {

    private final ManageGiverService manageGiverService;

    @Autowired
    public ManageGiverController(ManageGiverService manageGiverService) {
        this.manageGiverService = manageGiverService;
    }

    @GetMapping
    public List<ManageGiver> getAllManageGivers() {
        return manageGiverService.getAllManageGivers();
    }

    @GetMapping("/{id}")
    public Optional<ManageGiver> getManageGiverById(@PathVariable int id) {
        return manageGiverService.getManageGiverById(id);
    }

    @PostMapping
    public void addManageGiver(@RequestBody ManageGiver manageGiver) {
        manageGiverService.addManageGiver(manageGiver);
    }

    @DeleteMapping("/{id}")
    public void deleteManageGiver(@PathVariable int id) {
        manageGiverService.deleteManageGiver(id);
    }

    @PutMapping("/{id}")
    public void updateManageGiver(@PathVariable int id, @RequestBody ManageGiver manageGiver) {
        manageGiver.setManageId(id);
        manageGiverService.updateManageGiver(manageGiver);
    }
}
