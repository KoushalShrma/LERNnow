package me.learn.now.controller;

import me.learn.now.service.UserProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserProgressController {

    @Autowired
    private UserProgressService ups;
}
