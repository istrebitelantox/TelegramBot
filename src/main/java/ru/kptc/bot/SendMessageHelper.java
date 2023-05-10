package ru.kptc.bot;

import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageCaption;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageMedia;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;

import java.io.File;

import static java.lang.Math.toIntExact;

public class SendMessageHelper {
    private static SendMessageHelper sendMessageHelper = null;

    public static synchronized SendMessageHelper getSendMesHelp() {
        if (sendMessageHelper == null) {
            sendMessageHelper = new SendMessageHelper();
        }

        return sendMessageHelper;
    }
    private SendMessageHelper(){

    }
    public SendMessage sendMessage(String answer, String chat_id){
        SendMessage new_message = new SendMessage();
        new_message.setChatId(chat_id);
        new_message.setText(answer);
        return new_message;
    }
    public EditMessageText editMessage(String answer, String chat_id, long message_id){
        EditMessageText new_message = new EditMessageText();
        new_message.setChatId(chat_id);
        new_message.setMessageId(toIntExact(message_id));
        new_message.setText(answer);
        return new_message;
    }
    public EditMessageCaption editMessageCapt(String answer, String chat_id, long message_id){
        EditMessageCaption new_message = new EditMessageCaption();
        new_message.setChatId(chat_id);
        new_message.setMessageId(toIntExact(message_id));
        new_message.setCaption(answer);
//        new_message.setText(answer);
        return new_message;
    }
    public EditMessageMedia editMessageMedia(String imagePath,String answer, String chat_id, long message_id){
        InputMediaPhoto inputMediaPhoto=new InputMediaPhoto(imagePath);
        inputMediaPhoto.setCaption(answer);
        EditMessageMedia new_message = new EditMessageMedia();
        new_message.setChatId(chat_id);
        new_message.setMessageId(toIntExact(message_id));
        new_message.setMedia(inputMediaPhoto);
//        new_message.setText(answer);
        return new_message;
    }
    public EditMessageReplyMarkup editMessageInKey( String chat_id, long message_id){
        EditMessageReplyMarkup new_message = new EditMessageReplyMarkup();
        new_message.setChatId(chat_id);
        new_message.setMessageId(toIntExact(message_id));
        new_message.setReplyMarkup(null);
        return new_message;
    }
    @SneakyThrows
    public SendPhoto sendPhoto(String chatId, String imageCaption, String imagePath) {
        InputFile file=new InputFile(new File(imagePath));
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setPhoto(file);
        sendPhoto.setChatId(chatId);
        sendPhoto.setCaption(imageCaption);
        return sendPhoto;
    }
}
