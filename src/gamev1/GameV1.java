/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamev1;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author Moises
 */
public class GameV1 extends Canvas {
    public static void main(String[] args) {
        new GameV1().run();
    }
    //  FIELDS
    Song                                song;
    Dimension                           dmnsn;
    Spaceship                           spaceship;
    JFrame                              window;
    boolean                             running = true;
    final double                        TIME_FOR_EACH_FRAME = 1000000000/70;     // 1fr * 1sec/60fr
    BufferStrategy                      bs;
    //  CONSTRUCTOR
    public GameV1() {
        init();
        loadContent();
    }
    //  METHODS
    private void init() {
        song = new Song();
        spaceship = new Spaceship(5);
        dmnsn = new Dimension(800,800);
        setPreferredSize(dmnsn);
        
        window = new JFrame();
        window.add(this);
        window.pack();
        window.addKeyListener(spaceship);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

        createBufferStrategy(3);
        bs = getBufferStrategy();
    }
    private void loadContent() {
        spaceship.addFrame(loadImage("/resources/ship/ship00.png"));
        spaceship.addFrame(loadImage("/resources/ship/ship01.png"));
        spaceship.addFrame(loadImage("/resources/ship/ship02.png"));
        spaceship.addFrame(loadImage("/resources/ship/ship03.png"));
        spaceship.addFrame(loadImage("/resources/ship/ship04.png"));
        spaceship.addFrame(loadImage("/resources/ship/ship05.png"));
        spaceship.addFrame(loadImage("/resources/ship/ship06.png"));
        spaceship.addFrame(loadImage("/resources/ship/ship07.png"));
        spaceship.addFrame(loadImage("/resources/ship/ship08.png"));
        spaceship.addFrame(loadImage("/resources/ship/ship09.png"));
        spaceship.addFrame(loadImage("/resources/ship/ship10.png"));
        
        song.loadSong("C:\\Users\\Moises\\Documents\\NetBeansProjects\\GameV1\\src\\resources\\music\\space game music_WAV.wav");
    }
    private Image loadImage(String fileName) {
        return new ImageIcon(getClass().getResource(fileName)).getImage();
    }
    public void run() {
        int     frames = 0;                                                     // stores the number of frames
        double time0 = System.nanoTime();                                       // stores the initial of the run method
        double time1;                                                           // stores the initial time of the loop
        double elapsed_time0 = 0;                                               // store the time it takes to update and render
        double elapsed_time1 = 0;                                               // stores the time it takes to execute the loop
        double wait;                                                            // stores the time remaining to update and render the screen
        song.play();
        song.loop();
        while (running) {
            time1 = System.nanoTime();
            update((long)elapsed_time1);
            render();
            frames++;
            elapsed_time0 = System.nanoTime() - time1;
            
            wait = (TIME_FOR_EACH_FRAME - elapsed_time0) * 1/1000000.0;         // nanosec to milisecs
            if (wait < 0) { wait = 0; System.out.println("wait = 0"); }
            try {Thread.sleep((long)wait); } catch(InterruptedException e){e.printStackTrace();}
            System.out.println("wait = " + wait);
            elapsed_time1 = System.nanoTime() - time1;
            
            time0 += elapsed_time1;
            if (time0 >= 1000000000) {
                window.setTitle("FPS = " + frames);
                frames = 0;
                time0 = 0;
            }
        }
        song.stop();
    }
    public void update(long elapsedTime) {
        spaceship.update();
    }
    public void render() {
        Graphics2D g = (Graphics2D)bs.getDrawGraphics();
        {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, dmnsn.width, dmnsn.height);
            g.drawImage(spaceship.getImage(),spaceship.getX(),spaceship.getY(),null);
            
        }
        g.dispose();
        if (!bs.contentsLost())
            bs.show();
        Toolkit.getDefaultToolkit().sync();
    }
}
