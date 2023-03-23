package org.example.Rest;

import org.example.interfaces.IAll;

import java.io.File;
import java.io.IOException;

public class Repository implements IAll {
    private final String command;
    private final String inCommand="Привет!\n Как твои дела?";

    public Repository(String text1, String text2) {
        command= getProperty.getCommandProperty("partCommand") +text1+text2;
    }

    public void getResponseReturn() throws IOException, InterruptedException {
        ProcessBuilder altProcessBuilder = new ProcessBuilder(command.split(" "));
        altProcessBuilder.directory(new File("/home/"));
        Process process2 = altProcessBuilder.start();
        altProcessBuilder.redirectErrorStream(true);
        process2.getInputStream();
        process2.waitFor();
        process2.exitValue();
        process2.destroy();
    }
}
