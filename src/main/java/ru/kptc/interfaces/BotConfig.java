package ru.kptc.interfaces;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;
@Sources({"file:src/main/resources/BotConfig.properties"})
public interface BotConfig extends Config {
    String botToken();
    String botName();
}
