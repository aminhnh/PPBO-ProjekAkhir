package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class Sound {
//    Attribute
    private Clip clip;
    private
    URL soundURL[] = new URL[30];
    private long clipTimePosition;
    private FloatControl fc;
    private int volumeScale = 3;
    private float volume;

//    Constructor
    public Sound(){
        soundURL[0] = getClass().getResource("/sounds/chiptune-grooving.wav");
        soundURL[1] = getClass().getResource("/sounds/sfx_jump.wav");
        soundURL[2] = getClass().getResource("/sounds/cursor.wav");
        soundURL[3] = getClass().getResource("/sounds/sfx_dead.wav");
    }

//    Getter Setter
    public Clip getClip() {
        return clip;
    }

    public void setClip(Clip clip) {
        this.clip = clip;
    }

    public URL[] getSoundURL() {
        return soundURL;
    }

    public void setSoundURL(URL[] soundURL) {
        this.soundURL = soundURL;
    }

    public long getClipTimePosition() {
        return clipTimePosition;
    }

    public void setClipTimePosition(long clipTimePosition) {
        this.clipTimePosition = clipTimePosition;
    }

    public FloatControl getFc() {
        return fc;
    }

    public void setFc(FloatControl fc) {
        this.fc = fc;
    }

    public int getVolumeScale() {
        return volumeScale;
    }

    public void setVolumeScale(int volumeScale) {
        this.volumeScale = volumeScale;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    //    Method
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
