package me.learn.now.controller;

import me.learn.now.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class QuizController {

    @Autowired
    private QuizService qs;

}
