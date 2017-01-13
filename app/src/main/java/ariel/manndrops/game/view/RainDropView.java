package ariel.manndrops.game.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import ariel.manndrops.R;
import ariel.manndrops.databinding.RaindropBinding;
import ariel.manndrops.game.viewmodel.RainDropViewModel;
import ariel.manndrops.game.viewmodel.exercisesmanagment.generation.model.ExerciseDataLevel;


public class RainDropView {
    private static final String TAG = RainDropView.class.getName();

    private RainDropViewModel viewModel;
    private RaindropBinding binding;

    public RainDropView(Context context, String id, RelativeLayout gameViewLayout, ExerciseDataLevel level) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.raindrop,
                gameViewLayout,
                true);
        viewModel = new RainDropViewModel(context, binding.rainDropLayout, level, id);
        binding.setViewModel(viewModel);
        viewModel.dropAnimation(context);
        Log.d(TAG, "Raindrop view created in GameViewModel. id: " + id);
    }

    public String getAnswer() {
        return viewModel.getAnswer();
    }

    public View getView() {
        return binding.rainDropLayout;
    }
}
