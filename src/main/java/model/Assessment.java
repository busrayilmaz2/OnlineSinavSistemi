package model;

import jakarta.persistence.*;

@Entity
@Table(name = "assessments")
public class Assessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String type;

    @Column(name = "created_by")
    private int createdBy;

    // 1. Boş Constructor (Spring Boot'un çalışması için şart kanka)
    public Assessment() {}

    // 2. Üç Parametreli Constructor (Servis katmanındaki hatayı çözen can simidimiz)
    public Assessment(String title, String type, int createdBy) {
        this.title = title;
        this.type = type;
        this.createdBy = createdBy;
    }

    // Getter ve Setter Metotları
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public int getCreatedBy() { return createdBy; }
    public void setCreatedBy(int createdBy) { this.createdBy = createdBy; }
}