package repository;

import model.Question;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QuestionRepository {

    // 1. Sınava Yeni Soru Ekleme Metodu (Create)
    public void save(Question question) {
        String sql = "INSERT INTO questions (assessment_id, question_text, correct_answer) VALUES (?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, question.getAssessmentId());
            stmt.setString(2, question.getQuestionText());
            stmt.setString(3, question.getCorrectAnswer());
            stmt.executeUpdate();

            System.out.println("=> Soru başarıyla eklendi: " + question.getQuestionText());

        } catch (SQLException e) {
            System.out.println("=> Soru eklenirken hata oluştu: " + e.getMessage());
        }
    }
}