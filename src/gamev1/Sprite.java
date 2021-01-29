/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamev1;

import java.awt.Image;

/**
 * a class representing a animated graphic object that moves independently around the screen.
 */
public class Sprite {
    private Animation anim;
    private float x;
    private float y;
    private float dx;   // in pixel per milliseconds
    private float dy;   // in pixel per milliseconds

    /**
     * Creates a new Sprite object with the specified Animation.
     */
    public Sprite(Animation anim) {
        this.anim = anim;
    }
    
    /**
     * Updates this Sprite's animation and its position based on the velocity.
     */
    public void update(long elapsedTime) {
       // x += dx * elapsedTime;              
       // y += dy * elapsedTime;              
        anim.update(elapsedTime);
    }
    
    public float getX() { return x; }
    public float getY() { return y; }
    public void setX(float x) { this.x = x; }
    public void setY(float y) { this.y = y; }
    /**
     * Gets this Sprite's width, based on the size of the current image.
     */
    public int getWidth() {
        return anim.getImage().getWidth(null);
    }
    /**
     * Gets this Sprite's height, based on the size of the current image
     * @return the size of the current image
     */
    public int getHeight() { return anim.getImage().getHeight(null); }
    /**
     * gets the horizontal velocity of this Sprite in pixels per milliseconds.
     * @return 
     */
    public float getVelocityX() { return dx; }
    /**
     * gets the vertical velocity of this Sprite in pixel per milliseconds.
     * @return the vertical velocity in pixel per milliseconds
     */
    public float getVelocityY() { return dy; }
    /**
     * Sets the horizontal velocity of this Sprite in pixels per milliseconds.
     * @param dx 
     */
    public void setVelocityX(float dx) { this.dx = dx; }
    /**
     * sets the vertical velocity of this Sprite in pixels per milliseconds.
     * @param dy 
     */
    public void setVelocityY(float dy) { this.dy = dy; }
    /**
     * Gets this Sprite's current image.
     */
    public Image getImage() { return anim.getImage(); }
}
