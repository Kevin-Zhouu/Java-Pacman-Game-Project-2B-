package entity;

import bagel.Image;
import util.Vector2;

public class Wall extends AbstractEntity{
    private final static Image WALL = new Image("res/wall.png");
    /*
    Instantiate a new wall
    @param Vector2 position of the wall
     */
    public Wall(Vector2 position){
        super(position);
    }
    /*
    draw the current wall on canvas
     */
    public void draw(){
        if(isVisible()) WALL.drawFromTopLeft(getPosition().getX(),getPosition().getY());
    }
    public Image getImage(){
        return WALL;
    }
    public void collide(){

    }
}
