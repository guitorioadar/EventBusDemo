package com.vaidoos.eventbusdemo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.vaidoos.eventbusdemo.R;
import com.vaidoos.eventbusdemo.events.ActivityToFragmentEvent;
import com.vaidoos.eventbusdemo.events.CategoryIdEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {

    private static final String TAG = "SecondFragment";

    private TextView secondTextView;

    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // register the event to listen.
        EventBus.getDefault().register(this);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        secondTextView = view.findViewById(R.id.secondTextView);

        return view;
    }


    @Subscribe
    public void onSecondMessage(ActivityToFragmentEvent activityToFragmentEvent) {

        String message = activityToFragmentEvent.getCustomMessage();
        secondTextView.setText("Second Fragment: "+message);
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Subscribe
    public void onCategorySet(CategoryIdEvent categoryIdEvent){
        secondTextView.setText("Second Fragment: "+categoryIdEvent.getCategoryId());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // unregister the registered event.
        EventBus.getDefault().unregister(this);
    }

}
