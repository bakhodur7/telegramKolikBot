package carbot.project.KazCarBot.service;

public enum ButtonNameEnumInner {
    GET_VOLUME_FIRST("Объем от 0 до 1000 см в кубе"),
    GET_VOLUME_SECOND("Объем от 1001 до 2000 см в кубе"),
    GET_VOLUME_THIRD("Объем от 2001 до 3000 см в кубе"),
    GET_VOLUME_FOURTH("Объем от 3000 см в кубе и больше");

    private final String buttonName2;

    ButtonNameEnumInner(String buttonName2) {
        this.buttonName2 = buttonName2;
    }

    public String getButtonName() {
        return buttonName2;
    }
}
