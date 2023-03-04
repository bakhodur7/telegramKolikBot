package carbot.project.KazCarBot.service;

import com.vdurmont.emoji.EmojiParser;

public enum ButtonNameAge {
    AGE_FIRST("До 2-х лет"),
    AGE_SECOND("От 2-х до 3-х лет"),
    AGE_THIRD("Старше 3-х лет"),
    AGE_FOURTH(EmojiParser.parseToUnicode(":house:") + "Назад");

    private final String buttonName3;

    ButtonNameAge(String buttonName3) {
        this.buttonName3 = buttonName3;
    }

    public String getButtonName() {
        return buttonName3;
    }
}