package com.example.test_two_activities_with_sound;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.test_two_activities_with_sound.databinding.ActivityWithBackButtonBinding;

import java.util.Timer;
import java.util.TimerTask;

public class ActivityWithBackButton extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityWithBackButtonBinding binding;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityWithBackButtonBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_activity_with_back_button);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
/*
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                timerTick();
            }
        },0,5000);
        Toast.makeText(this, "To the processing activity in 5 seconds", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_activity_with_back_button);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }


    private Runnable doTask = new Runnable() {
        public void run() {
            toLaunchActivity();
        }
    };

    private void timerTick() {
        this.runOnUiThread(doTask);
    }

    public void toLaunchActivity(){
        timer.cancel();
        Intent myIntent = new Intent(this, MainActivity.class);
        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(myIntent);
        finish();
    }
}