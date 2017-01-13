package ariel.manndrops.game.viewmodel.events;

import android.view.View;

public class OnUserCorrect {
    public String rainDropId;
    public String answer;
    public View rainDropForRemove;

    public OnUserCorrect( String rainDropId, View rainDropForRemove, String answer) {
        this.answer = answer;
        this.rainDropId = rainDropId;
        this.rainDropForRemove = rainDropForRemove;
    }
}
