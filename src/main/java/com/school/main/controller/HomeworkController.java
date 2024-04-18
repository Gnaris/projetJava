package com.school.main.controller;

import com.school.main.service.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeworkController {

    @Autowired
    private HomeworkService homeworkService;
}
