package data;

import util.GameStatus;

public class GameState {
    private static GameState instance;
    private int level = 0;
    private GameStatus gameStatus;
    private int timeElapsed;

    private GameState(){
        timeElapsed = 0;
        gameStatus = GameStatus.WAITING_START;
    }
    /*
        get the singleton instance of the game data
        @return GameState instance of the game data
     */
    public static GameState getInstance(){
        if(instance == null) {
            instance = new GameState();
        }
        return instance;
    }
    /*
        update the current level
        @param newLevel the new level
     */
    public void setLevel(int newLevel){
        level = newLevel;
    }
    /*
        get the current level
        @return int the current level
     */
    public int getLevel(){
        return level;
    }
    /*
        get the time elapsed of the game
        @return int time elapsed since the start of the game
     */
    public int getTimeElapsed(){
        return timeElapsed;
    }
    /*
        increase the time elapsed of the game
     */
    public void incrementTimeElapsed(){
        timeElapsed = timeElapsed + 1;
    }
    /**
     * reset the time of the game
     */
    public void resetTimeElapsed(){
        timeElapsed = 0;
    }

    /**
     * @return the current state of the game
     */
    public GameStatus getGameStatus() {
        return gameStatus;
    }

    /**
     * @param gameStatus update the state of the game
     */
    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }


}
