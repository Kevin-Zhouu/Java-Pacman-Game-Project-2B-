package util;

import bagel.Image;
import bagel.util.Rectangle;
import entity.AbstractEntity;

import java.util.ArrayList;

public class CollisionDetector<T extends AbstractEntity, S extends AbstractEntity> {
    /*
    this function detects if two entities will collide
     */
    public boolean willEntitiesCollide(T entityA, S entityB, Vector2 velocity){
        // check if any of the entity is invisible
        if(!entityA.isVisible() || !entityB.isVisible()) return false;
        // get the positions of two entities
        Vector2 posEntityA = entityA.getPosition();
        Vector2 posEntityB = entityB.getPosition();
        //get the images of two entities
        Image imgEntityA = entityA.getImage();
        Image imgEntityB = entityB.getImage();
        //create new rectangle classes
        Rectangle rectangleA = new Rectangle(posEntityA.getX()+velocity.getX(),posEntityA.getY()+ velocity.getY(),
                imgEntityA.getWidth(),imgEntityA.getHeight());
        Rectangle rectangleB = new Rectangle(posEntityB.getX(),posEntityB.getY(),
                imgEntityB.getWidth(),imgEntityB.getHeight());
        //check if they collide
        return rectangleB.intersects(rectangleA);
    }

    public S getCollidedEntity(T entityA, ArrayList<S> entities, Vector2 delta){
        for(S entity:entities){
            if(willEntitiesCollide(entityA,entity,delta)){
                return entity;
            }
        }
        return null;
    }
    public S getCollidedEntity(T entityA, ArrayList<S> entities){
       return getCollidedEntity(entityA, entities, new Vector2(0,0));
    }
}
