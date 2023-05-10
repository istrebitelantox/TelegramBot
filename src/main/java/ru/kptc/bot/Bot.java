package ru.kptc.bot;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.aeonbits.owner.ConfigFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageCaption;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.kptc.helpClasses.ProcessHelper;
import ru.kptc.interfaces.ConfigFactoryCreater;
import ru.kptc.interfaces.properties.BotConfig;
import ru.kptc.spring.model.RestResponse;

public class Bot extends TelegramLongPollingBot {
    private static Bot bot = null;

    public static synchronized Bot getBot() {
        if (bot == null) {
            bot = new Bot();
        }

        return bot;
    }
    long messageId;
    String chatId=ConfigFactory.create(BotConfig.class).chatId();
    String startTest;
    @Getter@Setter
    private String buildNum;


    private Bot() {
        KeyBoard.getKeyBoard().initKeyboard("Меню");
    }

    @Override
    public String getBotUsername() {
        return ConfigFactory.create(BotConfig.class).botName();
                /*ConfigFactoryCreater.botCfg.botName()*/
    }

    @Override
    public String getBotToken() {
        return ConfigFactory.create(BotConfig.class).botToken();
        /*ConfigFactoryCreater.botCfg.botToken();*/
    }

    @Override
    @SneakyThrows
    public void onUpdateReceived(Update update) {
        //Создаем объект класса SendMessage - наш будущий ответ пользователю
        SendMessage outMess = new SendMessage();
        if (update.hasMessage() && update.getMessage().hasText()) {
            //Извлекаем из объекта сообщение пользователя
            Message inMess = update.getMessage();
            //Достаем из inMess id чата пользователя
//            chatId = inMess.getChatId().toString();
            //Получаем текст сообщения пользователя, отправляем в написанный нами обработчик
            //Добавляем в наше сообщение id чата а также наш ответ
            parseKeyboard(inMess.getText(), chatId, outMess);
        } else if (update.hasCallbackQuery()) {
            // Set variables
            String call_data = update.getCallbackQuery().getData();
//            chatId = update.getCallbackQuery().getMessage().getChatId().toString();
            messageId = update.getCallbackQuery().getMessage().getMessageId();
            outMess.setChatId(chatId);
            parseInlineKeyboard(chatId, call_data, outMess, messageId);
        }
    }

    @SneakyThrows
    public void parseKeyboard(String textMsg, String chatId, SendMessage outMess) {
        switch (textMsg) {
            case "Меню" -> {
                SendPhoto sendPhoto = SendMessageHelper.getSendMesHelp().sendPhoto(chatId, "Ваш приказ:", ConfigFactoryCreater.pictures.picturesFolder() + "five.jpg");
                sendPhoto.setReplyMarkup(KeyBoard.getKeyBoard().inlineKeyboard("Запустить job'у", "Приказ 66"));
                execute(sendPhoto);
            }
            case "Старт", "/start" -> {
                outMess.setChatId(chatId);
                outMess.setText("Приветствую, бот умеет дистанционно запускать и получать результатов тестов. Жми \"Меню\", чтобы начать один из них");
                outMess.setReplyMarkup(KeyBoard.getKeyBoard().initKeyboard("Меню"));
                execute(outMess);
            }
        }
    }

    @SneakyThrows
    public void parseInlineKeyboard(String chatId, String textMsg, SendMessage outMess, long messageId) {
        EditMessageCaption editMessageCaption;
        switch (textMsg) {
            case "Запустить job'у" -> {
                editMessageCaption = SendMessageHelper.getSendMesHelp().editMessageCapt("Список проектов", chatId, messageId);
                editMessageCaption.setReplyMarkup(KeyBoard.getKeyBoard().inlineKeyboard("Project_1", "Project_2"));
                execute(editMessageCaption);
            }
            case "Project_1","Project_2" -> {
                editMessageCaption = SendMessageHelper.getSendMesHelp().editMessageCapt("Ветки проекта", chatId, messageId);
                if(textMsg.equals("Project_1"))
                    editMessageCaption.setReplyMarkup(KeyBoard.getKeyBoard().inlineKeyboard("JenkinsAndDocker", "WebDriverProvider"));
                else
                    editMessageCaption.setReplyMarkup(KeyBoard.getKeyBoard().inlineKeyboard("master", ""));
                startTest=ConfigFactoryCreater.commands.startTest() + textMsg;
                execute(editMessageCaption);
            }
            case "Приказ 66" -> {
                RestResponse response=new RestResponse();
                editMessageCaption = SendMessageHelper.getSendMesHelp().editMessageCapt("Приступаем к исполнению", chatId, messageId);
                editMessageCaption.setReplyMarkup(KeyBoard.getKeyBoard().inlineKeyboard("Запустить job'у", "Приказ 66"));
                ProcessHelper.getProcHelp().startProcess(ConfigFactoryCreater.commands.kill()+buildNum+"/term");
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
