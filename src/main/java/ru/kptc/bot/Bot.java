package ru.kptc.bot;

import lombok.SneakyThrows;
import org.aeonbits.owner.ConfigFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageCaption;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.kptc.helpClasses.ProcessHelper;
import ru.kptc.interfaces.properties.BotConfig;
import ru.kptc.interfaces.properties.Commands;
import ru.kptc.interfaces.properties.Pictures;

public class Bot extends TelegramLongPollingBot {
    private static Bot bot = null;

    public static synchronized Bot getBot() {
        if (bot == null) {
            bot = new Bot();
        }

        return bot;
    }

    private final String chatId = ConfigFactory.create(BotConfig.class).chatId();
    private String startTest;


    private Bot() {
        KeyBoard.getKeyBoard().replyKeyboard("Меню");
    }

    @Override
    public String getBotUsername() {
        return ConfigFactory.create(BotConfig.class).botName();
    }

    @Override
    public String getBotToken() {
        return ConfigFactory.create(BotConfig.class).botToken();
    }

    @Override
    @SneakyThrows
    public void onUpdateReceived(Update update) {
        SendMessage outMess = new SendMessage();
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message inMess = update.getMessage();
            parseKeyboard(inMess.getText(), chatId, outMess);
        } else if (update.hasCallbackQuery()) {
            String call_data = update.getCallbackQuery().getData();
            long messageId = update.getCallbackQuery().getMessage().getMessageId();
            outMess.setChatId(chatId);
            parseInlineKeyboard(chatId, call_data, outMess, messageId);
        }
    }

    @SneakyThrows
    private void parseKeyboard(String textMsg, String chatId, SendMessage outMess) {
        switch (textMsg) {
            case "Меню" -> {
                SendPhoto sendPhoto = SendMessageHelper.getSendMesHelp().sendPhoto(chatId, "Ваш приказ:", ConfigFactory.create(Pictures.class).picturesFolder() + "five.jpg");
                sendPhoto.setReplyMarkup(KeyBoard.getKeyBoard().inlineKeyboard("Запустить job'у", ""));
                execute(sendPhoto);
            }
            case "Старт", "/start" -> {
                outMess.setChatId(chatId);
                outMess.setText("Приветствую, бот умеет дистанционно запускать и получать результатов тестов. Жми \"Меню\", чтобы начать один из них");
                outMess.setReplyMarkup(KeyBoard.getKeyBoard().replyKeyboard("Меню"));
                execute(outMess);
            }
        }
    }

    @SneakyThrows
    private void parseInlineKeyboard(String chatId, String textMsg, SendMessage outMess, long messageId) {
        EditMessageCaption editMessageCaption;
        switch (textMsg) {
            case "Запустить job'у" -> {
                editMessageCaption = SendMessageHelper.getSendMesHelp().editMessageCapt("Список проектов", chatId, messageId);
                editMessageCaption.setReplyMarkup(KeyBoard.getKeyBoard().inlineKeyboard("Project_1", "Project_2"));
                execute(editMessageCaption);
            }
            case "Project_1", "Project_2" -> {
                editMessageCaption = SendMessageHelper.getSendMesHelp().editMessageCapt("Ветки проекта", chatId, messageId);
                if (textMsg.equals("Project_1"))
                    editMessageCaption.setReplyMarkup(KeyBoard.getKeyBoard().inlineKeyboard("JenkinsAndDocker", "WebDriverProvider"));
                else
                    editMessageCaption.setReplyMarkup(KeyBoard.getKeyBoard().inlineKeyboard("master", ""));
                startTest = ConfigFactory.create(Commands.class).startTest() + textMsg;
                execute(editMessageCaption);
            }
            case "JenkinsAndDocker", "master", "WebDriverProvider" -> {
                editMessageCaption = SendMessageHelper.getSendMesHelp().editMessageCapt("Ваш приказ:", chatId, messageId);
                editMessageCaption.setReplyMarkup(KeyBoard.getKeyBoard().inlineKeyboard("Запустить job'у", "Приказ 66"));
                outMess.setText("Будет сделано!");
                ProcessHelper.getProcHelp().startProcess(startTest + "&BRANCH=" + textMsg);
                execute(editMessageCaption);
                execute(outMess);
            }
        }
    }
}
