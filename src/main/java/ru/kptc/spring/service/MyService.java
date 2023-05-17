package ru.kptc.spring.service;

import lombok.SneakyThrows;
import org.aeonbits.owner.ConfigFactory;
import org.springframework.stereotype.Service;
import ru.kptc.bot.Bot;
import ru.kptc.bot.SendMessageHelper;
import ru.kptc.helpClasses.UnzipClass;
import ru.kptc.interfaces.properties.Allure;
import ru.kptc.interfaces.properties.BotConfig;
import ru.kptc.interfaces.properties.Pictures;

@Service
public class MyService {
    @SneakyThrows
    public void sendAllure(String jobName, String buildNum) {
        Bot.getBot().execute(
                SendMessageHelper.getSendMesHelp().sendPhoto(
                        ConfigFactory.create(BotConfig.class).chatId(),
                        "Job Name:" + jobName + "\nBuild number:" + buildNum + UnzipClass.getUnzipClass().GetSummaryInfo().toString() + "\nAllure report:" + ConfigFactory.create(Allure.class).allureUrl(),
                        ConfigFactory.create(Pictures.class).picturesFolder() + "six.jpg"
                )
        );
    }
}
