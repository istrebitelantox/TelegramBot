package ru.kptc.interfaces.properties;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;
@Sources({"file:src/main/resources/allure.properties"})
public interface Allure extends Config {
    String allureUrl();
}