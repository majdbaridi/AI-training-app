package com.company.trainingapp.controller;

import com.company.trainingapp.model.Employee;
import com.company.trainingapp.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "http://localhost:5173")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(
            EmployeeService service) {
        this.service = service;
    }

    @PostMapping
    public Employee create(@RequestBody Employee employee) {
        return service.save(employee);
    }

    @GetMapping
    public List<Employee> getAll() {
        return service.getAll();
    }

    @PostMapping("/{employeeId}/assign-training/{trainingId}")
    public Employee assignTraining(
            @PathVariable Long employeeId,
            @PathVariable Long trainingId
    ) {
        return service.assignTraining(employeeId, trainingId);
    }






}
