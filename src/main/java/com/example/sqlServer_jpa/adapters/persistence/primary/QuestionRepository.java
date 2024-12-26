package com.example.sqlServer_jpa.adapters.persistence.primary;

import com.example.sqlServer_jpa.core.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> getQuestionsByCategory(String category);

    @Query(value = "SELECT TOP (?2) * FROM questions q where q.category = (?1) ORDER BY NEWID()", nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, int nQuestions);
}
