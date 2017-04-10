package ariel.manndrops.game.viewmodel.exercisesmanagment.generation.factories;

import java.util.List;
import java.util.Random;

import ariel.manndrops.game.viewmodel.exercisesmanagment.generation.model.Operator;

public class DivideFactory extends ExerciseFactory {
    public DivideFactory(List<Operator> operators, int maxNumber) {
        super(operators, maxNumber);
    }

    @Override
    public String createExercise() {
        Random rand = new Random();
        num1 = rand.nextInt(maxNumber - 1) + 1; //prevent division by zero
        num2 = rand.nextInt(maxNumber - 1) + 1;
        int multiplyResult = num1 * num2;
        return String.valueOf(multiplyResult + "/" + num2);
    }
}
