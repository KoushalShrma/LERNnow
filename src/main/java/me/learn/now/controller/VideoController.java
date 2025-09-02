package me.learn.now.controller;

import me.learn.now.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class VideoController {
    @Autowired
    private VideoService vs;
}
