package com.example.sqlServer_jpa.application.usecase.question_usecase;

import com.example.sqlServer_jpa.adapters.persistence.primary.QuestionRepository;
import com.example.sqlServer_jpa.core.domain.Question;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllQuestions {
    private final QuestionRepository questionRepository;

    public GetAllQuestions(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> execute() {
        return questionRepository.findAll();
    }
}
