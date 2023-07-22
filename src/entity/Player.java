package entity;
import bagel.DrawOptions;
import data.GameState;
import util.Vector2;
import bagel.Image;
public class Player extends AbstractEntity {
    public static final int INITIAL_SPEED = 3;
    public static final int FRENZY_SPEED = 4;
    private static final int INITIAL_HEALTH = 3;
    private static final int INITIAL_POINTS = 0;
    private static final String FILE_NAME = "res/pac.png";
    private static final String FILE_NAME_PAC_OPEN = "res/pacOpen.png";
    private static final Image PACMAN = new Image(FILE_NAME);
    private static final Image PACMAN_OPEN = new Image(FILE_NAME_PAC_OPEN);
    private final static int PLAYER_ANIMATION_TIME = 15;
    private static Player instance;
    private Image curImage;
    private double rotation = 0;
    private boolean isMouthOpen = false;
    private int points;
    private int health;
    private int speed;
    /*
    Instantiate a new player
     */
    private Player(){
        super(new Vector2(0,0));
        this.curImage = PACMAN;
        this.points = INITIAL_POINTS;
        this.health = INITIAL_HEALTH;
        this.speed = INITIAL_SPEED;
    }
    /*
    Get the player instance
     */
    public static Player getInstance(){
        if(instance == null){
            instance = new Player();
        }
        return instance;
    }
    /*
    Move the player to a new delta
    @param Vector2 the delta of the move
     */
    public void move(Vector2 delta){
        //get the x and y components
        double dx = delta.getX()*speed;
        double dy = delta.getY()*speed;
        super.getPosition().increaseX(dx);
        super.getPosition().increaseY(dy);
        //rotate the player based on the move direction
        if(dx >0 && dy == 0){
            //facing right
            rotation = 0;
        }else if(dx <0 && dy == 0){
            //facing left
            rotation = Math.PI;
        }else if(dx ==0 && dy > 0){
            //facing down
            rotation = Math.PI/2.0D;
        }else if(dx ==0 && dy < 0){
            //facing up
            rotation = 3*Math.PI/2.0D;
        }
    }
    /*
    Draw the current player on canvas
     */
    public void draw(){
        DrawOptions drawOptions = new DrawOptions();
        GameState gameState = GameState.getInstance();
        drawOptions.setRotation(rotation);
        //mouth opening animation
        if(gameState.getTimeElapsed() % PLAYER_ANIMATION_TIME == 0){
            isMouthOpen = !isMouthOpen;
            if(isMouthOpen){
                curImage = PACMAN_OPEN;
            }else{
                curImage = PACMAN;
            }
        }
        //draw the image
        if(isVisible()) curImage.drawFromTopLeft(getPosition().getX(), getPosition().getY(), drawOptions);

    }
    public Image getImage(){
        return this.curImage;
    }
    /*
       update the player point
       @param points the new points
    */
    public void setPoints(int points) {
        this.points = points;
    }
    /*
        get the point of a player
        @return int current point of the game
     */
    public int getPoints() {
        return points;
    }
    /*
        get the health of a player
        @return int current health of the game
     */
    public int getHealth() {
        return health;
    }
    /*
        set the health of a player
        @return int current health of the game
     */
    public void setHealth(int health) {
        this.health = health;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
