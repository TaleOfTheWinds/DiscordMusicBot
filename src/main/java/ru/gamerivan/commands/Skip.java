package ru.gamerivan.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import ru.gamerivan.ICommand;
import ru.gamerivan.lavaplayer.PlayerManager;

import java.util.ArrayList;
import java.util.List;

public class Skip implements ICommand {
    @Override
    public String getName() {
        return "skip";
    }

    @Override
    public String getDescription() {
        return "skip track";
    }

    @Override
    public List<OptionData> getOptions() {
        return null;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        PlayerManager playerManager = PlayerManager.getInstance();
        event.reply("Skipped <:pipsya:993915302402326720>").queue();
        playerManager.skip(event.getGuild());
    }
}
