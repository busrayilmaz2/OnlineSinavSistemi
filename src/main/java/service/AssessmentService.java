package service;

import model.Assessment;
import repository.AssessmentRepository;
import java.util.List;

public class AssessmentService {
    // Repository katmanına bağlanıyoruz
    private final AssessmentRepository assessmentRepository = new AssessmentRepository();

    // 1. Yeni Sınav/Anket Oluşturma İş Mantığı
    public void createAssessment(String title, String type, int createdBy) {
        if (title == null || title.trim().isEmpty()) {
            System.out.println("=> Hata: Sınav/Anket başlığı boş olamaz!");
            return;
        }

        // Tipi büyük harfe çevirip kontrol ediyoruz ('EXAM' veya 'SURVEY' olmalı)
        String upperType = type.toUpperCase();
        if (!upperType.equals("EXAM") && !upperType.equals("SURVEY")) {
            System.out.println("=> Hata: Geçersiz tip! Yalnızca 'EXAM' veya 'SURVEY' olabilir.");
            return;
        }

        Assessment assessment = new Assessment(0, title, upperType, createdBy);
        assessmentRepository.save(assessment);
    }

    // 2. Tüm Sınavları Listeleme İş Mantığı
    public List<Assessment> getAllAssessments() {
        return assessmentRepository.findAll();
    }
}
