package ariel.manndrops.game;

import android.view.View;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import ariel.manndrops.game.view.RainDropView;

public class GameViewModelTest {

    private static final String TAG = GameViewModelTest.class.getName();

    private Map<String, RainDropView> allRainDropsOnScreen = new HashMap<>();
//    private GameActivity gameActivity = Robolectric.setupActivity(GameActivity.class);

    @Before
    public void setUp() throws Exception {

        String rainDropId = UUID.randomUUID().toString();
  //      RelativeLayout gameLayout = (RelativeLayout) gameActivity.findViewById(R.id.gameViewLayout);
    //    allRainDropsOnScreen.put(rainDropId, new RainDropView(gameActivity, rainDropId, gameLayout, ExerciseDataLevel.NORMAL));
      //  allRainDropsOnScreen.put(rainDropId, new RainDropView(gameActivity, rainDropId, gameLayout, ExerciseDataLevel.NORMAL));
        //allRainDropsOnScreen.put(rainDropId, new RainDropView(gameActivity, rainDropId, gameLayout, ExerciseDataLevel.NORMAL));
    }

    @Test
    public void onUserAnswered() {
        return;
        /*String userAnswer = "3";
        int numbrerOfExercisesBeforeCalculation = allRainDropsOnScreen.keySet().size();
        for (Map.Entry<String, RainDropView> entry : allRainDropsOnScreen.entrySet()) {
            String id = entry.getKey();
            RainDropView rainDropView = entry.getValue();
            if (rainDropView.getAnswer().equals(userAnswer)) {
                Log.d(TAG, "User was correct with answer: " + rainDropView.getAnswer());
                //onUserCorrect(id, rainDropView.getView(), userAnswer);
            }
        }
        if(numbrerOfExercisesBeforeCalculation == allRainDropsOnScreen.keySet().size()){
            onUserWrong();
        }*/
    }

    @Test
    public void onUserWrong() {
        String score = "500";
        Integer currentScore = Integer.valueOf(score);
        Integer scorePanelty = currentScore * 5 / 100; //Decrease 5% from total score
        score = String.valueOf(currentScore - scorePanelty);
//        Log.d(TAG, "User was wrong, score panelty: " + scorePanelty);
    }

   /* @Test
    public void onUserCorrect(String rainDropId, View rainDropForRemove, String userAnswer){
        String score = "500";
        removeRainDropFromScreen(rainDropId, rainDropForRemove);
        Integer newScore = Integer.parseInt(score) + Integer.parseInt(userAnswer);
        score = String.valueOf(newScore);
        Log.d(TAG, "User was correct and raindrop with id: " + rainDropId + " was removed from screen");
    }*/

    private void removeRainDropFromScreen(String rainDropId, View rainDropForRemove){
        allRainDropsOnScreen.remove(rainDropId);
        //gameActivity.getBinding().gameViewLayout.removeView(rainDropForRemove);
    }

}