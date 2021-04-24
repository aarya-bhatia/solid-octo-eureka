package com.aarya.rain.audio;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class SoundClip {

    private Clip clip;
    private FloatControl gainControl;

    public SoundClip(String path) {
        try {
            InputStream audioSource = SoundClip.class.getResourceAsStream(path);
            InputStream bufferedIn = new BufferedInputStream(audioSource);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bufferedIn);
            AudioFormat baseFormat = audioInputStream.getFormat();
            AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                    baseFormat.getSampleRate(),
                    16,
                    baseFormat.getChannels(),
                    baseFormat.getChannels() * 2,
                    baseFormat.getSampleRate(),
                    false);

            AudioInputStream decodedInputStream = AudioSystem.getAudioInputStream(decodeFormat, audioInputStream);

            clip = AudioSystem.getClip();
            clip.open(decodedInputStream);

            gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public synchronized void play() {
        if(clip == null) { return; }
        stop();
        clip.setFramePosition(0);
        while(!clip.isRunning()) {
            clip.start();
        }
    }

    public synchronized void stop() {
        if(clip == null) { return; }
        if(clip.isRunning()) { clip.stop(); }
    }

    public synchronized void close() {
        stop();
        clip.drain();
        clip.close();
    }

    public synchronized void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        play();
    }

    public synchronized void setVolume(float value) {
        gainControl.setValue(value);
    }

    public boolean isRunning() {
        return clip.isRunning();
    }

}
