package com.company.trainingapp.service;

import com.company.trainingapp.model.Training;
import com.company.trainingapp.repository.TrainingRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TrainingService {

    private final TrainingRepository repo;

    public TrainingService(TrainingRepository repo) {
        this.repo = repo;
    }

    public List<Training> getAll() {
        return repo.findAll();
    }

    public Training save(Training training) {
        return repo.save(training);
    }
}