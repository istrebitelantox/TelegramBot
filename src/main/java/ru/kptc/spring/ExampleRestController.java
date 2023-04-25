package ru.kptc.spring;

import lombok.SneakyThrows;
import org.aeonbits.owner.ConfigFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.kptc.interfaces.All;
import ru.kptc.interfaces.Allure;
import ru.kptc.interfaces.BotConfig;

@RestController
public class ExampleRestController implements All {

    @PostMapping(value = "/telegram")
    @SneakyThrows
    public String postMethod(@RequestBody RestResponse response){
        final Allure allure = ConfigFactory.create(Allure.class);
        final BotConfig botConfig = ConfigFactory.create(BotConfig.class);
//        String chat_id="-677236483";
//        String chat_id="989552697";
        Repository repository = new Repository(response.getParam());
        System.out.println(repository.getCommand());
        bot.execute(sendHelper.sendPhoto(botConfig.chatId(), "","/home/yuriy/Pictures/Third.jpg"));
        bot.execute(sendHelper.sendMessage("Job Name:"+response.getParam()+unzipClass.SendToBot().toString()+"\nAllure report:"+allure.allureUrl(),botConfig.chatId()));
        return response.getParam();
    }
}
