package entity;

import bagel.Image;
import util.Vector2;

public class GhostBlue extends AbstractGhost {
    private final static Image GHOST = new Image("res/ghostBlue.png");
    private final static double SPEED = 2;
    private final static double FRENZY_SPEED = 1.5;
    private final static int POINTS = 30;
    /*
     Instantiate a new blue ghost
    @param Vector2 position of the entity
     */
    public GhostBlue(Vector2 position){
        super(position, POINTS,SPEED,FRENZY_SPEED, Vector2.DOWN_DIRECTION);
    }
    /*
    Draw the current ghost on canvas
     */
    protected void drawGhost(){
        if(super.isVisible()){
            GHOST.drawFromTopLeft(super.getPosition().getX(), super.getPosition().getY());
        }
    }


    public void changeDirection(){
        setDirection(Vector2.scalarProduct(getDirection(),-1));
    }

    public Image getImage(){
        return GHOST;
    }
    public void collide(){
        setVisible(false);
    }
}
