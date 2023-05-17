package ru.kptc.bot;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;


public class KeyBoard {
    private final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
    private final InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
    private static KeyBoard keyBoard = null;

    public static synchronized KeyBoard getKeyBoard() {
        if (keyBoard == null) {
            keyBoard = new KeyBoard();
        }

        return keyBoard;
    }

    private KeyBoard() {

    }

    public ReplyKeyboardMarkup replyKeyboard(String firstBtn) {
        //Создаем объект будущей клавиатуры и выставляем нужные настройки
        replyKeyboardMarkup.setResizeKeyboard(true); //подгоняем размер
        replyKeyboardMarkup.setOneTimeKeyboard(true); //скрываем после использования

        //Создаем список с рядами кнопок
        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
        //Создаем один ряд кнопок и добавляем его в список
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRows.add(keyboardRow);
        //Добавляем одну кнопку с текстом "Меню" наш ряд
        keyboardRow.add(new KeyboardButton(firstBtn));
        //добавляем лист с одним рядом кнопок в главный объект
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        return replyKeyboardMarkup;
    }

    public InlineKeyboardMarkup inlineKeyboard(String firstBtn, String secondBtn) {
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton(firstBtn);
        InlineKeyboardButton button2 = new InlineKeyboardButton(secondBtn);
        button1.setCallbackData(firstBtn);
        button2.setCallbackData(secondBtn);
        rowInline.add(button1);
        if (!secondBtn.equals("")) {
            rowInline.add(button2);
        }
        rowsInline.add(rowInline);
        markupInline.setKeyboard(rowsInline);
        return markupInline;
    }
}
