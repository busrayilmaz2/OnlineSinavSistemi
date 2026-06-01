package model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "assessment_id")
    private int assessmentId;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String text;

    @OneToMany(mappedBy = "questionId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<QuestionOption> options;

    public Question() {}

    public Question(int assessmentId, String text) {
        this.assessmentId = assessmentId;
        this.text = text;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getAssessmentId() { return assessmentId; }
    public void setAssessmentId(int assessmentId) { this.assessmentId = assessmentId; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public List<QuestionOption> getOptions() { return options; }
    public void setOptions(List<QuestionOption> options) { this.options = options; }
}