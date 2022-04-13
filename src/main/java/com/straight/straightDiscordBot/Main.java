package com.straight.straightDiscordBot;

import com.straight.straightDiscordBot.EventListener.MessageReceivedEventListener;
import com.straight.straightDiscordBot.EventListener.RoleEvent;
import com.straight.straightDiscordBot.EventListener.TakeLogEventListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.internal.utils.JDALogger;
import org.slf4j.Logger;

import javax.security.auth.login.LoginException;
import java.util.Date;
import java.util.Objects;

public class Main{

    public static final Logger logger = JDALogger.getLog("BOT");

    public static void main(String[] args) {

        logger.info(String.valueOf(new Date()));


        JDA jda = null;

        try {
            jda = JDABuilder.createDefault(System.getenv("")).build();
        } catch (LoginException e) {
            e.printStackTrace();
        }

        Objects.requireNonNull(jda).addEventListener(
                new MessageReceivedEventListener(),
                new TakeLogEventListener(),
                new RoleEvent()
        );

    }

}
