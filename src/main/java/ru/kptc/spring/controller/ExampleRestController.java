package ru.kptc.spring.controller;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.kptc.bot.Bot;
import ru.kptc.spring.model.RestResponse;
import ru.kptc.spring.service.MyService;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class ExampleRestController {
    @Autowired
    MyService myService;

    @PostMapping(value = "/telegram")
    @SneakyThrows
    public String postResult(@RequestBody RestResponse response) {
        myService.sendAllure(response.getParam1(),response.getParam2());

        return response.getParam1();
    }
    @PostMapping(value = "/telegram1")
    @SneakyThrows
    public String postBuildNumber(@RequestBody RestResponse response) {
        Bot.getBot().setBuildNum(response.getParam1());
        Logger logger=Logger.getLogger(MyService.class.getName());
        logger.log(Level.INFO,"Build number = " + Bot.getBot().getBuildNum());
        return response.getParam1();
    }
}
