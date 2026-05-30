package repository;

import model.Assessment;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AssessmentRepository {

    // 1. Yeni Sınav veya Anket Oluşturma Metodu (Create)
    public void save(Assessment assessment) {
        String sql = "INSERT INTO assessments (title, type, created_by) VALUES (?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, assessment.getTitle());
            stmt.setString(2, assessment.getType());
            stmt.setInt(3, assessment.getCreatedBy());
            stmt.executeUpdate();

            System.out.println("=> Sınav/Anket başarıyla oluşturuldu: " + assessment.getTitle());

        } catch (SQLException e) {
            System.out.println("=> Sınav oluşturulurken hata: " + e.getMessage());
        }
    }

    // 2. Tüm Sınavları ve Anketleri Listeleme Metodu (Read)
    public List<Assessment> findAll() {
        List<Assessment> list = new ArrayList<>();
        String sql = "SELECT * FROM assessments";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Assessment assessment = new Assessment(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("type"),
                        rs.getInt("created_by")
                );
                list.add(assessment);
            }
        } catch (SQLException e) {
            System.out.println("=> Sınavlar listelenirken hata: " + e.getMessage());
        }
        return list;
    }
}