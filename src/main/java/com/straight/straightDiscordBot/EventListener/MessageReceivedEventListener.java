package com.straight.straightDiscordBot.EventListener;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Objects;


public class MessageReceivedEventListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent e) {

        if(e.getAuthor().isBot()) return;


        String message = e.getMessage().getContentRaw();
        MessageChannel channel = e.getChannel();

        if(message.equals("!help")){
            EmbedBuilder eb = new EmbedBuilder();

            eb.setTitle("Help-説明");
            eb.setColor(Color.BLUE);
            eb.addField("!vote タイトル@説明@選択肢１/選択肢２...","",false);
            channel.sendMessageEmbeds(eb.build()).queue();
        }
        if(message.equals("!report")){

            EmbedBuilder eb = new EmbedBuilder();

            eb.setTitle("運営への報告");
            eb.setDescription("報告内容をこのDMに送信してください\n※メッセージの頭に必ず「報告 」といれてください\n入れなかった場合、運営は報告を受けることはできません");
            eb.setColor(Color.BLUE);
            eb.addBlankField(true);
            eb.setAuthor("バグ、違反行為等もこちらに");

            e.getAuthor().openPrivateChannel().complete().sendMessageEmbeds(eb.build()).queue();
            e.getMessage().delete().queue();

        }

        if(message.startsWith("!vote ")){

            String voteArgsStr = message.substring(6);
            String[] voteArgs = voteArgsStr.split("@");
            String[] voteChoices = voteArgs[2].split("/");

            if(voteChoices.length <= 1 || voteChoices.length >= 11 || !(voteArgs.length == 3)){
                channel.sendMessage("不正な引数").queue();
                return;
            }

            EmbedBuilder eb = new EmbedBuilder();

            int i = 0;int I = 1;
            for(String string : voteChoices){
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(voteChoices[i]);
                stringBuilder.insert(0,I + "⃣: ");
                string = stringBuilder.toString();
                eb.addField("",string,false);
                i++;I++;
            }


            eb.setTitle(voteArgs[0]);
            eb.setDescription(voteArgs[1]);
            eb.setColor(Color.BLUE);
            eb.addBlankField(false);
            eb.setAuthor(e.getAuthor().getName() + " によるアンケート");
            


            String messageId = channel.sendMessage(eb.build()).complete().getId();
            channel.addReactionById(messageId,"U+0031 U+FE0F U+20E3").queue();
            channel.addReactionById(messageId,"U+0032 U+FE0F U+20E3").queue();
            if(voteChoices.length >= 3) channel.addReactionById(messageId,"U+0033 U+FE0F U+20E3").queue();
            if(voteChoices.length >= 4) channel.addReactionById(messageId,"U+0034 U+FE0F U+20E3").queue();
            if(voteChoices.length >= 5) channel.addReactionById(messageId,"U+0035 U+FE0F U+20E3").queue();
            if(voteChoices.length >= 6) channel.addReactionById(messageId,"U+0036 U+FE0F U+20E3").queue();
            if(voteChoices.length >= 7) channel.addReactionById(messageId,"U+0037 U+FE0F U+20E3").queue();
            if(voteChoices.length >= 8) channel.addReactionById(messageId,"U+0038 U+FE0F U+20E3").queue();
            if(voteChoices.length >= 9) channel.addReactionById(messageId,"U+0039 U+FE0F U+20E3").queue();
            if(voteChoices.length >= 10) channel.addReactionById(messageId,"U+1F51F").queue();


        }

        if(message.contains("@everyone") || message.contains("@here")){
            if(!Objects.requireNonNull(e.getMember()).isOwner()) {
                e.getMessage().delete().queue();
            }
        }
    }

    @Override
    public void onPrivateMessageReceived(@NotNull PrivateMessageReceivedEvent e) {
        if(e.getAuthor().isBot()) return;
        String messageStr = e.getMessage().getContentRaw();
        if(!messageStr.startsWith("報告 ") || !messageStr.startsWith("報告　")) return;
        System.out.println();
    }
}
