package com.example.lance.simplebox.Persenter;

import com.example.lance.simplebox.MVPContract.MVPContract;

/**
 * Created by Lance on 2017/10/13.
 */

public class PersenterText implements MVPContract.Persenter{

    private MVPContract.Mode mode;
    private MVPContract.View view;

    public PersenterText(MVPContract.Mode mode,MVPContract.View view){
        this.mode = mode;
        this.view = view;
    }

    public void getTimeData(){
        view.showTimeData(mode.doTimeData());
    }
}
