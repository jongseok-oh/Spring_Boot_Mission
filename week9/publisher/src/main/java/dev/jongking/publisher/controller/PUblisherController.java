package dev.jongking.publisher.controller;

import dev.jongking.publisher.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PUblisherController {
    private final PublisherService publisherService;

    public PUblisherController(
            @Autowired PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping("/")
    public void sendMessage(){
        publisherService.publishMessage();
    }
}
