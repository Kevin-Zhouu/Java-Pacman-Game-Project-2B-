package entity;

import bagel.Image;
import util.Vector2;

public class GhostGreen extends AbstractGhost {
    private final static Image GHOST = new Image("res/ghostGreen.png");
    private final static double SPEED = 4;
    private final static double FRENZY_SPEED = 3.5;
    private final static int POINTS = 30;
    private final static double HALF = 0.5;

    /*
     Instantiate a new blue ghost
    @param Vector2 position of the entity
     */
    public GhostGreen(Vector2 position){
        super(position, POINTS,SPEED,FRENZY_SPEED, Vector2.RIGHT_DIRECTION);
        //update the direction randomly
        boolean isHorizontal = Math.random()<HALF;
        setDirection(isHorizontal ? Vector2.RIGHT_DIRECTION : Vector2.DOWN_DIRECTION);
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
        Change the ghost's direction when it hits the wall
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
