package model;

// Abstraction: Bu sınıf soyut (abstract) bir sınıftır ve doğrudan new ile oluşturulamaz.
public abstract class BaseEntity {

    // Encapsulation: Alanı private tutarak dışarıdan doğrudan erişimi engelliyoruz.
    private int id;

    // Getter ve Setter metotları ile kontrollü erişim sağlıyoruz.
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}