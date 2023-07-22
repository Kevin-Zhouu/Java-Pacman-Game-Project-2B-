package components;

import bagel.Font;
import entity.Player;
import util.Drawable;

public class Points implements Drawable {
    public final static int SCORE_FONT_SIZE = 24;
    public final Font GAME_FONT_SCORE = new Font("res/FSO8BITR.TTF", SCORE_FONT_SIZE);
    public final static int SCORE_X = 25;
    public final static int SCORE_Y = 25;
    public void draw(){
        Player player = Player.getInstance();
        GAME_FONT_SCORE.drawString("SCORE "+player.getPoints(), SCORE_X, SCORE_Y);
    }
}
