package com.straight.straightDiscordBot.EventListener;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.internal.entities.RoleImpl;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class RoleEvent extends ListenerAdapter {

    List<User> roleA  = new ArrayList<>();


    @Override
    public void onGuildMessageReactionAdd(@NotNull GuildMessageReactionAddEvent e) {
        if(e.getUser().isBot()) return;

        String roleName = "プレイヤー";
        if(e.getChannel().getName().equals("960098360562442280")) {
            if (!roleA.contains(e.getUser())) {
                roleA.add(e.getUser());
                e.getGuild().addRoleToMember(e.getUserId(), e.getGuild().getRolesByName(roleName, true).get(0)).queue();
            }
        }
    }


    @Override
    public void onGuildMessageReactionRemove(@NotNull GuildMessageReactionRemoveEvent e) {
        if(Objects.requireNonNull(e.getUser()).isBot()) return;

        String roleName = "プレイヤー";
        if(roleA.contains(e.getUser())) {
            if (e.getChannel().getId().equals("960098360562442280")) {
                roleA.remove(e.getUser());
                e.getGuild().removeRoleFromMember(e.getUserId(), e.getGuild().getRolesByName(roleName, true).get(0)).queue();
            }
        }
    }


}
