package repository;

import model.User;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {

    // 1. Veritabanına Yeni Kullanıcı Kaydetme Metodu (Create)
    public void save(User user) {
        String sql = "INSERT INTO users (name, email, role) VALUES (?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getRole());
            stmt.executeUpdate();

            System.out.println("=> Kullanıcı veritabanına başarıyla kaydedildi: " + user.getName());

        } catch (SQLException e) {
            System.out.println("=> Kullanıcı kaydedilirken hata oluştu: " + e.getMessage());
        }
    }

    // 2. E-posta Adresine Göre Kullanıcı Bulma Metodu (Giriş Sistemi İçin)
    public User findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("role")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("=> Kullanıcı aranırken hata oluştu: " + e.getMessage());
        }
        return null; // Kullanıcı bulunamazsa null döner
    }
}