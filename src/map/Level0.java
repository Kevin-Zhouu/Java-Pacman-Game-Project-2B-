package map;
import entity.*;
import util.CollisionDetector;
import util.Vector2;

import java.util.Objects;

//this class saves all tile data
public class Level0 extends LevelMap{
    /*
    Instantiate level 0
     */
    public Level0(String filepath) {
        super(filepath);
    }

    protected void newEntity(String type, Vector2 position){

        //create new entity based on it's type
        if(Objects.equals(type, "Wall")){
            addWall(new Wall(position));
        } else if (Objects.equals(type, "Dot")) {
            Dot newDot = new Dot(position);
            addProp(newDot);
            //update the win point
            setWinPoint(getWinPoint() + newDot.getPoints());
        }else if (Objects.equals(type, "Ghost")) {
            addGhost(new GhostRed(position));
        }

    }
    /**
     * check if two entities will collide
     */
    public void update(){
        Player player = Player.getInstance();
        //detect collision between player and dots
        CollisionDetector<Player, AbstractProp> propsDetector = new CollisionDetector<>();
        AbstractProp collidedDot = propsDetector.getCollidedEntity(player, getProps());
        if(collidedDot != null){
            handlePropCollision(collidedDot);
        }

        //detect collision between player and ghosts
        CollisionDetector<Player, AbstractGhost> ghostCollisionDetector = new CollisionDetector<>();
        AbstractGhost collidedGhost = ghostCollisionDetector.getCollidedEntity(player, getGhosts());
        if(collidedGhost != null){
            handleGhostCollision();
        }


    }
    private void handlePropCollision(AbstractProp prop){
        prop.collide();
        Player player = Player.getInstance();
        player.setPoints(player.getPoints() + prop.getPoints());

    }
    private void handleGhostCollision(){
        Player player = Player.getInstance();
        player.resetPosition();
        player.setHealth(player.getHealth() - 1);
    }

    /* renders all game data */
    public void draw(){
        Player player = Player.getInstance();
        for(AbstractProp prop: getProps()){
            prop.draw();
        }
        for(Wall wall:getWalls()){
            wall.draw();
        }
        for(AbstractGhost ghost:getGhosts()){
            ghost.draw();
        }
        player.draw();
    }

}
