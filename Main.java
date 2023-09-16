package org.example;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    private static String kluch = "6550574569:AAGteiWKvniGzQsSehIJnQlNgymAjiHKXiA";
    private static String id = "1976074735";

    public static void main(String[] args) {

        TelegramBot telegramBot = new TelegramBot(kluch);    //ключ для тєлєгі
        telegramBot.execute(new SendMessage(id, " А ОСЬ І Я "));   //вивод сообщ праця з ним!!
        telegramBot.execute(new SendMessage(id, " Привіт чувак це калькулятор : вводи числа та знаки через пробіл (2 + 2)"));   //вивод сообщ праця з ним!!

        telegramBot.setUpdatesListener(a -> {
            List<Update> updates = a;
            Update update = updates.get(0);
            Message message = update.message();
            String text = message.text();
            int num1 = 0;
            int num2 = 0;
            String znak ;
            String[] examination = text.split(" ");   // ділення строки на числа та знак
            if (examination.length == 3) {          //   перевірка на наявність двух чисел та знаку якщо то заходим
                try {
                    num1 = Integer.parseInt(examination[0]); //перевірка на адекватність 1 числа
                    if (examination[1].length() == 1) {   //перевірка строки знака на один символ якщо так то заходим
                        znak = examination[1];   //присваівання змінній частину строки (символа)
                        if (znak.equals("+") || znak.equals("-") || znak.equals("*") || znak.equals("/")) {
                            try {
                                num2 = Integer.parseInt(examination[2]); //перевірка на адекватність 2 числа
                                telegramBot.execute(new SendMessage(id, "І ОСЬ ЩО Я НАРАХУВАВ : " + count(num1, num2, znak)));//Підрахунок
                            } catch (NumberFormatException ex) {
                                telegramBot.execute(new SendMessage(id, "Друге число не існує "));   //
                                telegramBot.execute(new SendMessage(id, "Зберись"));   //
                            }
                        } else {                 // як що знак не знак дій
                            telegramBot.execute(new SendMessage(id, "Таких дій не існує "));   //
                            telegramBot.execute(new SendMessage(id, "Зберись"));   //
                        }
                    }
                    if (examination[1].length() != 1) {    // як що у знаку більше 1го символу
                        telegramBot.execute(new SendMessage(id, "В дій щось натупив "));   //вивод сообщ праця з ним!!
                        telegramBot.execute(new SendMessage(id, "Зберись"));   //вивод сообщ праця з ним!!
                    }
                } catch (NumberFormatException e) {
                    telegramBot.execute(new SendMessage(id, "Перше число не існує "));//вивод сообщ праця з ним!!
                    telegramBot.execute(new SendMessage(id, "Зберись"));   //вивод сообщ праця з ним!!
                }
            }
            if (examination.length != 3) {
                telegramBot.execute(new SendMessage(id, "Порушення формату вводу "));   //вивод сообщ праця з ним!!
                telegramBot.execute(new SendMessage(id, "Зберись"));   //вивод сообщ праця з ним!!
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }

    public static String count(int a, int b, String c) {

        int num1 = a;
        int num2 = b;
        char znak = c.charAt(0);
        int otv = 0;
        switch (znak) {
            case '+':
                otv = num1 + num2;
                break;
            case '-':
                otv = num1 - num2;
                break;
            case '*':
                otv = num1 * num2;
                break;
            case '/':
                otv = num1 / num2;
                break;
        }
        return String.valueOf(otv);
    }

}

