package map;
import entity.*;
import util.CollisionDetector;
import util.Vector2;

import java.util.Objects;

//this class saves all tile data
public class Level1 extends LevelMap{
    private final static int WIN_POINT = 800;
    private final static int FRENZY_PERIOD = 1000;
    private boolean isFrenzy;
    private int frenzyTime;
    /*
   Instantiate level 1
   @param String the path of map data
    */
    public Level1(String filepath) {
        super(filepath);
        setWinPoint(WIN_POINT);
    }
    /*
    Add new entity to the map
    @param String type of the entity
    @param Vector2 initial position of the entity
     */
    protected void newEntity(String type, Vector2 position){
        if(Objects.equals(type, "Wall")){
            addWall(new Wall(position));
        } else if (Objects.equals(type, "Dot")) {
            addProp(new Dot(position));
        }else if (Objects.equals(type, "GhostRed")) {
            addGhost(new GhostRed(position));
        }else if (Objects.equals(type, "GhostBlue")) {
            addGhost(new GhostBlue(position));
        }else if (Objects.equals(type, "GhostGreen")) {
            addGhost(new GhostGreen(position));
        }else if (Objects.equals(type, "GhostPink")) {
            addGhost(new GhostPink(position));
        }else if (Objects.equals(type, "Pellet")) {
            addProp(new Pellet(position));
        }else if (Objects.equals(type, "Cherry")) {
            addProp(new Cherry(position));
        }
    }
    /*
    Update the state of the map
     */
    public void update(){
        checkCollision();
        // update the frenzy state
        if(isFrenzy){
            //add the frenzy time
            frenzyTime += 1;
            //end the frenzy state if it exceeds the frenzy period
            if(frenzyTime >= FRENZY_PERIOD){
                endFrenzy();
            }
        }
    }

    /**
     * check if two entities will collide
     */
    private void checkCollision(){
        Player player = Player.getInstance();

        //detect collision between player and dots
        CollisionDetector<Player, AbstractProp> propsDetector = new CollisionDetector<>();
        AbstractProp collidedDot = propsDetector.getCollidedEntity(player, getProps());
        if(collidedDot != null){
            handlePropCollision(collidedDot);
        }

        //detect collision between player and ghosts
        CollisionDetector<Player, AbstractGhost> ghostDetector = new CollisionDetector<>();
        AbstractGhost collidedGhost = ghostDetector.getCollidedEntity(player, getGhosts());
        if(collidedGhost != null){
            handleGhostCollision(collidedGhost);
        }

        //detect collision between ghost and wall
        CollisionDetector<AbstractGhost, Wall> wallDetector = new CollisionDetector<>();
        for(AbstractGhost ghost : getGhosts()){
            if(wallDetector.getCollidedEntity(ghost, getWalls(), ghost.getVelocity()) == null){
                //if ghost doesn't collide with wall, update position
                ghost.update(isFrenzy);
            }else{
                //if ghost collides with wall, reverse direction
                ghost.changeDirection();
            }

        }
    }
    /*
        handles the entity behaviour when player collides with prop
     */
    private void handlePropCollision(AbstractProp prop){
        prop.collide();
        Player player = Player.getInstance();
        player.setPoints(player.getPoints() + prop.getPoints());
        System.out.println("adding point:"+prop.getPoints()+prop);
        if(prop instanceof Pellet){
            startFrenzy();
        }

    }
    /*
        handles the entity behaviour when player collides with ghost
     */
    private void handleGhostCollision(AbstractGhost ghost){
        Player player = Player.getInstance();
        //check if the player is frenzy
        if(isFrenzy){
            //if so, hide the ghost and increment the point
            ghost.collide();
            player.setPoints(player.getPoints() + ghost.getPoints());
        }else{
            //if not, deduct health and reset player position
            player.resetPosition();
            player.setHealth(player.getHealth() - 1);
        }
    }

    /* renders all game entities */
    public void draw(){
        Player player = Player.getInstance();
        for(AbstractProp prop: getProps()){
            prop.draw();
        }
        for(Wall wall:getWalls()){
            wall.draw();
        }
        for(AbstractGhost ghost:getGhosts()){
            ghost.draw(isFrenzy);
        }
        player.draw();
    }
    /*
    Handles the state changes when frenzy mode starts
     */
    private void startFrenzy(){
        Player player = Player.getInstance();
        player.setSpeed(Player.FRENZY_SPEED);
        isFrenzy = true;
        frenzyTime = 0;
    }
    /*
    handles entity behaviour when frenzy mode ends
     */
    private void endFrenzy(){
        Player player = Player.getInstance();
        player.setSpeed(Player.INITIAL_SPEED);
        isFrenzy = false;
        frenzyTime = 0;
        //reset all hidden ghost's position
        for(AbstractGhost ghost:getGhosts()){
            if(!ghost.isVisible()){
                ghost.resetPosition();
                ghost.setVisible(true);
            }
        }
    }

}
