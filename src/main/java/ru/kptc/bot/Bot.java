package ru.kptc.bot;

import lombok.SneakyThrows;
import org.aeonbits.owner.ConfigFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.kptc.interfaces.All;
import ru.kptc.interfaces.BotConfig;
import ru.kptc.interfaces.Commands;


public class Bot extends TelegramLongPollingBot implements All {
    private final BotConfig cfg = ConfigFactory.create(BotConfig.class);
    private final Commands commands = ConfigFactory.create(Commands.class);

    public Bot() {
        keyBoard.initKeyboard("Меню");
    }

    @Override
    public String getBotUsername() {
        return cfg.botName();
    }

    @Override
    public String getBotToken() {
        return cfg.botToken();
    }
    @Override
    @SneakyThrows
    public void onUpdateReceived(Update update) {
        //Создаем объект класса SendMessage - наш будущий ответ пользователю
        SendMessage outMess = new SendMessage();
        try{
            if(update.hasMessage() && update.getMessage().hasText())
            {
                //Извлекаем из объекта сообщение пользователя
                Message inMess = update.getMessage();
                //Достаем из inMess id чата пользователя
                String chatId = inMess.getChatId().toString();
                //Получаем текст сообщения пользователя, отправляем в написанный нами обработчик
                String response = parseMessage(inMess.getText(),chatId);
                //Добавляем в наше сообщение id чата а также наш ответ
                outMess.setChatId(chatId);
                outMess.setText(response);
                if(inMess.getText().equals("Меню")||inMess.getText().equals("Старт"))
                    outMess.setReplyMarkup(keyBoard.getMarkupInline());
                else
                    outMess.setReplyMarkup(keyBoard.getReplyKeyboardMarkup());
                //Отправка в чат
                execute(outMess);
            }
            else if (update.hasCallbackQuery()) {
                // Set variables
                String call_data = update.getCallbackQuery().getData();
                String chat_id = update.getCallbackQuery().getMessage().getChatId().toString();
                outMess.setChatId(chat_id);

                switch (call_data){
                    case "jobsList" ->{
                        outMess.setText("Список job'ов");
                        outMess.setReplyMarkup(keyBoard.inlineKeyboard("Project 1","Project 1"));
                        execute(outMess);
                    }
                    case "Project 1"->{
                        outMess.setText("Будет сделано!");
                        processHelper.startProcess(commands.startTest());
//                        outMess.setReplyMarkup(keyBoard.inlineKeyboard("Click on me","Oh my, you clicked on me!"));
                        execute(outMess);
                    }
//                    case "Oh my, you clicked on me!"->{
//                        outMess.setText(call_data);
//                        outMess.setReplyMarkup(keyBoard.inlineKeyboard("Click on me again","Oh my, you clicked on me again!"));
//                        execute(outMess);
//                    }
//                    case "Oh my, you clicked on me again!"-> execute(sendHelper.sendPhoto(chat_id,call_data,"/home/yuriy/Pictures/Second.jpg"));
//                    default ->{
//                        outMess.setText("Сообщение не распознано");
//                        execute(outMess);
//                    }
                }
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    @SneakyThrows
    public String parseMessage(String textMsg,String chatId) {
        String response;

        //Сравниваем текст пользователя с нашими командами, на основе этого формируем ответ
        switch (textMsg) {
            case "/start","Старт" ->{
                    response = "Приветствую, бот знает много анекдотов. Жми \"Посмеятся\", чтобы получить случайный из них";
                    keyBoard.inlineKeyboard("Начать работу","старт");
                    return response;
            }
            case "/jen", "Меню" -> {
                execute(sendHelper.sendPhoto(chatId,"","/home/yuriy/Pictures/Fourth.jpg"));
                response="Чего изволите?";
                keyBoard.inlineKeyboard("Запустить job'у","jobsList");
                return response;
            }
            default -> response = "Сообщение не распознано";
        }
        keyBoard.initKeyboard("Меню");
        return response;
    }
}
