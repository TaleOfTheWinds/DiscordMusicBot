package ru.gamerivan.lavaplayer;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import net.dv8tion.jda.api.entities.Guild;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TrackScheduler extends AudioEventAdapter {

    private final AudioPlayer player;
    private final BlockingQueue<AudioTrack> queue = new LinkedBlockingQueue<>();
    private final Guild guild;

    public TrackScheduler(AudioPlayer player, Guild guild) {
        this.player = player;
        this.guild = guild;
    }

    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
        boolean b = player.startTrack(queue.poll(), false);
        if (!b) {
            PlayerManager playerManager = PlayerManager.getInstance();
            playerManager.clear(guild);
            guild.getAudioManager().closeAudioConnection();
        }
    }

    public void queue(AudioTrack track) {
        if (!player.startTrack(track, true)) {
            queue.offer(track);
        }
        System.out.println("added track; size: " + queue.size());
    }

    public void skip() {
        player.stopTrack();
        System.out.println("skipped track; size: " + queue.size());
    }

    public void clear() {
        queue.clear();
        player.stopTrack();
    }
}
