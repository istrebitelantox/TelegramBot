package ru.kptc.helpClasses;

import lombok.SneakyThrows;

public class Sender extends UnzipClass {
    @SneakyThrows
    public boolean SendToBot(String text){
        processHelper.startProcess(getProperty.getCommandProperty("download"));
        extractFolder("src/main/resources/zipDir/allure-report.zip","src/main/resources/unzipDir/");
        ParseJsonFromFile("src/main/resources/unzipDir/allure-report/widgets/summary.json");
        return true;
    }
}
