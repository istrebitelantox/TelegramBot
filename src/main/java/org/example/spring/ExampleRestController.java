package org.example.spring;

import lombok.SneakyThrows;
import org.example.interfaces.IAll;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleRestController implements IAll {

    @PostMapping(value = "/telegram")
    @SneakyThrows
    public RestResponse postMethod(@RequestBody RestResponse response)  {
//        unzipClass.Check();
        Repository repository = new Repository(response.getParam1(), response.getParam2());
        processHelper.startProcess(repository.getCommand());
        return response;
    }
}
