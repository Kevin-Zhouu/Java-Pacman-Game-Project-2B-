package map;
import entity.*;
import util.CollisionDetector;
import util.Vector2;
import util.Drawable;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

//this class saves all tile data
public abstract class LevelMap implements Drawable{
    private Vector2 initialPlayerPosition;
    private final ArrayList<Wall> walls;
    private final ArrayList<AbstractProp> props;
    private final ArrayList<AbstractGhost> ghosts;
    private int winPoint;
    /*
    Instantiate a new level
    @param String the path of the map file
     */
    public LevelMap(String filepath) {
        walls = new ArrayList<>();
        props = new ArrayList<>();
        ghosts = new ArrayList<>();
        //read csv to map data
        readCSV(filepath);
    }
    /*
    This method reads a map csv and save it to GameMap class
     */
    public void readCSV(String filename) {
        try {
            Scanner scanner = new Scanner(new File(filename));
            //read a new entity
            while (scanner.hasNextLine()) {
                //for every new lines, gets the type and coordinates
                String nextLine = scanner.nextLine();
                String[] entityData = nextLine.split(",");
                String entityType = entityData[0];
                double entityX = Double.parseDouble(entityData[1]);
                double entityY = Double.parseDouble(entityData[2]);
                Vector2 position = new Vector2(entityX,entityY);
                //create new object based on the type
                if (Objects.equals(entityType, "Player")) {
                    setInitialPlayerPosition(new Vector2(entityX,entityY));
                }else{
                    newEntity(entityType,position);
                }
            }
            scanner.close();
        }catch (IOException e){
            System.out.println("Failed to read the file");
        }
    }
    /*
    Add new entity to the map
    @param String type of the entity
    @param Vector2 initial position of the entity
     */
    abstract protected void newEntity(String type, Vector2 position);

    /*
    Draw all entities on the map
     */
    public abstract void draw();

    /**
     * check if two entities will collide
     */
    public abstract void update();

    /**
     * Moves player if it does not collide with any wall
     * @param delta the amount of x and y that the player is moving
     */
    public void movePlayer(Vector2 delta){
        Player player = Player.getInstance();
        Vector2 calculatedDelta = Vector2.scalarProduct(delta,player.getSpeed());
        //check if player collides with wall
        CollisionDetector<Player, Wall> wallCollisionDetector = new CollisionDetector<>();
        Wall detectedWall = wallCollisionDetector.getCollidedEntity(player, walls, calculatedDelta);
        //if player won't collide with wall, move the player
        if(detectedWall == null){
            player.move(delta);
        }
    }


    /**
     * get the initial position of a player
     * @return Vector2 the initial position of a player
     */
    public Vector2 getInitialPlayerPosition() {
        return initialPlayerPosition;
    }

    /**
     * set the initial position of a player
     * @param initialPlayerPosition set the initial position of a player
     */
    public void setInitialPlayerPosition(Vector2 initialPlayerPosition) {
        this.initialPlayerPosition = initialPlayerPosition;
    }
    /**
     * get the walls
     * @return ArrayList<Wall> current walls
     */
    protected ArrayList<Wall> getWalls(){
        return this.walls;
    }
    /**
     * add new the walls
     */
    protected void addWall(Wall wall){
        walls.add(wall);
    }/**
     * get the walls
     * @return ArrayList<Entity> current walls
     */
    protected ArrayList<AbstractProp> getProps(){
        return this.props;
    }
    /**
     * add new the walls
     */
    protected void addProp(AbstractProp entity){
        props.add(entity);
    }

    /* get the walls
     * @return ArrayList<Entity> current walls
     */
    protected ArrayList<AbstractGhost> getGhosts(){
        return this.ghosts;
    }
    /**
     * add new the walls
     */
    protected void addGhost(AbstractGhost ghost){
        ghosts.add(ghost);
    }

    /* get win points
     * @return ArrayList<Entity> current walls
     */
    public int getWinPoint() {
        return winPoint;
    }
    /* set the win points
     * @param int new win points
     */
    public void setWinPoint(int winPoint) {
        this.winPoint = winPoint;
    }
}
