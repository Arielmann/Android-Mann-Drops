package ariel.manndrops.game.model;

import java.util.HashSet;
import java.util.Set;

import ariel.manndrops.game.viewmodel.exercisesmanagment.calculation.GameCalculator;

public class RainDropModel {

    public static Set<RainDropModel> rainDrops = new HashSet();

    String answer;
    String exercise;
    String velocity;

    public RainDropModel(String exercise) {
        this.exercise = exercise;
        answer = GameCalculator.getInstance().calculate(exercise);
        velocity = "4"; //TODO: make it random
    }

    public String getVelocity() {
        return velocity;
    }

    public String getAnswer() {
        return answer;
    }

    public String getExercise() {
        return exercise;
    }
}
