package model;

// Inheritance: "extends BaseEntity" yazarak BaseEntity sınıfından id alanını miras alıyoruz.
public class User extends BaseEntity {

    // Encapsulation: Alanları private tutarak sarmalama yapıyoruz.
    private String name;
    private String email;
    private String role; // 'ADMIN' veya 'STUDENT'

    // Constructor: Nesne ilk oluşturulduğunda çalışan yapıcı metot.
    public User(int id, String name, String email, String role) {
        this.setId(id); // Miras aldığımız id alanını ayarlıyoruz.
        this.name = name;
        this.email = email;
        this.role = role;
    }

    // Getter ve Setter Metotları (Encapsulation)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}