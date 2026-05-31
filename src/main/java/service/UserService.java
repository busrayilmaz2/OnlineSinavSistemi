package service;

import model.User;
import repository.UserRepository;

public class UserService {
    // Repository katmanına bağlanıyoruz (Dependency Injection mantığı)
    private final UserRepository userRepository = new UserRepository();

    // 1. Kullanıcı Kayıt Mantığı
    public void registerUser(String name, String email, String role) {
        if (userRepository.findByEmail(email) != null) {
            System.out.println("=> Hata: Bu e-posta adresi zaten sisteme kayıtlı!");
            return;
        }
        User newUser = new User(0, name, email, role);
        userRepository.save(newUser);
    }

    // 2. Kullanıcı Giriş Kontrol Mantığı
    public User loginUser(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            System.out.println("=> Hata: Kullanıcı bulunamadı!");
            return null;
        }
        System.out.println("=> Giriş başarılı! Hoş geldin, " + user.getName() + " (" + user.getRole() + ")");
        return user;
    }
}