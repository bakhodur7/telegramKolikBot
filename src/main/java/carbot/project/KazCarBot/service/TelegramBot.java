package carbot.project.KazCarBot.service;

import carbot.project.KazCarBot.config.BotConfig;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.vdurmont.emoji.EmojiParser;

import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class TelegramBot extends TelegramLongPollingBot {

    public String check = EmojiParser.parseToUnicode(":white_check_mark:");
    public String recycle_emoji = EmojiParser.parseToUnicode(":recycle:");
    public String house_emoji = EmojiParser.parseToUnicode(":house:");
    public String kz_emoji = EmojiParser.parseToUnicode(":kz:");
    public String pen_emoji = EmojiParser.parseToUnicode(":lower_left_ballpoint_pen:");
    public String down_emoji = EmojiParser.parseToUnicode(":arrow_down:");
    public String pushpin_emoji = EmojiParser.parseToUnicode(":pushpin:");


    final BotConfig config;
    public TelegramBot(BotConfig config){
        this.config = config;
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    String str;
    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //метод проверяющий, является ли введенный текст числом
    }
    public static boolean isDouble(String dig){
        double x = Double.parseDouble(dig);
        if (x == Math.floor(x)){
            return false;
        }else{
            return true;
        }
    }
//    String botState = "start";
//    String botState1 = null;
//    String botState2 = null;
//    String botState3 = null;
//    String botState4 = null;
//    String botState5 = null;
    double koef_util;
    double koef_age;

    int total = 0;
    int aktciz = 0;
    int resultNds = 0;
    int resultUtilInt = 0;
    int resultInitReg = 0;
    int price_of_car = 0;
    int resultNdsChisto = 0;

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {

            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

                if (messageText.equals("/start") || messageText.equals(house_emoji + "Назад")){
                    startCommandReceived(chatId, update.getMessage().getChat().getFirstName());


                }if (messageText.equals(pen_emoji + "Ввести стоимость авто")) {
                    sendMessage(chatId, pen_emoji +"Введите стоимость автомобиля в тенге. Введите только число, без пробелов. " +
                            "\n" +
                            "\nНапример: автомобиль стоит 8000000 тенге, значит вы должны ввести число: 8000000" +
                            "\nВведите сумму свыше 2000000 тенге" + down_emoji);
                    Date d = new Date();
                    System.out.println("\n" +
                            "Начало расчета:" +
                            "\nИмя: " + update.getMessage().getChat().getFirstName() +
                            "\nЧатайди: " + chatId+
                            "\nВремя: " + d);
                }if(isNumeric(messageText) && isDouble(messageText) == false && (Integer.parseInt (messageText) > 2000000)){

                        price_of_car =Integer.parseInt (messageText);
                        String formattedNumberMessagePrice = String.format("%,d", price_of_car);
                        Date d = new Date();
                        System.out.println("Цена вашей машины: " + formattedNumberMessagePrice +
                                        "\nИмя: " + update.getMessage().getChat().getFirstName() +
                                        "\nЧатайди: " + chatId +
                                        "\nВремя: " + d);
                        sendMessageKeyVolume(chatId, check + "Отлично, цена вашей машины: " + formattedNumberMessagePrice + " тенге. " + "\n" +
                                "\nТеперь выберите объем двигателя"+ down_emoji + "" +
                                "\n1л - это 1000 см в кубе" +
                                "\nНапример, если объем двигателя авто 2.5л - это 2500 см в кубе, " +
                                "значит вы должны выбрать кнопку от 2001 до 3000 см в кубе.");

                        price_of_car =Integer.parseInt (messageText);
                        int customs_sbor = 20000;
                        int resultCustomsValue = price_of_car + customs_sbor;
                        int resultCustomsDuty = ((resultCustomsValue * 15)/100) + resultCustomsValue;
                        resultNds = ((resultCustomsDuty *12)/100) + resultCustomsDuty;

                        resultNdsChisto = resultNds - price_of_car;


                }if((messageText.equals("До 2-х лет")) || (messageText.equals("От 2-х до 3-х лет")) || (messageText.equals("Старше 3-х лет"))){
                    int mrp = 3450;

                    switch (messageText){
                        case "До 2-х лет":
                            sendMessageLastAnswer(chatId, check + "Отлично, возраст вашего авто до 2-х лет" +
                                    "\nТеперь нажмите кнопку: " + recycle_emoji + "Рассчитать стоимость авто");
                            koef_age = 0.25;

                            break;
                        case "От 2-х до 3-х лет":
                            sendMessageLastAnswer(chatId, check + "Отлично, возраст вашего авто от 2-х до 3-х"+
                                    "\nТеперь нажмите кнопку: " + recycle_emoji + "Рассчитать стоимость авто");
                            koef_age = 50;

                            break;
                        case "Старше 3-х лет":
                            sendMessageLastAnswer(chatId, check + "Отлично, возраст вашего авто больше 3-х лет"+
                                    "\nТеперь нажмите кнопку: " + recycle_emoji + "Рассчитать стоимость авто");
                            koef_age = 500;

                            break;
                    }
                    double resultInitRegDouble = koef_age*mrp;
                    resultInitReg = (int)resultInitRegDouble;
                    System.out.println("Первичная регистрация: " + resultInitReg);

                }if((messageText.equals("Объем от 0 до 1000 см в кубе") || messageText.equals("Объем от 1001 до 2000 см в кубе") ||
                        messageText.equals("Объем от 2001 до 3000 см в кубе") || messageText.equals("Объем от 3000 см в кубе и больше"))){

                    int mrp = 3450;
                    int base_rate = 50*mrp; //базовая ставка

                    switch (messageText){
                        case "Объем от 0 до 1000 см в кубе":
                            koef_util = 1.5;
                            sendMessageKeyAge(chatId, check + "Принято, объем от 0 до 1000 см в кубе" +
                                    "\nТеперь из нижнего меню выберите возраст вашего авто" + down_emoji);
                            break;
                        case "Объем от 1001 до 2000 см в кубе":
                            koef_util = 3.5;
                            sendMessageKeyAge(chatId, check + "Принято, объем от 1001 до 2000 см в кубе" +
                                    "\nТеперь из нижнего меню выберите возраст вашего авто" + down_emoji);
                            break;
                        case "Объем от 2001 до 3000 см в кубе":
                            koef_util = 5;
                            sendMessageKeyAge(chatId, check + "Принято, объем от 2001 до 3000 см в кубе" +
                                    "\nТеперь из нижнего меню выберите возраст вашего авто" + down_emoji);
                            break;
                        case "Объем от 3000 см в кубе и больше":
                            koef_util = 11.5;

                            sendMessageKeyVolume2(chatId, "Напишите точно объем вашего авто" +
                                    "\nНапример если объем 3.5 литра, введите число: 3.5" + down_emoji);
                            break;
                    }
                    double resultUtilDouble = koef_util*base_rate;
                    resultUtilInt = (int)resultUtilDouble;
                    System.out.println("Утильсбор: " + resultUtilInt);


                }
            total = resultNds + resultInitReg + resultUtilInt + aktciz + 100000;

            String formattedNumberTotal = String.format("%,d", total);
            String formattedNumberPriceOfCar = String.format("%,d", price_of_car);
            int tamojka = total - price_of_car;
            String formattedNumberTamojka = String.format("%,d", tamojka);
            String formattedNumberResultNdsChisto = String.format("%,d", resultNdsChisto);
            String formattedNumberResultUtilInt = String.format("%,d", resultUtilInt);
            String formattedNumberResultInitReg = String.format("%,d", resultInitReg);
            String formattedNumberResultAktciz = String.format("%,d", aktciz);



                if(messageText.equals(recycle_emoji + "Рассчитать стоимость авто")){
                sendMessage(chatId, pushpin_emoji + "Окончательная стоимость вашего авто: \n" + formattedNumberPriceOfCar + " + " + (formattedNumberTamojka) + " = " + formattedNumberTotal + " тенге." +
                        "\n" +
                        "\nИз них: " +
                        "\nСтоимость вашего авто: " + formattedNumberPriceOfCar + " тенге." +
                        "\nТаможенная пошлина и НДС: " + formattedNumberResultNdsChisto + " тенге." +
                        "\nУтильсбор: " + formattedNumberResultUtilInt + " тенге." +
                        "\nСБКТС:  100 000" + " тенге." +
                        "\nСбор за первичную регистрацию: " + formattedNumberResultInitReg + " тенге." +
                        "\nАкциз равен = " + formattedNumberResultAktciz + " тенге" +
                        "\n"
                        );
                    System.out.println("Акциз: " + formattedNumberResultAktciz);
                    System.out.println("Окончательная цена Вашего авто с растаможкой и регистрацией = " + formattedNumberTotal + " тенге");
                    System.out.println("Рассчет завершен успешно.");

                }if((isDouble(messageText) && ((Double.parseDouble(messageText)) > 3))){
                    sendMessageKeyAge(chatId, check +"Принято, объем вашей машины: " + messageText + " литров " +
                            "\nТеперь из нижнего меню выберите возраст вашего авто" + down_emoji);
                    double volume_for_aktciz = Double.parseDouble(messageText); //точный объем нужен для подсчета акциза
                    double aktcizDouble = volume_for_aktciz*1000*100;
                    aktciz = (int)aktcizDouble;


                }
//            if(((Double.parseDouble(messageText))==(4.0) || (Double.parseDouble(messageText))==(5.0) ||
//                    (Double.parseDouble(messageText))==(6.0) || (Double.parseDouble(messageText))==(7.0) || (Double.parseDouble(messageText))==(8.0))){
//                sendMessageKeyAge(chatId, check +"Принято, объем вашей машины: " + messageText + " литров " +
//                        "\nТеперь из нижнего меню выберите возраст вашего авто" + down_emoji);
//                double volume_for_aktciz = Double.parseDouble(messageText); //точный объем нужен для подсчета акциза
//                double aktcizDouble = volume_for_aktciz*1000*100;
//                aktciz = (int)aktcizDouble;
//            }
                else{
                    aktciz = 0;
                }

                total = 0;
        }
    }



            private void startCommandReceived ( long chatId, String name){
                String answer = "Привет, " + name + "! Здесь ты можешь посчитать сумму растаможки " +
                        "автомобиля ввезенного из-за границы в Казахстан" + kz_emoji;
                log.info("Replied to user: " + name);

                sendMessage(chatId, answer);
            }
            ReplyKeyboardMaker replyKeyboardMaker = new ReplyKeyboardMaker();

            private void sendMessageKeyVolume(long chatId, String textToSend){
                SendMessage message1 = new SendMessage();
                message1.setChatId(String.valueOf(chatId));
                message1.setText(textToSend);

                message1.setReplyMarkup(replyKeyboardMaker.getMainMenuKeyboardVolume());

                try{
                    execute(message1);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

    private void sendMessageKeyVolume2(long chatId, String textToSend){
        SendMessage messageV = new SendMessage();
        messageV.setChatId(String.valueOf(chatId));
        messageV.setText(textToSend);
        try{
            execute(messageV);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendMessageKeyAge(long chatId, String textToSend){
        SendMessage message2 = new SendMessage();
        message2.setChatId(String.valueOf(chatId));
        message2.setText(textToSend);
        message2.setReplyMarkup(replyKeyboardMaker.getMainMenuKeyboardAge());

        try{
            execute(message2);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendMessageLastAnswer(long chatId, String textToSend){
        SendMessage messageLast = new SendMessage();
        messageLast.setChatId(String.valueOf(chatId));
        messageLast.setText(textToSend);

        messageLast.setReplyMarkup(replyKeyboardMaker.getMainMenuKeyboardLastAnswer());
        try {
            execute(messageLast);
        } catch (TelegramApiException e) {
            log.error("Error occured: " + e.getMessage());
        }
    }

            private void sendMessage(long chatId, String textToSend){
                SendMessage message = new SendMessage();
                message.setChatId(String.valueOf(chatId));
                message.setText(textToSend);

                message.setReplyMarkup(replyKeyboardMaker.getMainMenuKeyboard());
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    log.error("Error occured: " + e.getMessage());
                }
            }

        }