package com.straight.straightDiscordBot.EventListener;

import com.straight.straightDiscordBot.Main;
import net.dv8tion.jda.api.events.message.MessageUpdateEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageDeleteEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;


public class TakeLogEventListener extends ListenerAdapter {


    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent e) {
        Main.logger.info("MessageSend " + e.getAuthor() + " " + e.getChannel() + " " + e.getMessage().getContentRaw());
    }

    @Override
    public void onGuildMessageDelete(@NotNull GuildMessageDeleteEvent e) {
        Main.logger.info("MessageDelete " + e.getChannel() + " ID:" + e.getMessageId());
    }

    @Override
    public void onMessageUpdate(@NotNull MessageUpdateEvent e) {
        Main.logger.info("MessageUpdate " + e.getAuthor() + " " + e.getChannel() + " " + e.getMessage().getContentRaw());
    }
}
