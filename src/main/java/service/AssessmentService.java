package service;

import model.Assessment;
import model.Question;
import model.QuestionOption;
import model.StudentAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.AssessmentRepository;
import repository.QuestionOptionRepository;
import repository.QuestionRepository;
import repository.StudentAnswerRepository;

import java.util.List;

@Service
public class AssessmentService {

    @Autowired
    private AssessmentRepository assessmentRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionOptionRepository questionOptionRepository;

    @Autowired
    private StudentAnswerRepository studentAnswerRepository;

    public Assessment createAssessment(String title, String type, int createdBy) {
        Assessment assessment = new Assessment(title, type, createdBy);
        return assessmentRepository.save(assessment);
    }

    public List<Assessment> getAllAssessments() {
        return assessmentRepository.findAll();
    }

    public Question addQuestion(int assessmentId, String text) {
        Question question = new Question(assessmentId, text);
        return questionRepository.save(question);
    }

    public QuestionOption addOption(int questionId, String optionText, boolean isCorrect) {
        QuestionOption option = new QuestionOption(questionId, optionText, isCorrect);
        return questionOptionRepository.save(option);
    }

    public StudentAnswer saveStudentAnswer(StudentAnswer answer) {
        return studentAnswerRepository.save(answer);
    }

    public List<Question> getQuestionsByAssessment(int assessmentId) {
        List<Question> questions = questionRepository.findByAssessmentId(assessmentId);
        for (Question q : questions) {
            q.setOptions(questionOptionRepository.findByQuestionId(q.getId()));
        }
        return questions;
    }
}