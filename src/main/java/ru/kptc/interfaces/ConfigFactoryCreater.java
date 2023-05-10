package ru.kptc.interfaces;

import org.aeonbits.owner.ConfigFactory;
import ru.kptc.interfaces.properties.Allure;
import ru.kptc.interfaces.properties.BotConfig;
import ru.kptc.interfaces.properties.Commands;
import ru.kptc.interfaces.properties.Pictures;

public interface ConfigFactoryCreater {
    BotConfig botCfg = ConfigFactory.create(BotConfig.class);
    Commands commands = ConfigFactory.create(Commands.class);
    Allure allure = ConfigFactory.create(Allure.class);
    Pictures pictures = ConfigFactory.create(Pictures.class);

}
