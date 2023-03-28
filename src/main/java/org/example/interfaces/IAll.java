package org.example.interfaces;

import org.example.bot.KeyBoard;
import org.example.helpClasses.GetProperty;
import org.example.helpClasses.ProcessHelper;
import org.example.helpClasses.UnzipClass;

import java.util.Properties;

public interface IAll {
    Properties properties=new Properties();
    KeyBoard keyBoard=new KeyBoard();
    GetProperty getProperty=new GetProperty();
    ProcessHelper processHelper=new ProcessHelper();
    UnzipClass unzipClass =new UnzipClass();
}
