package ru.kptc.bot;

import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Getter
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
    private KeyBoard(){

    }

     public ReplyKeyboardMarkup initKeyboard(String firstBtn) {
        //Создаем объект будущей клавиатуры и выставляем нужные настройки
        replyKeyboardMarkup.setResizeKeyboard(true); //подгоняем размер
        replyKeyboardMarkup.setOneTimeKeyboard(true); //скрываем после использования

        //Создаем список с рядами кнопок
        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
        //Создаем один ряд кнопок и добавляем его в список
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRows.add(keyboardRow);
        //Добавляем одну кнопку с текстом "Посмеяться" наш ряд
        keyboardRow.add(new KeyboardButton(firstBtn));
//        keyboardRow.add(new KeyboardButton(secondBtn));
        //добавляем лист с одним рядом кнопок в главный объект
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        return replyKeyboardMarkup;
    }

     public InlineKeyboardMarkup inlineKeyboard(String firstBtn, String secondBtn) {
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        InlineKeyboardButton button = new InlineKeyboardButton(firstBtn);
        InlineKeyboardButton button1 = new InlineKeyboardButton(secondBtn);
        button.setCallbackData(firstBtn);
        button1.setCallbackData(secondBtn);
        rowInline.add(button);
        if (!secondBtn.equals("")) {
            rowInline.add(button1);
        }
        rowsInline.add(rowInline);
        // Add it to the message
        markupInline.setKeyboard(rowsInline);
        return markupInline;
    }
}
