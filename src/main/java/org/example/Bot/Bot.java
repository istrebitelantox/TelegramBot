package org.example.Bot;

import org.example.interfaces.IAll;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.IOException;


public class Bot extends TelegramLongPollingBot implements IAll {
    Storage storage;
    ProcessBuilder processBuilder = new ProcessBuilder(getProperty.getCommandProperty("command").split(" "));

    public Bot() {
        storage = new Storage();
        keyBoard.setReplyKeyboardMarkup(new ReplyKeyboardMarkup());
        keyBoard.initKeyboard("Посмеяться","Запуск автотестов");
    }

    @Override
    public String getBotUsername() {
        return getProperty.getBotProperty("bot_name");
    }

    @Override
    public String getBotToken() {
        return getProperty.getBotProperty("bot_token");
    }
    public void startTest() throws IOException, InterruptedException {
        processBuilder.directory(new File("/home/"));
        Process process = processBuilder.start();
        processBuilder.redirectErrorStream(true);
        process.getInputStream();
        process.waitFor();
        process.exitValue();
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
                outMess.setReplyMarkup(keyBoard.getReplyKeyboardMarkup());

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
        switch (textMsg) {
            case "/start","Старт" ->
                    response = "Приветствую, бот знает много анекдотов. Жми \"Посмеятся\", чтобы получить случайный из них";
            case "/get", "Посмеяться" -> response = storage.getRandQuote();
            case "/jen", "Запуск автотестов" -> {
                response="Выберите проект";
                keyBoard.initKeyboard("Project 1","Project 2");
            }
            case "/project1", "Project 1" -> {
                try {
                    response = "Тест 1 запускается...";
                    startTest();
                    keyBoard.initKeyboard("Посмеяться","Запуск автотестов");
                    return response;
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            case "/project2", "Project 2" -> {
                response = "Project 2 запускается...";
                keyBoard.initKeyboard("Посмеяться","Запуск автотестов");
            }
            default -> response = "Сообщение не распознано";
        }
        return response;
    }
}
