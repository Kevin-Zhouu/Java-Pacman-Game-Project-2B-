package entity;

import bagel.Image;
import util.Collidable;
import util.Vector2;

public abstract class AbstractGhost extends AbstractEntity implements Collidable {
    private final Image FRENZY = new Image("res/ghostFrenzy.png");
    private final int points;
    private final double speed;
    private final double frenzySpeed;
    private Vector2 direction;

    /**
     *
     Instantiate a ghost
     * @param position initial position of the ghost
     * @param points point award for the ghost
     * @param speed speed of the ghost
     * @param frenzySpeed frenzy speed of the ghost
     * @param direction initial direction of the ghost
     */
    public AbstractGhost(Vector2 position, int points, double speed, double frenzySpeed,
                         Vector2 direction) {
        super(position);
        this.points = points;
        this.speed = speed;
        this.frenzySpeed = frenzySpeed;
        this.direction = direction;
    }
    /*
        Get the current image of the ghost
     */
    public abstract Image getImage();

    /*
        Ghost behaviour when collided with player during frenzy mode
     */
    public abstract void collide();

    /*
        Change the ghost's direction when it hits the wall
     */
    public abstract void changeDirection();
    /*
    draw the ghost on canvas
    @param boolean whether it is in frenzy mode
     */
    public void draw(boolean isFrenzy){
        if(!isVisible()) return;
        if(isFrenzy){
            FRENZY.drawFromTopLeft(getPosition().getX(), getPosition().getY());
        }else{
            drawGhost();
        }
    }

    /*
        Draw the current ghost without considering frenzy mode
     */
    public void draw(){
        draw(false);
    }

    /**
     Updates the position of the object based on the current direction and speed.
     @param isFrenzy a boolean value indicating whether the object is in frenzy mode
     */
    public void update(boolean isFrenzy) {
        // Get the current position of the object
        Vector2 curPosition = getPosition();
        // Set the speed based on the frenzy mode
        double speed = getSpeed();
        if (isFrenzy) {
            speed = getFrenzySpeed();
        }
        // Calculate the velocity vector based on the direction and speed
        Vector2 velocity = Vector2.scalarProduct(getDirection(), speed);

        // Calculate the new position
        Vector2 newPosition = Vector2.addition(curPosition, velocity);

        // Set the new position of the object
        super.setPosition(newPosition);
    }
    protected abstract void drawGhost();
    public int getPoints() {
        return points;
    }

    public double getSpeed() {
        return speed;
    }

    public double getFrenzySpeed() {
        return frenzySpeed;
    }

    public Vector2 getDirection() {
        return direction;
    }

    public void setDirection(Vector2 direction) {
        this.direction = direction;
    }

    /*
    Get the velocity of the ghost for collision detection
    @return Vector2 the velocity of current ghost
     */
    public Vector2 getVelocity(){
        return Vector2.scalarProduct(direction,speed);
    }
}
