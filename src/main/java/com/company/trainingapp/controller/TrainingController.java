// src/main/java/com/company/trainingapp/controller/TrainingController.java
package com.company.trainingapp.controller;

import com.company.trainingapp.model.Training;
import com.company.trainingapp.service.TrainingService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/trainings")
@CrossOrigin(origins = "http://localhost:5173") // adjust if needed
public class TrainingController {

    private final TrainingService service;

    public TrainingController(TrainingService service) {
        this.service = service;
    }

    @GetMapping
    public List<Training> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Training create(@RequestBody Training training) {
        return service.save(training);
    }
}