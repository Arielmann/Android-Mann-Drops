package ariel.manndrops.game.viewmodel.exercisesmanagment.generation.model;

/**
 * Created by dekel31 on 10/8/2016.
 */
public enum Operator {
    PLUS("+"),
    MINUS("-"),
    MULTIPLY("*"),
    DIVIDE("/");

    String operator;
    Operator(String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return operator;
    }
}
