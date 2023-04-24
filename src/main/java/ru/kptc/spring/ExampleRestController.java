package ru.kptc.spring;

import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.kptc.interfaces.All;

@RestController
public class ExampleRestController implements All {

    @PostMapping(value = "/telegram")
    @SneakyThrows
    public String postMethod(@RequestBody RestResponse response){
        Repository repository = new Repository(response.getParam());
        System.out.println(repository.getCommand());
        processHelper.startProcess(repository.getCommand());
        return response.getParam();
    }
}
