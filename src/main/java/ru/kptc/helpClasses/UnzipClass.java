package ru.kptc.helpClasses;

import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.aeonbits.owner.ConfigFactory;
import org.apache.commons.io.FileUtils;
import ru.kptc.interfaces.properties.Commands;
import ru.kptc.pojo.Summary;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class UnzipClass {
    private static UnzipClass unzipClass = null;

    public static synchronized UnzipClass getUnzipClass() {
        if (unzipClass == null) {
            unzipClass = new UnzipClass();
        }

        return unzipClass;
    }
    private UnzipClass(){

    }
    private void extractFolder(String zipFile, String extractFolder) {
        try {
            int BUFFER = 2048;
            File file = new File(zipFile);

            ZipFile zip = new ZipFile(file);
            String newPath = extractFolder;

            new File(newPath).mkdir();
            Enumeration zipFileEntries = zip.entries();

            // Process each entry
            while (zipFileEntries.hasMoreElements()) {
                // grab a zip file entry
                ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();
                String currentEntry = entry.getName();

                File destFile = new File(newPath, currentEntry);
                //destFile = new File(newPath, destFile.getName());
                File destinationParent = destFile.getParentFile();

                // create the parent directory structure if needed
                destinationParent.mkdirs();

                if (!entry.isDirectory()) {
                    BufferedInputStream is = new BufferedInputStream(zip
                            .getInputStream(entry));
                    int currentByte;
                    // establish buffer for writing file
                    byte data[] = new byte[BUFFER];

                    // write the current file to disk
                    FileOutputStream fos = new FileOutputStream(destFile);
                    BufferedOutputStream dest = new BufferedOutputStream(fos,
                            BUFFER);

                    // read and write until last byte is encountered
                    while ((currentByte = is.read(data, 0, BUFFER)) != -1) {
                        dest.write(data, 0, currentByte);
                    }
                    dest.flush();
                    dest.close();
                    is.close();
                    deleteFIle("src/main/resources/zipDir/");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    private void deleteFIle(String text) {
        File directory = new File(new File(text).toURI());
        FileUtils.cleanDirectory(directory);
    }

    @SneakyThrows
    private Summary ParseJsonFromFile(String fileDir) {
        Gson gson = new Gson();
        try {
            File file = new File(fileDir);
            //создаем объект FileReader для объекта File
            FileReader fr = new FileReader(file);
            //создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader reader = new BufferedReader(fr);
            // считаем сначала первую строку
            String line = reader.readLine();
            return gson.fromJson(line, Summary.class);
        } catch (IOException e) {
            throw new IOException();
        }
    }

    @SneakyThrows
    public Summary GetSummaryInfo() {
        ProcessHelper.getProcHelp().startProcess(ConfigFactory.create(Commands.class).download());
        extractFolder("src/main/resources/zipDir/allure-report.zip", "src/main/resources/unzipDir/");
        return ParseJsonFromFile("src/main/resources/unzipDir/allure-report/widgets/summary.json");
    }
}
