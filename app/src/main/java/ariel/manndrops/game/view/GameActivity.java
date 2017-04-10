package ariel.manndrops.game.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import ariel.manndrops.R;
import ariel.manndrops.game.viewmodel.GameViewModel;
import ariel.manndrops.databinding.ActivityGameBinding;

public class GameActivity extends AppCompatActivity {

    // public static ExerciseDataLevel currentLevel = ExerciseDataLevel.EASY;

    private ActivityGameBinding binding;
    private GameViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_game);
        viewModel = new GameViewModel(this);
        binding.setViewModel(viewModel);
    }

    @Override
    public void onDestroy() {
        viewModel.onDestroy();
        super.onDestroy();
    }

    public ActivityGameBinding getBinding() {
        return binding;
    }
}
