package com.vaidoos.eventbusdemo;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.vaidoos.eventbusdemo.events.ActivityToActivityEvent;
import com.vaidoos.eventbusdemo.events.CategoryIdEvent;
import com.vaidoos.eventbusdemo.fragment.MainFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private TextView tvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.framelayout,new MainFragment());
        fragmentTransaction.commit();

        tvMain = findViewById(R.id.tvMain);

        EventBus.getDefault().register(this);

    }


    @Subscribe
    public void onMainMessage(ActivityToActivityEvent event){
        Toast.makeText(this, "Event Message: "+event.getMessage(), Toast.LENGTH_SHORT).show();
        tvMain.setText("MainActivity: "+event.getMessage());
    }



    public void intentSecondtActivity(View view) {
        startActivity(new Intent(this,SecondActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
