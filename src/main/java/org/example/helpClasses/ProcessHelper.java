package org.example.helpClasses;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class ProcessHelper {
    String string;

    public void startProcess(String text) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder(text.split(" "));
        processBuilder.directory(new File("src/main/resources/zipDir"));
        Process process = processBuilder.start();
        processBuilder.redirectErrorStream(true);
        process.getInputStream();
        process.waitFor();
        process.exitValue();
        process.destroy();
    }
}
