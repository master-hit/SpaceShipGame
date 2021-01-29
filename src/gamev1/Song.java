/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamev1;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Moises
 */
public class Song {
    //  FIELDS
    Clip                            clip;
    AudioInputStream                input;
    //  CONSTRUCTOR
    Song() {
        try {
            clip = AudioSystem.getClip();
        }
        catch(LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    //  METHODS
    public void loadSong(String fileName) {
        try {
            input = AudioSystem.getAudioInputStream( new File(fileName));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }
    public void play() {
        try {
            clip.open(input);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        catch(LineUnavailableException e) {
            e.printStackTrace();
        }
        clip.start();
    }
    public void stop() {
        clip.stop();
    }
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}
