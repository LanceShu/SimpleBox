package com.example.lance.simplebox.MVPContract;

/**
 * Created by Lance on 2017/10/13.
 */

public class MVPContract {

    public interface  View{
        void showTimeData(String data);
    }

    public interface  Persenter{
        void getTimeData();
    }

    public interface  Mode{
        String doTimeData();
    }
}
