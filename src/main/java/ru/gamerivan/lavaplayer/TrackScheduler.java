package ru.gamerivan.lavaplayer;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import net.dv8tion.jda.api.entities.Guild;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TransferQueue;

public class TrackScheduler extends AudioEventAdapter {

    private final AudioPlayer player;
    private final BlockingQueue<AudioTrack> queue = new LinkedBlockingQueue<>();
    private final Guild guild;
    private final Logger logger = LoggerFactory.getLogger(TransferQueue.class);

    public TrackScheduler(AudioPlayer player, Guild guild) {
        this.player = player;
        this.guild = guild;
    }

    @Override
    public void onTrackException(AudioPlayer player, AudioTrack track, FriendlyException exception) {
        exception.printStackTrace();
        if (exception.getMessage().equals("Please sign in")) { // TODO exception.getCause() - read timeout
            logger.warn("\n-----------------\nPlease sign in ^^^\nPlease sign in ^^^\nPlease sign in ^^^\n-----------------\n");
        } else if (exception.getMessage().contains("The uploader has not made this video available in your country")) {
            logger.warn("\n-----------------\nThe uploader has not made this video available in your country\n-----------------\n");
        } else queue(track.makeClone());

    }

    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
        boolean b = player.startTrack(queue.poll(), false);
        if (!b) {
            PlayerManager playerManager = PlayerManager.getInstance();
            playerManager.clear(guild);
            guild.getAudioManager().closeAudioConnection();
        }
        logger.info("track end; size: " + queue.size());
    }

    public void queue(AudioTrack track) {
        if (!player.startTrack(track, true)) {
            queue.offer(track);
        }
        logger.info("added track; size: " + queue.size());
    }

    public void skip() {
        player.stopTrack();
        logger.info("skipped track; size: " + queue.size());
    }

    public void clear() {
        queue.clear();
        player.stopTrack();
    }
}
