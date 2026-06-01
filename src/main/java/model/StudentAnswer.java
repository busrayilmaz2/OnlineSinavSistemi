package model;

import jakarta.persistence.*;

@Entity
@Table(name = "student_answers")
public class StudentAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "question_id")
    private int questionId;

    @Column(name = "selected_option_id")
    private Integer selectedOptionId; // Şıklı sorular için (Boş olabilir diye Integer yaptık)

    @Column(name = "text_answer", columnDefinition = "TEXT")
    private String textAnswer; // Anket/Yorum soruları için

    public StudentAnswer() {}

    // Şıklı cevaplar için constructor
    public StudentAnswer(int userId, int questionId, int selectedOptionId) {
        this.userId = userId;
        this.questionId = questionId;
        this.selectedOptionId = selectedOptionId;
    }

    // Yazılı/Yorum cevapları için constructor
    public StudentAnswer(int userId, int questionId, String textAnswer) {
        this.userId = userId;
        this.questionId = questionId;
        this.textAnswer = textAnswer;
    }

    // Getter ve Setter Metotları
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getQuestionId() { return questionId; }
    public void setQuestionId(int questionId) { this.questionId = questionId; }

    public Integer getSelectedOptionId() { return selectedOptionId; }
    public void setSelectedOptionId(Integer selectedOptionId) { this.selectedOptionId = selectedOptionId; }

    public String getTextAnswer() { return textAnswer; }
    public void setTextAnswer(String textAnswer) { this.textAnswer = textAnswer; }
}