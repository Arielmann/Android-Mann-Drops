package ariel.manndrops.game.viewmodel.exercisesmanagment.generation.factories;

import java.util.List;
import java.util.Random;

import ariel.manndrops.game.viewmodel.exercisesmanagment.generation.model.Operator;

public class ExerciseFactory {

    /*
    * get an operator and a max number int.
    * generate 2 random numbers between 1 - max number
    * return a new exercise
    * */

    Operator operator;
    int maxNumber;
    int num1;
    int num2;

    public ExerciseFactory(List<Operator> operators, int maxNumber) {
        long random = Math.round(Math.random() * (operators.size() - 1));
        this.operator = operators.get((int) random);
        this.maxNumber = maxNumber;
    }

    public String createExercise() {
        Random rand = new Random();
        num1 = rand.nextInt(maxNumber);
        num2 = rand.nextInt(maxNumber);
        if(num2 > num1){
            return String.valueOf(num2 + " " + operator.toString() + " " + num1);
        }
        return String.valueOf(num1 + operator.toString() + num2);
    }
}
