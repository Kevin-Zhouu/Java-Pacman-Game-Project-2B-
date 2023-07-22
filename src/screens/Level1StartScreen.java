package screens;

import bagel.Font;
import util.Drawable;

public class Level1StartScreen implements Drawable {

    private final static int FONT_SIZE = 40;
    private final static int TITLE_X = 200;
    private final static int TITLE_Y = 350;
    private final static int SUBTITLE_X = 200;
    private final static int SUBTITLE_Y = 380;
    private final static int SUBTITLE_Y_INCREMENT = 30;
    private final static int SUBTITLE_FONT_SIZE = 24;
    private final Font GAME_FONT_SUBTITLE = new Font("res/FSO8BITR.TTF", SUBTITLE_FONT_SIZE);

    /**
     * draws the start screen
     */
    public void draw(){
        GAME_FONT_SUBTITLE.drawString("PRESS SPACE TO START",
                TITLE_X, TITLE_Y);
        //Draw the subtitles
        GAME_FONT_SUBTITLE.drawString("USE ARROW KEYS TO MOVE",
                SUBTITLE_X, SUBTITLE_Y);
        GAME_FONT_SUBTITLE.drawString("EAT THE PELLET TO ATTACK",SUBTITLE_X,
                SUBTITLE_Y+SUBTITLE_Y_INCREMENT);
    }
}
