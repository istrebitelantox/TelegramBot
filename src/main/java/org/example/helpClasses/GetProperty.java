package org.example.helpClasses;

import org.example.interfaces.IAll;

import java.io.FileInputStream;
import java.io.IOException;

public class GetProperty implements IAll {
    public String getBotProperty(String propertyName ){
        try {
            properties.load(new FileInputStream("src/main/resources/bot.properties"));
            return properties.getProperty(propertyName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String getCommandProperty(String propertyName ){
        try {
            properties.load(new FileInputStream("src/main/resources/command.properties"));
            return properties.getProperty(propertyName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
