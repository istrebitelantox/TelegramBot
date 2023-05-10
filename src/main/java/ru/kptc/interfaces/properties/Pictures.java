package ru.kptc.interfaces.properties;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;
@Sources({"file:src/main/resources/pictures.properties"})
public interface Pictures extends Config {
    String picturesFolder();
}
