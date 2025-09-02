package me.learn.now.service;

import me.learn.now.repository.ScoreCardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreCardService {

    @Autowired
    private ScoreCardRepo scr;
}
