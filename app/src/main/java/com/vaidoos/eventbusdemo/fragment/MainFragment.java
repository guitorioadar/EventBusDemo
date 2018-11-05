package com.vaidoos.eventbusdemo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.vaidoos.eventbusdemo.R;
import com.vaidoos.eventbusdemo.events.ActivityToFragmentEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    private TextView tvMainFragment;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        EventBus.getDefault().register(this);

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_main, container, false);

        tvMainFragment = view.findViewById(R.id.tvMainFragment);

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.firstFragment,new FirstFragment());
        fragmentTransaction.commit();

        FragmentTransaction fragmentTransaction1 = getFragmentManager().beginTransaction();
        fragmentTransaction1.replace(R.id.secondFragment,new SecondFragment());
        fragmentTransaction1.commit();

        return view;

    }

    @Subscribe
    public void onMainFragMessage(ActivityToFragmentEvent activityToFragmentEvent) {

        String message = activityToFragmentEvent.getCustomMessage();
        tvMainFragment.setText("Main Fragment: "+message);
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // unregister the registered event.
        EventBus.getDefault().unregister(this);
    }

}
