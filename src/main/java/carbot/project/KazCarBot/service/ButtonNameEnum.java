package carbot.project.KazCarBot.service;

import com.vdurmont.emoji.EmojiParser;

public enum ButtonNameEnum {

    GET_PRICE_BUTTON(EmojiParser.parseToUnicode(":lower_left_ballpoint_pen:") + "Ввести стоимость авто"),
    GET_BACK_BUTTON(EmojiParser.parseToUnicode(":house:") + "Назад"),
    GET_VOLUME_BUTTON("Выбрать объем авто"),
    HELP_BUTTON("Помощь");

    private final String buttonName;

    ButtonNameEnum(String buttonName) {
        this.buttonName = buttonName;
    }

    public String getButtonName() {
        return buttonName;
    }
}
