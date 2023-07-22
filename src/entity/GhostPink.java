package entity;

import bagel.Image;
import util.Vector2;

public class GhostPink extends AbstractGhost {
    private final static Image GHOST = new Image("res/ghostPink.png");
    private final static double SPEED = 3;
    private final static double FRENZY_SPEED = 2.5;
    private final static int POINTS = 30;
    private final static double QUARTER_HALF = 0.25;
    private final static double HALF = 0.5;
    private final static double THREE_QUARTER = 0.75;

    /*
     Instantiate a new blue ghost
    @param Vector2 position of the entity
     */
    public GhostPink(Vector2 position){
        super(position, POINTS, SPEED, FRENZY_SPEED, Vector2.LEFT_DIRECTION);
        setDirection(getRandomDirection());
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
    get a new random direction, either up, down, left or right
     */
    private Vector2 getRandomDirection(){
        double seed = Math.random();
        if(seed<QUARTER_HALF){
            return Vector2.UP_DIRECTION;
        }else if(seed < HALF){
            return Vector2.LEFT_DIRECTION;
        }else if(seed< THREE_QUARTER){
            return Vector2.DOWN_DIRECTION;
        }
        return Vector2.RIGHT_DIRECTION;
    }
    /*
        Change the ghost's direction when it hits the wall
     */
    public void changeDirection(){
        setDirection(getRandomDirection());
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
