package screens;

import util.GameStatus;

public class ScreenManager{
    private final PlayingScreen playingScreen;
    private final WinScreen winScreen;
    private final LevelCompleteScreen levelCompleteScreen;
    private final StartScreen startScreen;
    private final GameOverScreen gameOverScreen;
    private final Level1StartScreen level1StartScreen;

    /*
    instantiate screen manager
     */
    public ScreenManager(){
        //add screens
        playingScreen = new PlayingScreen();
        winScreen = new WinScreen();
        levelCompleteScreen = new LevelCompleteScreen();
        startScreen = new StartScreen();
        gameOverScreen = new GameOverScreen();
        level1StartScreen = new Level1StartScreen();
    }
    /*
    draw the corresponding screen based on the game status
     */
    public void draw(GameStatus status){
        if(status == GameStatus.WAITING_START) startScreen.draw();
        if(status == GameStatus.PLAYING) playingScreen.draw();
        if(status == GameStatus.LEVEL_COMPLETE) levelCompleteScreen.draw();
        if(status == GameStatus.WIN) winScreen.draw();
        if(status == GameStatus.GAME_OVER) gameOverScreen.draw();
        if(status == GameStatus.LEVEL1_WAITING_START) level1StartScreen.draw();
    }

}
