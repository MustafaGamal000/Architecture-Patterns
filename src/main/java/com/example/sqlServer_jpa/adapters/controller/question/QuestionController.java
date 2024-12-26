package com.example.sqlServer_jpa.adapters.controller.question;

import com.example.sqlServer_jpa.application.usecase.activeMQ_usecase.GetAllActiveMQData;
import com.example.sqlServer_jpa.application.usecase.question_usecase.GetAllQuestions;
import com.example.sqlServer_jpa.application.usecase.question_usecase.GetQuestionsByCategory;
import com.example.sqlServer_jpa.application.usecase.question_usecase.GetRandomQuestionsByCategory;
import com.example.sqlServer_jpa.core.domain.Question;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QuestionController {

    private final GetAllQuestions getAllQuestions;
    private final GetQuestionsByCategory getQuestionsByCategory;
    private final GetRandomQuestionsByCategory getRandomQuestionsByCategory;

    public QuestionController(GetAllQuestions getAllQuestions, GetQuestionsByCategory getQuestionsByCategory, GetRandomQuestionsByCategory getRandomQuestionsByCategory) {
        this.getAllQuestions = getAllQuestions;
        this.getQuestionsByCategory = getQuestionsByCategory;
        this.getRandomQuestionsByCategory = getRandomQuestionsByCategory;
    }

    @GetMapping("/questions")
    public List<Question> getAllQuestions() {
        return getAllQuestions.execute();
    }
    @GetMapping("/questions-category")
    public List<Question> getAllQuestionsByCategory(@RequestParam String c) {
        return getQuestionsByCategory.execute(c);
    }
    @GetMapping("/rand-questions-category")
    public List<Question> getRandomQuestionsByCategory(@RequestParam String c, int n) {
        return getRandomQuestionsByCategory.execute(c, n);
    }
}
