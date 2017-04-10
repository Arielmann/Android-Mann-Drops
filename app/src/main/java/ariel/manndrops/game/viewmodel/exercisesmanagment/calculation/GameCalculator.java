package ariel.manndrops.game.viewmodel.exercisesmanagment.calculation;

import com.udojava.evalex.Expression;

public class GameCalculator{

    private static GameCalculator calculator;

    public static GameCalculator getInstance(){
        if(calculator == null){
            calculator = new GameCalculator();
        }
        return calculator;
    }

    private GameCalculator(){
    }

   public String calculate(CharSequence exercise){
       Expression expression = new Expression(exercise.toString());
       return expression.eval().toString();
   }
}
