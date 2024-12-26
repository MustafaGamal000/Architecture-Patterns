package com.example.sqlServer_jpa.adapters.controller;

import com.example.sqlServer_jpa.application.Service.ActiveMQService;
import com.example.sqlServer_jpa.application.Service.QuestionService;
import com.example.sqlServer_jpa.core.domain.ActiveMQLock;
import com.example.sqlServer_jpa.core.domain.Question;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class TestController {

    private final ActiveMQService activeMQService;
    private final QuestionService questionService;

    public TestController(ActiveMQService activeMQService, QuestionService questionService) {
        this.activeMQService = activeMQService;
        this.questionService = questionService;
    }

    @GetMapping("/questions")
    public List<Question> getAllEmployees() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/active-mq")
    public List<ActiveMQLock> getAllStudents() {
        return activeMQService.getAllActiveMQData();
    }

}
