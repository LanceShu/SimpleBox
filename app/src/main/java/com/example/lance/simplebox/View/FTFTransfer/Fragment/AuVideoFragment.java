package com.example.lance.simplebox.View.FTFTransfer.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lance.simplebox.Mode.MusicBean;
import com.example.lance.simplebox.R;
import com.example.lance.simplebox.View.FTFTransfer.Utils.ScanMusicUtil;

import java.util.List;

/**
 * Created by Lance on 2017/11/21.
 */

public class AuVideoFragment extends Fragment {

    private View view;
    private TextView vedio;

    private StringBuilder builder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.list_audio_vedio,container,false);
        initData();
        initWight(view);
        return view;
    }

    /**
     * 初始化数据;
     * */
    private void initData() {
        List<MusicBean> musicBeanList = ScanMusicUtil.INSTANCE.scanMusicFile(getContext());
        Log.e("musicSize:",musicBeanList.size()+"");
        builder = new StringBuilder();
        for(int i =0 ;i<musicBeanList.size();i++){
            builder.append(musicBeanList.get(i).getMusicName()+"-----"+musicBeanList.get(i).getMusicPath()+"-----"+musicBeanList.get(i).getMusicSize()+"\n");
            Log.e("music"+i,musicBeanList.get(i).getMusicName()+"-----"+musicBeanList.get(i).getMusicPath()+"-----"+musicBeanList.get(i).getMusicSize());
        }
    }


    /**
     * 初始化控件;
     * */
    private void initWight(View view) {
        vedio = (TextView) view.findViewById(R.id.ftf_vedio);

        vedio.setText(builder.toString());
    }
}
