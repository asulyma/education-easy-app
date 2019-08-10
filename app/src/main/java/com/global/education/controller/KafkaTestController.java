package com.global.education.controller;

import com.global.education.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaTestController {

    @Autowired
    private KafkaService kafkaService;

    @GetMapping
    public String sendEvent(@RequestParam("message") String message) {
        kafkaService.sendEvent(message);
        return "Successfully";
    }

}
