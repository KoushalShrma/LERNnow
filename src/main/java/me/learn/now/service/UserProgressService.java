package me.learn.now.service;

import me.learn.now.repository.UserProgressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProgressService {
    @Autowired
    private UserProgressRepo upr;
}
