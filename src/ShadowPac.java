import data.GameState;
import entity.Player;
import map.*;
import bagel.*;
import screens.ScreenManager;
import util.GameStatus;
import util.Vector2;

import java.util.ArrayList;

/**
 * Skeleton Code for SWEN20003 Project 2, Semester 1, 2023
 * Please enter your name below
 * @author Shiyuan Zhou 1346384
 */
public class ShadowPac extends AbstractGame  {

    //game data
    private final ArrayList<LevelMap> levels;
    private final static int WINDOW_WIDTH = 1024;
    private final static int WINDOW_HEIGHT = 768;

    private final static String GAME_TITLE = "SHADOW PAC";
    private final Image BACKGROUND_IMAGE = new Image("res/background0.png");
    private final ScreenManager screenManager;
    private final static int LEVEL_COMPLETE_DISPLAY_TIME = 300;
    private final static int LEVEL_0 = 0;
    private final static int LEVEL_1 = 1;
    private final static int MIN_HEALTH = 0;
    private final static int STATIONARY = 0;


    public ShadowPac(){
        super(WINDOW_WIDTH, WINDOW_HEIGHT, GAME_TITLE);
        levels = new ArrayList<>();
        levels.add(new Level0("res/level0.csv"));
        levels.add(new Level1("res/level1.csv"));
        screenManager = new ScreenManager();
    }


    /**
     * The entry point for the program.
     */
    public static void main(String[] args) {
        ShadowPac game = new ShadowPac();
        game.run();
    }

    /**
     * Performs a state update.
     * Allows the game to exit when the escape key is pressed.
     */
    @Override
    protected void update(Input input) {
        handleKeyboardActions(input);
        updateGame();
        render();
    }
    //update the state of current game
    private void updateGame(){
        GameState gameState = GameState.getInstance();
        LevelMap curLevel = levels.get(gameState.getLevel());
        Player player = Player.getInstance();
        GameStatus gameStatus = gameState.getGameStatus();
        int timeElapsed = gameState.getTimeElapsed();
        //check if win
        if(player.getPoints() >= curLevel.getWinPoint()){
            player.setPoints(0);
            //after completing level 0
            if(gameState.getLevel() == LEVEL_0){
                //change the game state
                gameState.setGameStatus(GameStatus.LEVEL_COMPLETE);
                gameState.resetTimeElapsed();
                player.setInitialPosition(levels.get(0).getInitialPlayerPosition());
                player.resetPosition();
            }
            //after completing level 1
            if(gameState.getLevel() == LEVEL_1){
                gameState.setGameStatus(GameStatus.WIN);
            }
        }
        //check if the player is dead
        if(player.getHealth() <= MIN_HEALTH){
            gameState.setGameStatus(GameStatus.GAME_OVER);
        }
        //switch from level complete screen to level 2 start screen
        if(gameStatus == GameStatus.LEVEL_COMPLETE && timeElapsed >= LEVEL_COMPLETE_DISPLAY_TIME){
            gameState.setGameStatus(GameStatus.LEVEL1_WAITING_START);
        }
        curLevel.update();
        gameState.incrementTimeElapsed();
    }
    /*
     * Handles all keyboard actions
     */
    private void handleKeyboardActions(Input input){
        GameState gameState = GameState.getInstance();
        Player player = Player.getInstance();
        LevelMap curLevel = levels.get(gameState.getLevel());
        if (input.isDown(Keys.LEFT)) {
            //move player to left
            curLevel.movePlayer(Vector2.LEFT_DIRECTION);

        }
        if (input.isDown(Keys.RIGHT)) {
            //move player to right
            curLevel.movePlayer(Vector2.RIGHT_DIRECTION);
        }
        if (input.isDown(Keys.UP)) {
            //move player to up
            curLevel.movePlayer(Vector2.UP_DIRECTION);
        }
        if (input.isDown(Keys.DOWN)) {
            //move player to down
            curLevel.movePlayer(Vector2.DOWN_DIRECTION);
        }
        //close the screen
        if (input.wasPressed(Keys.ESCAPE)){
            Window.close();
        }
        //start level 0 after pressing space
        if(input.wasPressed(Keys.SPACE) && gameState.getGameStatus() == GameStatus.WAITING_START){
            gameState.setGameStatus(GameStatus.PLAYING);
            Vector2 initialPosition = curLevel.getInitialPlayerPosition();
            player.setInitialPosition(initialPosition.copy());
            player.resetPosition();
        }

        //start level 1 after pressing space
        if(input.wasPressed(Keys.SPACE) && gameState.getGameStatus() == GameStatus.LEVEL1_WAITING_START){
            gameState.setLevel(LEVEL_1);
            gameState.setGameStatus(GameStatus.PLAYING);
            Vector2 initialPosition = curLevel.getInitialPlayerPosition();
            player.setInitialPosition(initialPosition.copy());
            player.resetPosition();
        }
    }

    /* Renders sprites on screen
     */
    private void render(){
        GameState gameState = GameState.getInstance();
        //draw background
        BACKGROUND_IMAGE.draw(Window.getWidth()/2.0, Window.getHeight()/2.0);
        //renders different screens based on it's state
        screenManager.draw(gameState.getGameStatus());
        //draw the game entities
        if(gameState.getGameStatus() == GameStatus.PLAYING){
            levels.get(gameState.getLevel()).draw();
        }
    }

}
