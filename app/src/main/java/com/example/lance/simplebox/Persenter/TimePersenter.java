package com.example.lance.simplebox.Persenter;

import com.example.lance.simplebox.MVPContract.MVPContract;

/**
 * Created by Lance on 2017/10/13.
 */

public class TimePersenter implements MVPContract.TimePersenter{

    private MVPContract.TimeMode mode;
    private MVPContract.TimeView view;

    public TimePersenter(MVPContract.TimeMode mode, MVPContract.TimeView view){
        this.mode = mode;
        this.view = view;
    }

    public void getTimeData(){
        view.showTimeData(mode.doTimeData());
    }
}
