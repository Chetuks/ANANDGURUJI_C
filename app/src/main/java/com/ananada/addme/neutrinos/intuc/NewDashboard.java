package com.ananada.addme.neutrinos.intuc;

import android.app.ProgressDialog;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

public class NewDashboard extends android.support.v4.app.Fragment {
    View root;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static DashBoardActivity newInstance() {
        DashBoardActivity fragment = new DashBoardActivity();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.activity_new_dashboard, container, false);
        return root;
    }
}
