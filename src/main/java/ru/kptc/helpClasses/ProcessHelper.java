package ru.kptc.helpClasses;

import ru.kptc.interfaces.All;

import java.io.File;
import java.io.IOException;

public class ProcessHelper implements All {
    String string;

    public void startProcess(String command) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
        processBuilder.directory(new File("src/main/resources/zipDir"));
        Process process = processBuilder.start();
        processBuilder.redirectErrorStream(true);
        process.getInputStream();
        process.waitFor();
        process.exitValue();
        process.destroy();
    }
}
