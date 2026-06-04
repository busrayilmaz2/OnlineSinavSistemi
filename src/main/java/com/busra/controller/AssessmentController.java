package com.busra.controller;

import model.Assessment;
import model.Question;
import model.QuestionOption;
import model.StudentAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.AssessmentService;

import java.util.List;

@RestController
@RequestMapping("/api/assessments")
public class AssessmentController {

    @Autowired
    private AssessmentService assessmentService;

    // 1. Yeni Sınav/Anket Oluşturma (POST -> http://localhost:8080/api/assessments)
    @PostMapping
    public Assessment createAssessment(@RequestBody Assessment assessment) {
        return assessmentService.createAssessment(assessment.getTitle(), assessment.getType(), assessment.getCreatedBy());
    }

    // 2. Tüm Sınavları Listeleme (GET -> http://localhost:8080/api/assessments)
    @GetMapping
    public List<Assessment> getAllAssessments() {
        return assessmentService.getAllAssessments();
    }

    // 3. Sınava Soru Ekleme (POST -> http://localhost:8080/api/assessments/{id}/questions)
    @PostMapping("/{id}/questions")
    public Question addQuestion(@PathVariable int id, @RequestBody Question question) {
        return assessmentService.addQuestion(id, question.getText());
    }

    // 4. Soruya Şık Ekleme (POST -> http://localhost:8080/api/assessments/questions/{questionId}/options)
    @PostMapping("/questions/{questionId}/options")
    public QuestionOption addOption(@PathVariable int questionId, @RequestBody QuestionOption option) {
        return assessmentService.addOption(questionId, option.getOptionText(), option.isCorrect());
    }

    // 5. Öğrencinin Sınav/Anket Cevaplaması (POST -> http://localhost:8080/api/assessments/answer)
    @PostMapping("/answer")
    public StudentAnswer answerQuestion(@RequestBody StudentAnswer answer) {
        return assessmentService.saveStudentAnswer(answer);
    }

    // 6. Sınav Sonuç Raporu ve Doğru-Yanlış Analizi (GET -> http://localhost:8080/api/assessments/report?userId=1&assessmentId=1)
    @GetMapping("/report")
    public String getReport(@RequestParam int userId, @RequestParam int assessmentId) {
        return assessmentService.getStudentReport(userId, assessmentId);
    }
}