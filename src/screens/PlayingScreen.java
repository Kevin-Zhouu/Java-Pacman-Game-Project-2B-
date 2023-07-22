package screens;

import components.Hearts;
import components.Points;
import util.Drawable;

public class PlayingScreen implements Drawable {
    private final Hearts hearts;
    private final Points points;
    /*
    instantiate playing screen
     */
    public PlayingScreen(){
        hearts = new Hearts();
        points = new Points();
    }
    /**
     * draws the level complete screen
     */
    public void draw(){
        hearts.draw();
        points.draw();
    }
}
