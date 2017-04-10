package ariel.manndrops.game.model;

import java.util.Set;

public class GameModel {

    private static GameModel gameModel;

    private String name = "";
    private int score = 0;
    private int exercisesSolved = 0;
    private int errors = 0;
    private int lives = 3;

    private GameModel(){
    }

    public static GameModel getInstance() {
        if(gameModel == null){
            gameModel = new GameModel();
        }
        return gameModel;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int getExercisesSolved() {
        return exercisesSolved;
    }

    public int getErrors() {
        return errors;
    }

    public int getLives() {
        return lives;
    }

}
