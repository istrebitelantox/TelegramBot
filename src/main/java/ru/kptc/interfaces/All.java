package ru.kptc.interfaces;


import ru.kptc.bot.KeyBoard;
import ru.kptc.helpClasses.GetProperty;
import ru.kptc.helpClasses.ProcessHelper;
import ru.kptc.helpClasses.UnzipClass;

public interface All {
    ProcessHelper processHelper=new ProcessHelper();
    UnzipClass unzipClass=new UnzipClass();
    GetProperty getProperty=new GetProperty();
    KeyBoard keyBoard=new KeyBoard();
}
