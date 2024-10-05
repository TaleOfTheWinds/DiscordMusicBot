package ru.gamerivan.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import ru.gamerivan.ICommand;

import java.util.ArrayList;
import java.util.List;

public class Test implements ICommand {

    @Override
    public String getName() {
        return "test";
    }

    @Override
    public String getDescription() {
        return "test command";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> data = new ArrayList<>();
        data.add(new OptionData(OptionType.STRING, "str", "String type", true));
        return data;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.reply("hello world " + event.getOption("str")).queue();
    }
}
