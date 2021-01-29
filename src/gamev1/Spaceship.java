/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamev1;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 *  this is a special animation used for the main character of the game
 * @author Moises
 */
public class Spaceship implements KeyListener {
    //  FIELDS
    private ArrayList<Image>                        frames;                     // stores the all the frames for our character
    private int                                     x = 300;                    // position in x
    private int                                     y = 300;                    // position in y
    private int                                     horizontal = 0;             // this variable stores the horizontal direction the spaceship is moving (right = 1) (left = -1) (no moving = 0)
    private int                                     vertical = 0;               // this variable stores the vertical diraction this spaceship is moving (up = -1) (down = 1) (no moving = 0)
    private int                                     index;                      // points to a certain frame 
    private int                                     next = 0;                   // points to the next image
    
    //  CONSTRUCTOR
    Spaceship(int index) {
        frames = new ArrayList<Image>();
        this.index = index;
    }
    
    //  METHODS
    /**
     * adds a frame to the Spaceship animation
     * @param image the image to be displayed
     */
    public void addFrame(Image image/* long nanoseconds*/) {
        frames.add(image);
    }
    
    /**
     * this methods is the mechanism to get the animation to be displayed
     * @return the image to be displayed
     */
    public Image getImage() {
        return (Image)frames.get(index);
    }
    
    /**
     * this methods is used to return the current x position of the this spaceship
     * @return the x position
     */
    public int getX() {
        return x;
    }
    
    /**
     * this methods is used to return the current y position of the this spaceship
     * @return the y position
     */
    public int getY() {
        return y;
    }
    
    
    /**
     * this methods is used to update the spaceship position
     */
    public void update() {
        x = x + horizontal;
        y = y + vertical;
        if(horizontal > 0 && (index+next) < frames.size()){
            index = index + next;
        }
        if(horizontal < 0 && (index+next) >= 0) {
            index = index + next;
        } 
    }
    
    
    //  OVERRIDING METHODS
    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        switch(ke.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                horizontal = 2;
                next = 1;
                break;
            case KeyEvent.VK_LEFT:
                horizontal = -2;
                next = -1;
                break;
            case KeyEvent.VK_DOWN:
                vertical = 2;
                break;
            case KeyEvent.VK_UP:
                vertical = -2;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        switch(ke.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
//                horizontal = 0;
                break;
            case KeyEvent.VK_LEFT:
//                horizontal = 0;
                break;
            case KeyEvent.VK_DOWN:
//                vertical = 0;
                break;
            case KeyEvent.VK_UP:
//                vertical = 0;
                break;
        }

    }
}
