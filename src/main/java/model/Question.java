package model;

public class Question extends BaseEntity {

    private int assessmentId; // Sorunun ait olduğu sınavın ID'si (Veritabanı ilişkisi için)
    private String questionText;
    private String correctAnswer;

    // Constructor
    public Question(int id, int assessmentId, String questionText, String correctAnswer) {
        this.setId(id);
        this.assessmentId = assessmentId;
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
    }

    // Getter ve Setter Metotları
    public int getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(int assessmentId) {
        this.assessmentId = assessmentId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}