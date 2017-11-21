package com.example.lance.simplebox.View.Main.Persenter;

import com.example.lance.simplebox.View.Main.Contract.TimeContract;

/**
 * Created by Lance on 2017/10/13.
 */

public class TimePersenter implements TimeContract.TimePersenter{

    private TimeContract.TimeMode mode;
    private TimeContract.TimeView view;

    public TimePersenter(TimeContract.TimeMode mode, TimeContract.TimeView view){
        this.mode = mode;
        this.view = view;
    }

    public void getTimeData(){
        view.showTimeData(mode.doTimeData());
    }
}
