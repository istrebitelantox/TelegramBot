package ru.kptc.interfaces;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;
@Sources({"file:src/main/resources/command.properties"})
public interface Commands extends Config {
    String startTest();
    String sendMessage();
    String download();
}
