package io.jfxdevelop.lab_7_3_part;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class BotLauncher {
    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new MyBot());
            System.out.println("Бот запущен.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
