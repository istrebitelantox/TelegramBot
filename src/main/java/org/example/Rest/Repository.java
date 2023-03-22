package org.example.Rest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Repository {
    private final String command;
    private String partCommand="curl -i https://api.telegram.org/bot6230654907:AAFJNsjTrLDqBhQbAe0zXaY0-qmmX15Jsaw/sendMessage?chat_id=-1001781545200^&text=";
    private final String inCommand="Привет!\n Как твои дела?";
    private Process process2;
    private String k;

    public Repository(String text1, String text2) {
        command=partCommand+text1+text2;
    }

    public String getResponseReturn() throws IOException, InterruptedException {
        ProcessBuilder altProcessBuilder = new ProcessBuilder(command.split(" "));
        altProcessBuilder.directory(new File("/home/"));
        process2 = altProcessBuilder.start();
        altProcessBuilder.redirectErrorStream(true);
        InputStream altIns = process2.getInputStream();
        k=new Scanner(altIns).useDelimiter("\\A").next();
        process2.waitFor();
        int altExitCode = process2.exitValue();
        process2.destroy();
        return k;
    }
}
