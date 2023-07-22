package screens;

import bagel.Font;
import util.Drawable;

public class StartScreen implements Drawable {

    private final static int FONT_SIZE = 64;
    private final static int TITLE_X = 260;
    private final static int TITLE_Y = 250;
    private final static int SUBTITLE_X = 320;
    private final static int SUBTITLE_Y = 440;
    private final static int SUBTITLE_Y_INCREMENT = 40;
    private final static int SUBTITLE_FONT_SIZE = 24;
    private final Font GAME_FONT = new Font("res/FSO8BITR.TTF", FONT_SIZE);
    private final Font GAME_FONT_SUBTITLE = new Font("res/FSO8BITR.TTF", SUBTITLE_FONT_SIZE);

    /**
     * draws the start screen
     */
    public void draw(){
        //Draw the game title
        GAME_FONT.drawString("SHADOW PAC",TITLE_X,TITLE_Y);
        //Draw the subtitles
        GAME_FONT_SUBTITLE.drawString("PRESS SPACE TO START",
                SUBTITLE_X, SUBTITLE_Y);
        GAME_FONT_SUBTITLE.drawString("USE ARROW KEY TO MOVE",SUBTITLE_X,
                SUBTITLE_Y+SUBTITLE_Y_INCREMENT);
    }
}
