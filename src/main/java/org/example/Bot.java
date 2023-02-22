package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public class Bot extends TelegramLongPollingBot {
    Properties properties=new Properties();

    Storage storage;
    ReplyKeyboardMarkup replyKeyboardMarkup;
    String command =
            "curl -I http://telegrambot:12345612@localhost:8080/job/demoBuildTelegram/build?token=telegram_test";

    ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));

    Bot() {
        storage = new Storage();
        initKeyboard();
    }

    @Override
    public String getBotUsername() {
        try {
            properties.load(new FileInputStream("src/main/resources/bot.properties"));
            return properties.getProperty("bot_name");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getBotToken() {
        try {
            properties.load(new FileInputStream("src/main/resources/bot.properties"));
            return properties.getProperty("bot_token");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void start() throws IOException {
        processBuilder.directory(new File("/home/"));
        Process process = processBuilder.start();
        InputStream inputStream = process.getInputStream();
        inputStream.close();
        System.out.println(process.exitValue());
        process.destroy();
    }

    @Override
    public void onUpdateReceived(Update update) {
        try{
            if(update.hasMessage() && update.getMessage().hasText())
            {
                //Извлекаем из объекта сообщение пользователя
                Message inMess = update.getMessage();
                //Достаем из inMess id чата пользователя
                String chatId = inMess.getChatId().toString();
                //Получаем текст сообщения пользователя, отправляем в написанный нами обработчик
                String response = parseMessage(inMess.getText());
                //Создаем объект класса SendMessage - наш будущий ответ пользователю
                SendMessage outMess = new SendMessage();

                //Добавляем в наше сообщение id чата а также наш ответ
                outMess.setChatId(chatId);
                outMess.setText(response);
                outMess.setReplyMarkup(replyKeyboardMarkup);

                //Отправка в чат
                execute(outMess);
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    public String parseMessage(String textMsg) {
        String response;


        //Сравниваем текст пользователя с нашими командами, на основе этого формируем ответ
        if(textMsg.equals("/start"))
            response = "Приветствую, бот знает много анекдотов. Жми \"Посмеятся\", чтобы получить случайный из них";
        else if(textMsg.equals("/get") || textMsg.equals("Посмеяться"))
            response = storage.getRandQuote();
        else if(textMsg.equals("/jen") || textMsg.equals("Запуск автотестов")) {
            response = "Тест запускается...";
            try {
                start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        else
            response = "Сообщение не распознано";

        return response;
    }
    void initKeyboard()
    {
        //Создаем объект будущей клавиатуры и выставляем нужные настройки
        replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true); //подгоняем размер
        replyKeyboardMarkup.setOneTimeKeyboard(false); //скрываем после использования

        //Создаем список с рядами кнопок
        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
        //Создаем один ряд кнопок и добавляем его в список
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRows.add(keyboardRow);
        //Добавляем одну кнопку с текстом "Посмеяться" наш ряд
        keyboardRow.add(new KeyboardButton("Посмеяться"));
        keyboardRow.add(new KeyboardButton("Запуск автотестов"));
        //добавляем лист с одним рядом кнопок в главный объект
        replyKeyboardMarkup.setKeyboard(keyboardRows);
    }
}
