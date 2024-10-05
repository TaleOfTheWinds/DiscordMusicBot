package ru.gamerivan;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import ru.gamerivan.commands.*;

public class Main {
    public static void main(String[] args) {
        JDA jda = JDABuilder.createDefault("MTI5MjA0NTE5MDQ4NzYwNTI0OA.GWlxBR.U58f53dVRCsJow-p9w2NFmHlU2AZhSLpUqwf2A").build();
        CommandManager manager = new CommandManager();
        manager.add(new Play());
        manager.add(new Test());
        manager.add(new Skip());
        manager.add(new Quit());
        jda.addEventListener(manager);
    }
}