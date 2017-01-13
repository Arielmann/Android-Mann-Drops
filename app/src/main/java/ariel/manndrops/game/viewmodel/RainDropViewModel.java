package ariel.manndrops.game.viewmodel;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import org.greenrobot.eventbus.EventBus;

import java.util.Random;

import ariel.manndrops.game.model.RainDropModel;
import ariel.manndrops.game.viewmodel.events.OnRainDropFell;
import ariel.manndrops.game.viewmodel.exercisesmanagment.generation.model.ExerciseDataLevel;
import ariel.manndrops.profile.SharedPrefManager;

public class RainDropViewModel implements ViewModel {

    private static final String TAG = RainDropViewModel.class.getName();
    public ObservableField<String> exercise = new ObservableField<>("");
    public ObservableInt rainDropXCoor = new ObservableInt();
    public ObservableInt rainDropYCoor = new ObservableInt();
    public RainDropModel model;
    private String rainDropId;
    private View rainDropLayout;

    public RainDropViewModel(Context context, View rainDropLayout, ExerciseDataLevel level, String rainDropId) {
        this.rainDropLayout = rainDropLayout;
        model = new RainDropModel(level.generateExercise());
        exercise.set(model.getExercise());
        rainDropXCoor.set(generateXCoor(context));
        this.rainDropId = rainDropId;
    }

    private static int generateXCoor(Context context) {
        Random r = new Random();
        int max = -80;
        int min = -SharedPrefManager.getInstance(context).getUserDeviceScreenWidth() + 110;
        return r.nextInt(max - min) + min;
    }

    public void dropAnimation(Context context) {
        final int screenHeight = SharedPrefManager.getInstance(context).getUserDeviceScreenHeight();
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, screenHeight * 55 / 100); //stop fropping in 55% of user's screen
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer updatedYCoor = Math.round((Float) animation.getAnimatedValue());
                rainDropYCoor.set(updatedYCoor);
            }
        });

        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                EventBus.getDefault().post(new OnRainDropFell(rainDropId, rainDropLayout));
            }
        });

        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(7000);
        valueAnimator.start();
        Log.d(TAG, "RainDrop created. Xcoor: " + rainDropXCoor + " Exercise: " + exercise);
        //startValueAnimator(valueAnimator);
    }

    @Override
    public void onDestroy() {
        rainDropLayout = null;
    }

    public String getAnswer(){
        return model.getAnswer();
    }
}

