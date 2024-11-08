package ru.gamerivan;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import ru.gamerivan.commands.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    private static Properties props = new Properties();
    public static void main(String[] args) throws IOException, URISyntaxException {
        System.setProperty("file.encoding", "UTF-8");
        try {
            props.load(new FileReader(System.getProperty("user.dir") + "/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JDA jda = JDABuilder.createDefault(props.getProperty("discordToken")).build();
        CommandManager manager = new CommandManager();
        manager.add(new Play());
        manager.add(new Test());
        manager.add(new Skip());
        manager.add(new Quit());
        jda.addEventListener(manager);
    }

    public static Properties getProperties() {
        return props;
    }
}