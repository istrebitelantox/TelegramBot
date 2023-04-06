package ru.kptc.spring;

import lombok.Getter;
import lombok.SneakyThrows;
import ru.kptc.interfaces.All;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Getter
public class Repository implements All {
    private final String command;
    private final String allureReportUrl="http://10.254.7.23:9090/job/demoBuildTelegram/lastBuild/allure/";

    @SneakyThrows
    public Repository() {
        command = getProperty.getCommandProperty("sendMessage")
                + URLEncoder.encode(unzipClass.SendToBot().toString()
                +"\nAllure report:"+ allureReportUrl, StandardCharsets.UTF_8);
    }
}
