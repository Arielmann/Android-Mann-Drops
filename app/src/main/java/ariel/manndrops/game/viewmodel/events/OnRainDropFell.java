package ariel.manndrops.game.viewmodel.events;

import android.view.View;

public class OnRainDropFell {

    public String rainDropId;
    public View rainDropForRemove;

    public OnRainDropFell(String rainDropId, View rainDropForRemove) {
        this.rainDropId = rainDropId;
        this.rainDropForRemove = rainDropForRemove;
    }

}
