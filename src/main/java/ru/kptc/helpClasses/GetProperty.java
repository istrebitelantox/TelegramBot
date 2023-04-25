package ru.kptc.helpClasses;

import ru.kptc.interfaces.All;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class GetProperty implements All {
    Properties properties=new Properties();

    public String getCommandProperty(String propertyName ){
        try {
            properties.load(new FileInputStream("src/main/resources/command.properties"));
            return properties.getProperty(propertyName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
