package org.example.Rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ExampleRestController {

    @PostMapping(value = "/telegram")
    public RestResponse postMethod(@RequestBody RestResponse response) throws IOException, InterruptedException {
        Repository repository = new Repository(response.getParam1(), response.getParam2());
        repository.getResponseReturn();
        return response;
    }
}
