package entity;

import bagel.Image;
import util.Vector2;

public class Cherry extends AbstractProp  {
    private final static Image DOT = new Image("res/cherry.png");
    private final static int POINTS = 20;
    /*
    Instantiate a new cherry
    @param Vector2 position of the entity
     */
    public Cherry(Vector2 position){
        super(position,POINTS);
    }
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
