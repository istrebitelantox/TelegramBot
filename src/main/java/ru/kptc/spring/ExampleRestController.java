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
//        String chat_id="-677236483";
        String chat_id="989552697";
        Repository repository = new Repository(response.getParam());
        System.out.println(repository.getCommand());
//        processHelper.startProcess(repository.getCommand());
        bot.execute(sendHelper.sendPhoto(chat_id,"","/home/yuriy/Pictures/Third.jpg"));
        bot.execute(sendHelper.sendMessage("Job Name:"+response.getParam()+unzipClass.SendToBot().toString(),chat_id));
        return response.getParam();
    }
}
