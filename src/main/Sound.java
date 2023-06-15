package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class Sound {
    Clip clip;
    URL soundURL[] = new URL[30];
    private long clipTimePosition;
    FloatControl fc;
    int volumeScale = 3;
    float volume;

    public Sound(){
        soundURL[0] = getClass().getResource("/sounds/chiptune-grooving.wav");
        soundURL[1] = getClass().getResource("/sounds/sfx_jump.wav");
        soundURL[2] = getClass().getResource("/sounds/cursor.wav");
    }

    public void setFile(int i){
        // Membuka audio file
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();
        } catch (Exception e){
            e.printStackTrace();
        }

        // Jika merupakan musik (bukan sfx), kecilin audionya
        if (i == 0){
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-20.0f);
        }
    }
    public void play(){
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }
    public void pause(){
        clipTimePosition = clip.getMicrosecondPosition();
        clip.stop();
    }
    public void resume(){
        clip.setMicrosecondPosition(clipTimePosition);
        clip.start();
    }

//    ADJUST MUSIC AND SOUND VOLUME
    public void checkVolume() {
        switch (volumeScale) {
            case 0 -> volume = -80f;
            case 1 -> volume = -20f;
            case 2 -> volume = -12f;
            case 3 -> volume = -5f;
            case 4 -> volume = 1f;
            case 5 -> volume = 6f;
        }
        fc.setValue(volume);
    }
}
