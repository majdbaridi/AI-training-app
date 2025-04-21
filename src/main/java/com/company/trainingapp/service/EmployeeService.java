package com.company.trainingapp.service;

import com.company.trainingapp.event.TrainingAssignedEvent;
import com.company.trainingapp.model.Employee;
import com.company.trainingapp.model.Training;

import com.company.trainingapp.repository.EmployeeRepository;
import com.company.trainingapp.repository.TrainingRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Service
public class EmployeeService {
    private final EmployeeRepository repo;
    private final TrainingRepository trainingRepo;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final String TOPIC = "training.assigned";
    public EmployeeService(EmployeeRepository repo  , TrainingRepository trainingRepo, KafkaTemplate<String, String> kafkaTemplate) {
        this.repo = repo;
        this.trainingRepo = trainingRepo;
        this.kafkaTemplate = kafkaTemplate;
    }

    public Employee save(Employee employee) {
        return repo.save(employee);
    }

    public List<Employee> getAll() {
        return repo.findAll();
    }

    public Employee assignTraining(Long employeeId, Long trainingId) {
        Employee employee = repo.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Training training = trainingRepo.findById(trainingId)
                .orElseThrow(() -> new RuntimeException("Training not found"));

        employee.getTrainings().add(training);
        Employee updated= repo.save(employee);


        try {
            TrainingAssignedEvent event = new TrainingAssignedEvent(
                    employeeId, trainingId, java.time.Instant.now().toString()
            );
            String json = objectMapper.writeValueAsString(event);
            kafkaTemplate.send(TOPIC, json);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return updated;
    }





    }





