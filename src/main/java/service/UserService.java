package service;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UserRepository;
import java.util.Optional;

@Service
public class UserService {

    // Kanka o lanet 'new' kelimesini tamamen sildik.
    // Spring Boot'un bunu otomatik doldurması için @Autowired mekanizmasına geri döndük.
    @Autowired
    private UserRepository userRepository;

    // Kullanıcı Kaydetme / Kayıt Olma
    public User registerUser(String name, String email, String role) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setRole(role);
        return userRepository.save(user);
    }

    // Giriş Yapma / E-posta ile Kullanıcı Sorgulama
    public User loginUser(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            System.out.println("=> Giriş Başarılı! Hoş geldin, " + userOptional.get().getName());
            return userOptional.get();
        } else {
            System.out.println("=> Kullanıcı bulunamadı!");
            return null;
        }
    }

    // Rol Kontrolü
    public boolean checkUserRole(int userId, String requiredRole) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            String userRole = userOptional.get().getRole().toUpperCase();
            return userRole.equals(requiredRole.toUpperCase()) || userRole.equals("ADMIN");
        }
        return false;
    }
}