package com.vaidoos.eventbusdemo.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vaidoos.eventbusdemo.events.ActivityToFragmentEvent;
import com.vaidoos.eventbusdemo.events.CategoryIdEvent;
import com.vaidoos.eventbusdemo.interfaces.OnDataPassListener;
import com.vaidoos.eventbusdemo.model.AllCategoryModel;
import com.vaidoos.eventbusdemo.R;
import com.vaidoos.eventbusdemo.adapter.MainCategoryAdapter;
import com.vaidoos.eventbusdemo.networking.EmailOptions;
import com.vaidoos.eventbusdemo.networking.interfaces.VolleyCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment implements VolleyCallback,OnDataPassListener{

    private static final String TAG = "FirstFragment";
    
    private RecyclerView recyFirstCategory;

    private String responceReceiveType = "";
    private String rptType = "",
            fillParam1 = "",
            fillParam2 = "",
            fillParam3 = "",
            fillParam4 = "",
            fillParam5 = "",
            fillParam6 = "",
            fillParam7 = "",
            fillParam8 = "",
            fillParam9 = "",
            fillParam0 = "",
            companyId = "",
            userId = "",
            _lat = "",
            _lang = "";
    private RecyclerView recyMainCategory;
    private AllCategoryModel[] productModels;
    private ProgressDialog progressDialog;
    private TextView tvFirstFragment;


    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        EventBus.getDefault().register(this);

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_first, container, false);

        tvFirstFragment = view.findViewById(R.id.tvFirstFragment);

        recyMainCategory = view.findViewById(R.id.recyFirstCategory);
        recyMainCategory.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.show();

        responceReceiveType = "fillPC";
        rptType = "fillPC";
        companyId = "01";

        Log.d(TAG, "onClick: All Data : " + rptType + " " + fillParam1 + " " + fillParam2 + " " + fillParam3 + " " + fillParam4 + " " + fillParam5 + " " + fillParam6 + " " + fillParam7 + " " + fillParam8 + " " + fillParam9 + " " + fillParam0 + " " + companyId + " " + userId + " " + _lat + " " + _lang + " ");

        EmailOptions emailOptions = new EmailOptions(getActivity());
        emailOptions.setEmailOptionsListener(this);
        emailOptions.apiLocation(rptType, fillParam1, fillParam2, fillParam3, fillParam4, fillParam5, fillParam6, fillParam7, fillParam8, fillParam9, fillParam0, companyId, userId, _lat, _lang);


        return view;
    }

    @Override
    public void onSuccessResponse(String response) {

        progressDialog.dismiss();

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        productModels = gson.fromJson(response,AllCategoryModel[].class);

        MainCategoryAdapter mainCategoryAdapter = new MainCategoryAdapter(getActivity(),productModels);
        mainCategoryAdapter.setOnDataPassListener(this);
        recyMainCategory.setAdapter(mainCategoryAdapter);
    }

    @Subscribe
    public void onFirstMessage(ActivityToFragmentEvent activityToFragmentEvent) {

        String message = activityToFragmentEvent.getCustomMessage();
        tvFirstFragment.setText("First Fragment: "+message);
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // unregister the registered event.
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDataPass(String data) {
        tvFirstFragment.setText(data);

        CategoryIdEvent categoryIdEvent = new CategoryIdEvent();
        categoryIdEvent.setCategoryId(data);
        EventBus.getDefault().post(categoryIdEvent);
    }

}
