package carbot.project.KazCarBot.service;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;

@Component
public class ReplyKeyboardMaker {
    public ReplyKeyboardMarkup getMainMenuKeyboard() {
        KeyboardRow row1 = new KeyboardRow();
        row1.add(new KeyboardButton(ButtonNameEnum.GET_PRICE_BUTTON.getButtonName())); // Кнопка указать стоимость
        row1.add(new KeyboardButton(ButtonNameEnum.GET_BACK_BUTTON.getButtonName())); // Кнопка назад или сброс

//        KeyboardRow row2 = new KeyboardRow();
//        row2.add(new KeyboardButton(ButtonNameEnum.GET_VOLUME_BUTTON.getButtonName()));
//        row2.add(new KeyboardButton(ButtonNameEnum.HELP_BUTTON.getButtonName()));

        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row1);
        //keyboard.add(row2);

        final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboard);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        return replyKeyboardMarkup;
    }
    public ReplyKeyboardMarkup getMainMenuKeyboardVolume() {
        KeyboardRow row1 = new KeyboardRow();
        row1.add(new KeyboardButton(ButtonNameEnumInner.GET_VOLUME_FIRST.getButtonName()));
        row1.add(new KeyboardButton(ButtonNameEnumInner.GET_VOLUME_SECOND.getButtonName()));

        KeyboardRow row2 = new KeyboardRow();
        row2.add(new KeyboardButton(ButtonNameEnumInner.GET_VOLUME_THIRD.getButtonName()));
        row2.add(new KeyboardButton(ButtonNameEnumInner.GET_VOLUME_FOURTH.getButtonName()));

        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row1);
        keyboard.add(row2);

        final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboard);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        return replyKeyboardMarkup;
    }

    public ReplyKeyboardMarkup getMainMenuKeyboardVolume2() {
        KeyboardRow row1 = new KeyboardRow();
        row1.add(new KeyboardButton(ButtonNameEnumInner.GET_VOLUME_FIRST.getButtonName()));
        row1.add(new KeyboardButton(ButtonNameEnumInner.GET_VOLUME_SECOND.getButtonName()));

        KeyboardRow row2 = new KeyboardRow();
        row2.add(new KeyboardButton(ButtonNameEnumInner.GET_VOLUME_THIRD.getButtonName()));
        row2.add(new KeyboardButton(ButtonNameEnumInner.GET_VOLUME_FOURTH.getButtonName()));

        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row1);
        keyboard.add(row2);

        final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboard);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        return replyKeyboardMarkup;
    }

    public ReplyKeyboardMarkup getMainMenuKeyboardLastAnswer() {
        KeyboardRow row1 = new KeyboardRow();
        row1.add(new KeyboardButton(ButtonLastAnswer.GET_CALC_PRICE.getButtonName()));
        row1.add(new KeyboardButton(ButtonLastAnswer.GET_BACK.getButtonName()));

//        KeyboardRow row2 = new KeyboardRow();
//        row2.add(new KeyboardButton(ButtonNameEnumInner.GET_VOLUME_THIRD.getButtonName()));
//        row2.add(new KeyboardButton(ButtonNameEnumInner.GET_VOLUME_FOURTH.getButtonName()));

        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row1);

        final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboard);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        return replyKeyboardMarkup;
    }

    public ReplyKeyboardMarkup getMainMenuKeyboardAge() {
        KeyboardRow row1 = new KeyboardRow();
        row1.add(new KeyboardButton(ButtonNameAge.AGE_FIRST.getButtonName()));
        row1.add(new KeyboardButton(ButtonNameAge.AGE_SECOND.getButtonName()));

        KeyboardRow row2 = new KeyboardRow();
        row2.add(new KeyboardButton(ButtonNameAge.AGE_THIRD.getButtonName()));
        row2.add(new KeyboardButton(ButtonNameAge.AGE_FOURTH.getButtonName()));

        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row1);
        keyboard.add(row2);

        final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboard);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        return replyKeyboardMarkup;
    }
}
