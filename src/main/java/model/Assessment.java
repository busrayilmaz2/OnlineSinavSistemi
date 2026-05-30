package model;

// Inheritance: BaseEntity'den id alanını miras alıyoruz.
public class Assessment extends BaseEntity {

    // Encapsulation: private alanlar
    private String title;
    private String type; // 'EXAM' veya 'SURVEY'
    private int createdBy; // Oluşturan kullanıcının ID'si

    // Constructor (Yapıcı Metot)
    public Assessment(int id, String title, String type, int createdBy) {
        this.setId(id); // Üst sınıftan gelen id'yi set ediyoruz
        this.title = title;
        this.type = type;
        this.createdBy = createdBy;
    }

    // Getter ve Setter Metotları
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }
}