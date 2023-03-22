package org.example.Rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ExampleRestController {
    private Repository repository;
/*    @RequestMapping(value = "/hello",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public String getMethod(String name){
        RestResponse result=new RestResponse();
        result.setParam1("Hello");
        result.setParam2(name);

        return "Hello";
    }*/

    @PostMapping(value = "/telegram")
    public RestResponse postMethod(@RequestBody RestResponse response) throws IOException, InterruptedException {
        repository=new Repository(response.getParam1(),response.getParam2());
        repository.getResponseReturn();
        return response;
    }
/*    @GetMapping(value = "/hello")
    public RestResponse getMethod(String name) throws IOException, InterruptedException {
        return response;
    }*/
}
