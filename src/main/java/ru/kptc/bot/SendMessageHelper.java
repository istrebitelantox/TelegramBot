package ru.kptc.bot;

import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import ru.kptc.interfaces.All;

import java.io.File;

public class SendMessageHelper implements All {
    public SendMessage sendMessage(String answer, String chat_id){
        SendMessage new_message = new SendMessage();
        new_message.setChatId(chat_id);
        new_message.setText(answer);
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
