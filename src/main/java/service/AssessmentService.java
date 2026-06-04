package service;

import model.Assessment;
import model.Question;
import model.QuestionOption;
import model.StudentAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.AssessmentRepository;
import repository.QuestionOptionRepository;
import repository.QuestionRepository;
import repository.StudentAnswerRepository;

import java.util.List;

@Service
public class AssessmentService {

    @Autowired
    private AssessmentRepository assessmentRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionOptionRepository questionOptionRepository;

    @Autowired
    private StudentAnswerRepository studentAnswerRepository;

    // Sınav/Anket Oluşturma
    public Assessment createAssessment(String title, String type, int createdBy) {
        Assessment assessment = new Assessment(title, type, createdBy);
        return assessmentRepository.save(assessment);
    }

    // Tüm Sınav/Anketleri Listeleme
    public List<Assessment> getAllAssessments() {
        return assessmentRepository.findAll();
    }

    // Sınava Soru Ekleme (Hocanın kılavuzdaki isteği)
    public Question addQuestion(int assessmentId, String text) {
        Question question = new Question(assessmentId, text);
        return questionRepository.save(question);
    }

    // Soruya Şık/Seçenek Ekleme
    public QuestionOption addOption(int questionId, String optionText, boolean isCorrect) {
        QuestionOption option = new QuestionOption(questionId, optionText, isCorrect);
        return questionOptionRepository.save(option);
    }

    // Öğrencinin Cevabını Kaydetme (Katılım Senaryosu)
    public StudentAnswer saveStudentAnswer(StudentAnswer answer) {
        return studentAnswerRepository.save(answer);
    }

    // Bir Sınavın Sorularını Şıklarıyla Birlikte Getirme
    public List<Question> getQuestionsByAssessment(int assessmentId) {
        List<Question> questions = questionRepository.findByAssessmentId(assessmentId);
        for (Question q : questions) {
            q.setOptions(questionOptionRepository.findByQuestionId(q.getId()));
        }
        return questions;
    }

    // Bir öğrencinin belirli bir sınavdaki Skor, Doğru ve Yanlış analizini hesaplar (Kılavuz Madde 6)
    public String getStudentReport(int userId, int assessmentId) {
        List<Question> questions = questionRepository.findByAssessmentId(assessmentId);
        List<StudentAnswer> answers = studentAnswerRepository.findByUserId(userId);

        int totalQuestions = questions.size();
        if (totalQuestions == 0) return "Bu sinavda henuz soru bulunmuyor.";

        int correctAnswers = 0;
        int wrongAnswers = 0;

        for (Question q : questions) {
            // Öğrencinin bu soruya verdiği cevabı bulalım
            StudentAnswer studentAnswer = answers.stream()
                    .filter(a -> a.getQuestionId() == q.getId())
                    .findFirst()
                    .orElse(null);

            if (studentAnswer != null && studentAnswer.getSelectedOptionId() != null) {
                // Soruya ait şıkları çekip hangisinin doğru olduğunu bulalım
                List<QuestionOption> options = questionOptionRepository.findByQuestionId(q.getId());
                QuestionOption correctOption = options.stream()
                        .filter(QuestionOption::isCorrect)
                        .findFirst()
                        .orElse(null);

                if (correctOption != null && studentAnswer.getSelectedOptionId() == correctOption.getId()) {
                    correctAnswers++;
                } else {
                    wrongAnswers++;
                }
            }
        }

        // 100 üzerinden başarı skoru hesaplama
        double score = ((double) correctAnswers / totalQuestions) * 100;

        return "--- SINAV SONUC RAPORU ---\n" +
                "Toplam Soru: " + totalQuestions + "\n" +
                "Dogru Cevap: " + correctAnswers + "\n" +
                "Yanlis Cevap: " + wrongAnswers + "\n" +
                "Basari Skoru: %" + String.format("%.2f", score);
    }
}