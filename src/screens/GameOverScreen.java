package screens;

import bagel.Font;
import bagel.Window;
import util.Drawable;

public class GameOverScreen implements Drawable {

    private final static int FONT_SIZE = 64;
    private final static String TEXT = "GAME OVER";
    private final Font GAME_FONT = new Font("res/FSO8BITR.TTF", FONT_SIZE);

    /**
     * draws the finish screen
     */
    public void draw(){
        double x = Window.getWidth()/2.0 - GAME_FONT.getWidth(TEXT)/2.0;
        double y = Window.getHeight()/2.0;
        GAME_FONT.drawString(TEXT, x,y);
    }
}
