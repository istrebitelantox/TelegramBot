package ru.kptc.spring.service;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import ru.kptc.bot.Bot;
import ru.kptc.bot.SendMessageHelper;
import ru.kptc.helpClasses.UnzipClass;
import ru.kptc.interfaces.ConfigFactoryCreater;

@Service
public class MyService {
    @SneakyThrows
    public void sendAllure(String jobName,String buildNum) {
        Bot.getBot().execute(
                SendMessageHelper.getSendMesHelp().sendPhoto(
                        ConfigFactoryCreater.botCfg.chatId(),
                        "Job Name:" + jobName +"\nBuild number:"+ buildNum + UnzipClass.getUnzipClass().GetSummaryInfo().toString() + "\nAllure report:" + ConfigFactoryCreater.allure.allureUrl(),
                        ConfigFactoryCreater.pictures.picturesFolder()+"six.jpg"
                )
        );
    }
}
