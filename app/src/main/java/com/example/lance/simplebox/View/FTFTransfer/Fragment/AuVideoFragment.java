package com.example.lance.simplebox.View.FTFTransfer.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lance.simplebox.R;

/**
 * Created by Lance on 2017/11/21.
 */

public class AuVideoFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.ftf_sender,container,false);
        return view;
    }
}
