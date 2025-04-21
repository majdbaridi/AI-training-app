package com.company.trainingapp.event;

public class TrainingAssignedEvent {
    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(Long trainingId) {
        this.trainingId = trainingId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    private Long employeeId;
    private Long trainingId;
    private String timestamp;

    public TrainingAssignedEvent() {}

    public TrainingAssignedEvent(Long employeeId, Long trainingId, String timestamp) {
        this.employeeId = employeeId;
        this.trainingId = trainingId;
        this.timestamp = timestamp;
    }

    // Getters and Setters
}
