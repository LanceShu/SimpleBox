package com.example.lance.simplebox.View.FTFTransfer.Fragment;

import android.gesture.GestureLibraries;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lance.simplebox.Content.Content;
import com.example.lance.simplebox.DataBean.AudioBean;
import com.example.lance.simplebox.DataBean.MusicBean;
import com.example.lance.simplebox.R;
import com.example.lance.simplebox.View.FTFTransfer.Utils.ScanAudioUtil;
import com.example.lance.simplebox.View.FTFTransfer.Utils.ScanMusicUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lance on 2017/11/21.
 */

public class AuVideoFragment extends Fragment {

    private View view;
    private TextView vedio;
    private ImageView imageView;

    private String builder;

    private ArrayList<AudioBean> audioBeanList;
    private static final File audioFile = Environment.getExternalStorageDirectory();

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
        builder = "";
        for(int i =0 ;i<musicBeanList.size();i++){
            builder += musicBeanList.get(i).getMusicName()+"-----"+musicBeanList.get(i).getMusicPath()+"-----"+musicBeanList.get(i).getMusicSize()+"\n";
            Log.e("music"+i,musicBeanList.get(i).getMusicName()+"-----"+musicBeanList.get(i).getMusicPath()+"-----"+musicBeanList.get(i).getMusicSize());
        }

        audioBeanList = ScanAudioUtil.INSTANCE.scanAudioFile(getContext());
        for(int i =0 ;i<audioBeanList.size();i++){
            Log.e("music"+i, audioBeanList.get(i).getAudioName()+"-----"+audioBeanList.get(i).getAudioPath()+"-----"+audioBeanList.get(i).getAudioSize());
        }

    }

    /**
     * 初始化控件;
     * */
    private void initWight(View view) {
        vedio = (TextView) view.findViewById(R.id.ftf_vedio);
        imageView = (ImageView) view.findViewById(R.id.audioimage);

        vedio.setText(builder.toString());
        Glide.with(getContext())
                .load(audioBeanList.get(3).getAudioPath())
                .asBitmap()
                .override(300,300)
                .centerCrop()
                .into(imageView);
    }
}
