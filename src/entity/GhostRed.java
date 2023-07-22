package entity;

import bagel.Image;
import util.Vector2;

public class GhostRed extends AbstractGhost {
    private final static Image GHOST = new Image("res/ghostRed.png");
    private final static double SPEED = 1;
    private final static double FRENZY_SPEED = 0.5;
    private final static int POINTS = 30;

    /*
     Instantiate a new blue ghost
    @param Vector2 position of the entity
     */
    public GhostRed(Vector2 position){
        super(position, POINTS,SPEED,FRENZY_SPEED,Vector2.RIGHT_DIRECTION);
    }

    /*
    Draw the current ghost on canvas
     */
    protected void drawGhost(){
        if(super.isVisible()){
            GHOST.drawFromTopLeft(super.getPosition().getX(), super.getPosition().getY());
        }
    }

    /*
        Get the current image of the ghost
     */
    public void changeDirection(){
        setDirection(Vector2.scalarProduct(getDirection(),-1));
    }
    /*
        Get the current image of the ghost
     */
    public Image getImage(){
        return GHOST;
    }
    /*
        Ghost behaviour when collided with player during frenzy mode
     */
    public void collide(){
        setVisible(false);
    }
}
