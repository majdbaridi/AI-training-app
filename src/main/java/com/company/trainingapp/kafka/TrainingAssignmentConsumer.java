package com.company.trainingapp.kafka;

import com.company.trainingapp.event.TrainingAssignedEvent;
import com.company.trainingapp.model.Training;
import com.company.trainingapp.repository.TrainingRepository;
import com.company.trainingapp.service.GptService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TrainingAssignmentConsumer {

    private final GptService gptService;
    private final TrainingRepository trainingRepo;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public TrainingAssignmentConsumer(GptService gptService, TrainingRepository trainingRepo) {
        this.gptService = gptService;
        this.trainingRepo = trainingRepo;
    }

    @KafkaListener(topics = "training.assigned", groupId = "myGroup")
    public void handleTrainingAssigned(String message) {
        try {
            TrainingAssignedEvent event = objectMapper.readValue(message, TrainingAssignedEvent.class);

            Training training = trainingRepo.findById(event.getTrainingId())
                    .orElseThrow(() -> new RuntimeException("Training not found"));

            System.out.println("📨 Employee " + event.getEmployeeId()
                    + " was assigned to training: " + training.getTitle()
                    + " at " + event.getTimestamp());

            String prompt = "An employee just completed training: \"" + training.getTitle() + "\". " +
                    "What 3 trainings would you recommend next?";
            String reply = gptService.ask(prompt);

            System.out.println("🤖 GPT Recommendation:\n" + reply);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("❌ Failed to handle training assigned event");
        }
    }
}
