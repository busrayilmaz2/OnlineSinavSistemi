package repository;

import model.StudentAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentAnswerRepository extends JpaRepository<StudentAnswer, Integer> {
    // Bir öğrencinin verdiği tüm cevapları getirir:
    List<StudentAnswer> findByUserId(int userId);

    // Belirli bir soruya verilen tüm cevapları raporlamak için getirir:
    List<StudentAnswer> findByQuestionId(int questionId);
}
