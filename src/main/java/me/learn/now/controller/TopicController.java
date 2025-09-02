package me.learn.now.controller;

import me.learn.now.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TopicController {

    @Autowired
    private TopicService ts;
}
