package com.example.kevin.uv_dn5;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    ImageButton pets, dino;

    @Override
    protected void onResume()
    {
        super.onResume();
        hideAndroidApplicationBar();
    }

    private void hideAndroidApplicationBar()
    {
        final View view = getWindow().getDecorView();
        // Hide Android Soft keys
        view.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide navigation bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        setContentView(R.layout.activity_main);

        pets = (ImageButton) findViewById(R.id.imageButton3);
        dino = (ImageButton) findViewById(R.id.imageButton2);
        Intent intent = new Intent(getApplicationContext(),game.class);
        //startActivity(intent);
        //finish();

        pets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),game.class);
                startActivity(intent);
            }
        });
        dino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),dino.class);
                startActivity(intent);
            }
        });

    }



    public void dino(){


    }
}
