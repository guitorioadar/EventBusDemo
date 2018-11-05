package com.vaidoos.eventbusdemo;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.vaidoos.eventbusdemo.events.ActivityToActivityEvent;
import com.vaidoos.eventbusdemo.events.ActivityToFragmentEvent;

import org.greenrobot.eventbus.EventBus;

public class SecondActivity extends AppCompatActivity {

    private EditText editTextMessage;
    private Button btnSendMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        editTextMessage = findViewById(R.id.editTextMessage);
        btnSendMessage = findViewById(R.id.btnSendMessage);
        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityToActivityEvent event = new ActivityToActivityEvent();
                event.setMessage(editTextMessage.getText().toString());
                EventBus.getDefault().post(event);

                ActivityToFragmentEvent acfEvent = new ActivityToFragmentEvent();
                acfEvent.setCustomMessage(editTextMessage.getText().toString());
                EventBus.getDefault().post(acfEvent);

                finish();
            }
        });

    }
}
