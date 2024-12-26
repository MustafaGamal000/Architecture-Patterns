package com.example.sqlServer_jpa.application.Service;

import com.example.sqlServer_jpa.adapters.persistence.QuestionRepository;
import com.example.sqlServer_jpa.core.domain.Question;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }
}
