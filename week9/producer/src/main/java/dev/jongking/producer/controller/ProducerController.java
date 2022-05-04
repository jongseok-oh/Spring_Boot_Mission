package dev.jongking.producer.controller;

import dev.jongking.producer.model.JobProcess;
import dev.jongking.producer.service.ProducerService;
import dev.jongking.producer.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {
    private final ProducerService producerService;
    private final RedisService redisService;

    public ProducerController(
            RedisService redisService,
            ProducerService producerService) {
        this.producerService = producerService;
        this.redisService =redisService;
    }

    @GetMapping("/")
    public String sendMessage(){
        return producerService.send();
    }

    @GetMapping("/{jobId}")
    public JobProcess getResult(@PathVariable("jobId") String jobId){
        return redisService.retrieveJob(jobId);
    }
}
