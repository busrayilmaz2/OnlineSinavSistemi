package repository;

import model.QuestionOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionOptionRepository extends JpaRepository<QuestionOption, Integer> {
    // Bir soruya ait tüm şıkları (A, B, C, D) otomatik getiren metot:
    List<QuestionOption> findByQuestionId(int questionId);
}