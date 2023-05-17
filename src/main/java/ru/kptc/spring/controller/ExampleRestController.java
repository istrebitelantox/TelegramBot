package ru.kptc.spring.controller;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.kptc.pojo.JenkinsResponse;
import ru.kptc.spring.service.MyService;

@RestController
public class ExampleRestController {
    @Autowired
    MyService myService;

    @PostMapping(value = "/telegram")
    @SneakyThrows
    public String postResult(@RequestBody JenkinsResponse response) {
        myService.sendAllure(response.getJobName(), response.getBuildNumber());

        return response.getJobName();
    }
}
