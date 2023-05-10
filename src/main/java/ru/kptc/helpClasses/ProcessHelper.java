package ru.kptc.helpClasses;

import java.io.File;
import java.io.IOException;

public class ProcessHelper {
    private static ProcessHelper processHelper = null;

    public static synchronized ProcessHelper getProcHelp() {
        if (processHelper == null) {
            processHelper = new ProcessHelper();
        }

        return processHelper;
    }
    private ProcessHelper(){

    }
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
