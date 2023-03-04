package carbot.project.KazCarBot.service;
import carbot.project.KazCarBot.service.TelegramBot;
import com.vdurmont.emoji.EmojiParser;

public enum ButtonLastAnswer {


    GET_CALC_PRICE(EmojiParser.parseToUnicode(":recycle:")+ "Рассчитать стоимость авто"),
    GET_BACK(EmojiParser.parseToUnicode(":house:") + "Назад");

    private final String buttonName;
    ButtonLastAnswer(String buttonName) {
        this.buttonName = buttonName;
    }

    public String getButtonName() {
        return buttonName;
    }
}
