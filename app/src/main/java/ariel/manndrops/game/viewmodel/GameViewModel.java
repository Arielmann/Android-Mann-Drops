package ariel.manndrops.game.viewmodel;

import android.databinding.ObservableField;
import android.graphics.Point;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import ariel.manndrops.game.view.GameActivity;
import ariel.manndrops.game.view.RainDropView;
import ariel.manndrops.game.viewmodel.events.OnRainDropFell;
import ariel.manndrops.game.viewmodel.exercisesmanagment.generation.model.ExerciseDataLevel;
import ariel.manndrops.profile.SharedPrefManager;

public class GameViewModel implements ViewModel {

    private static final String TAG = GameViewModel.class.getName();
    public ObservableField<String> userAnswer;
    public ObservableField<String> score;
    private GameActivity gameActivity;
    private Map<String, RainDropView> allRainDropsOnScreen;

    public GameViewModel(GameActivity gameActivity) {
        // saveScreenSizesToSharedPref();
        EventBus.getDefault().register(this);
        userAnswer = new ObservableField<>(""); //empty string prevents "null" as string in result field
        score = new ObservableField<>("0");
        this.gameActivity = gameActivity;
        this.allRainDropsOnScreen = new HashMap<>();
        scheduleRainDropping(1000, 4000);
    }

    public void onNumberClicked(View clickedNumberView) {
        Button numberButton = (Button) clickedNumberView;
        userAnswer.set(userAnswer.get() + numberButton.getText());
    }

    public void onExerciseAnswered(View enterButton) {
        if (!userAnswer.get().isEmpty()) {
            onUserAnswered(userAnswer.get());
        }
        userAnswer.set("");
    }

    public void onDeleteClicked(View deleteButton) {
        if (userAnswer.get().length() > 0) {
            String resultWithoutLastDigit = (userAnswer.get().substring(0, userAnswer.get().length() - 1));
            userAnswer.set(resultWithoutLastDigit);
        }
    }

    public void onExerciseError() {
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        gameActivity = null;
    }

    private void scheduleRainDropping(int delayTime, int constantInterval) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    public void run() {
                        dropRainDrop();
                    }
                });
            }
        }, delayTime, constantInterval);
    }

    private void dropRainDrop() { //Raindrop is dropping method is called upon creation
        //pass activity authorities, gameLayout and exercise model for exercise generation
        String id = UUID.randomUUID().toString();
        RelativeLayout gameLayout = gameActivity.getBinding().gameViewLayout;
        RainDropView view = new RainDropView(gameActivity, id, gameLayout, ExerciseDataLevel.NORMAL);
        allRainDropsOnScreen.put(id, view);
    }

    private void saveScreenSizesToSharedPref() {
        Display display = gameActivity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        SharedPrefManager.getInstance(gameActivity).saveIntInfoToSharedPreferences(gameActivity, "deviceScreenHeight", height);
        SharedPrefManager.getInstance(gameActivity).saveIntInfoToSharedPreferences(gameActivity, "deviceScreenWidth", width);
    }

    private void onUserCorrect(String rainDropId, View rainDropForRemove, String answer) {
        removeRainDropFromScreen(rainDropId, rainDropForRemove);
        Integer newScore = Integer.parseInt(score.get()) + Integer.parseInt(answer);
        score.set(String.valueOf(newScore));
        Log.d(TAG, "User was correct and raindrop with id: " + rainDropId + " was removed from screen");
    }

    private void onUserAnswered(String userAnswer) {
        int numbrerOfExercisesBeforeCalculation = allRainDropsOnScreen.keySet().size();
        Iterator iter = allRainDropsOnScreen.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry item = (Map.Entry<String, RainDropView>) iter.next();
            String rainDropId = (String) item.getKey();
            RainDropView rainDropView = (RainDropView) item.getValue();
            if (rainDropView.getAnswer().equals(userAnswer)) {
                Log.d(TAG, "User was correct with answer: " + rainDropView.getAnswer());
                onUserCorrect(rainDropId, rainDropView.getView(), userAnswer);
            }
        }

        if (numbrerOfExercisesBeforeCalculation == allRainDropsOnScreen.keySet().size()) {
            onUserWrong();
        }
    }

    @Subscribe
    public void onRainDropFell(OnRainDropFell event) {
        removeRainDropFromScreen(event.rainDropId, event.rainDropForRemove);
        //   removeAllRainDropsFromScreen();
        Log.d(TAG, "Raindrop with id: " + event.rainDropId + " fell before user has answered it. activate panelty");
    }

    private void removeAllRainDropsFromScreen() {
        for (Map.Entry<String, RainDropView> entry : allRainDropsOnScreen.entrySet()) {
            String id = entry.getKey();
            View rainDropView = entry.getValue().getView();
            removeRainDropFromScreen(id, rainDropView);
        }
        allRainDropsOnScreen.clear();
    }


    private void onUserWrong() {
        Integer currentScore = Integer.valueOf(score.get());
        Integer scorePanelty = currentScore * 5 / 100; //Decrease 5% from total score
        score.set(String.valueOf(currentScore - scorePanelty));
        Log.d(TAG, "User was wrong, score panelty: " + scorePanelty);
    }

    private void removeRainDropFromScreen(String id, View rainDrop) {
        allRainDropsOnScreen.remove(id);
        gameActivity.getBinding().gameViewLayout.removeView(rainDrop);
    }
}