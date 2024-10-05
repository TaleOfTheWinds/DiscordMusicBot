package ru.gamerivan.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import ru.gamerivan.ICommand;
import ru.gamerivan.lavaplayer.PlayerManager;

import java.util.List;

public class Quit implements ICommand {
    @Override
    public String getName() {
        return "quit";
    }

    @Override
    public String getDescription() {
        return "quit channel";
    }

    @Override
    public List<OptionData> getOptions() {
        return null;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.reply("Quitting <:pipsya:993915302402326720>").queue();
        PlayerManager playerManager = PlayerManager.getInstance();
        playerManager.clear(event.getGuild());
        event.getGuild().getAudioManager().closeAudioConnection();
    }
}
