/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamev1;

import java.awt.Image;
import java.util.ArrayList;

/**
 *  This class manages a series of images(frames) and the amount of time to display it on the screen
 * @author Moises
 */
public class Animation {
    //  FIELDS
    private ArrayList<Frame>            frames;                                 // stores all the frames
    private int                         index;                                  // points to a specific frame
    private long                        elapsedTime;                            // the time passed for this animation
    private long                        totalDuration;                          // stores the time it takes the whole animation
    //  PRIVATE CLASS
    private class Frame {
        Image image;
        long endTime;
        public Frame(Image image, long endTime) {
            this.image = image;
            this.endTime = endTime;
        }
    }                                          // class representing a frame
    //  CONSTRUCTOR
    public Animation () {
        frames = new ArrayList();
        totalDuration = 0;
        start();
    }
    //  METHODS
    /**
     * add a frame with a specified time to be displayed on the screen
     * @param image the frame
     * @param duration nanoseconds this image will be displayed
     */
    public synchronized void addFrame(Image image, long duration) {
        totalDuration += duration;
        frames.add(new Frame(image,totalDuration));
    }
    
    /**
     * method to start the animation from the beginning;
     */
    public synchronized void start() {
        elapsedTime = 0;
        index = 0;
    }
    
    /**
     * updates the animation by changing the current image depending on the time provided.
     * @param nanoseconds the time passed since the last called to this method.
     */
    public synchronized void update(long nanoseconds) {
        if(frames.size() > 1) {
            elapsedTime += nanoseconds;
            
            if (elapsedTime >= totalDuration) {
                elapsedTime = elapsedTime % totalDuration;
                index = 0;
            }
            while (elapsedTime > getFrame(index).endTime) {
                index++;
            }
        }
    }

    /**
     * provides the current image to be displayed. It returns null if no image is found. 
     * @return an image to be displayed for this animation
     */
    public synchronized Image getImage() {
        if(frames.size() == 0) {
            return null;
        }
        else {
            return getFrame(index).image;
        }
    }
    private Frame getFrame(int i) {
        return (Frame)frames.get(i);
    }
    
}
