package entity;

import bagel.Image;
import util.Vector2;

public class Dot extends AbstractProp  {
    private final static Image DOT = new Image("res/dot.png");
    private final static int POINTS = 10;
    /*
    Instantiate a new dot
    @param Vector2 position of the entity
     */
    public Dot(Vector2 position){
        super(position,POINTS);
    }
    /*
    Draw the current dot on canvas
     */
    public void draw(){
        if(super.isVisible()){
            DOT.drawFromTopLeft(super.getPosition().getX(),super.getPosition().getY());
        }
    }
    /*
        Behaviour when collided with player
     */
    public void collide(){
        setVisible(false);
    }
    /*
        Get the current image of the entity
     */
    public Image getImage(){
        return DOT;
    }
}
