package components;

import bagel.Image;
import entity.Player;
import util.Drawable;

public class Hearts implements Drawable {

    private final static int HEART_X = 900;
    private final static int HEART_Y = 10;
    private final static int HEART_X_INCREMENT = 30;
    private final Image HEART = new Image("res/heart.png");

    /**
     * Draws the hearts component on the screen
     */
    public void draw(){
        Player player = Player.getInstance();
        for(int i=0;i<player.getHealth();i++){
            HEART.drawFromTopLeft(HEART_X+HEART_X_INCREMENT*i,
                    HEART_Y);
        }
    }
}
