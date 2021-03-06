package com.example.lance.simplebox.View.FTFTransfer.Fragment;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lance.simplebox.Adapter.FTFAudioAdapter;
import com.example.lance.simplebox.Adapter.FTFMusicAdapter;
import com.example.lance.simplebox.R;
import com.example.lance.simplebox.View.FTFTransfer.Utils.ScanAudioUtil;
import com.example.lance.simplebox.View.FTFTransfer.Utils.ScanMusicUtil;

import java.io.File;
import static com.example.lance.simplebox.View.FTFTransfer.FTFContent.FTFContent.*;

/**
 * Created by Lance on 2017/11/21.
 */

public class AuVideoFragment extends Fragment implements View.OnClickListener{

    private View view;
    private LinearLayout musicExpand;
    private LinearLayout audioExpand;
    private ImageView musicArrow;
    private ImageView audioArrow;
    private RecyclerView musicRecycler;
    private RecyclerView audioRecycler;

    private boolean isMusicOpen;
    private boolean isAudioOpen;

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

        isMusicOpen = true;
        isAudioOpen = false;

    }

    /**
     * 初始化控件;
     * */
    private void initWight(View view) {
        musicExpand = (LinearLayout) view.findViewById(R.id.ftf_music);
        audioExpand = (LinearLayout) view.findViewById(R.id.ftf_audio);
        musicArrow = (ImageView) view.findViewById(R.id.ftf_music_arrow);
        audioArrow = (ImageView) view.findViewById(R.id.ftf_audio_arrow);
        musicRecycler = (RecyclerView) view.findViewById(R.id.ftf_music_recycle);
        audioRecycler = (RecyclerView) view.findViewById(R.id.ftf_audio_recycle);

        if(isMusicOpen){
            openDiretory(musicArrow);
        }else {
            closeDiretory(musicArrow);
        }

        if(isAudioOpen){
            openDiretory(audioArrow);
        }else {
            closeDiretory(audioArrow);
        }

        /**
         * 音频与视频列表的点击事件;
         * */
        musicExpand.setOnClickListener(this);
        audioExpand.setOnClickListener(this);

        /**
         * 音频与视频列表是否可见（初始不可见）;
         * */
        musicRecycler.setVisibility(View.VISIBLE);
        audioRecycler.setVisibility(View.GONE);

       if(musicBeans != null){
           LinearLayoutManager musicLayoutManager = new LinearLayoutManager(getContext());
           musicLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

           musicRecycler.setLayoutManager(musicLayoutManager);
           FTFMusicAdapter musicAdapter = new FTFMusicAdapter(getContext(),musicBeans);
           musicRecycler.setAdapter(musicAdapter);
       }

        if(audioBeans != null){
            LinearLayoutManager audioLayoutManager = new LinearLayoutManager(getContext());
            audioLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

            audioRecycler.setLayoutManager(audioLayoutManager);
            FTFAudioAdapter audioAdapter = new FTFAudioAdapter(getContext(),audioBeans);
            audioRecycler.setAdapter(audioAdapter);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ftf_music:
                if(isMusicOpen){
                    musicRecycler.setVisibility(View.GONE);
                    closeDiretory(musicArrow);
                    isMusicOpen = false;
                }else{
                    musicRecycler.setVisibility(View.VISIBLE);
                    openDiretory(musicArrow);
                    isMusicOpen = true;
                }
                break;
            case R.id.ftf_audio:
                if(isAudioOpen){
                    musicExpand.setVisibility(View.VISIBLE);

                    audioRecycler.setVisibility(View.GONE);
                    closeDiretory(audioArrow);
                    isAudioOpen = false;
                }else{
                    musicExpand.setVisibility(View.GONE);

                    audioRecycler.setVisibility(View.VISIBLE);
                    openDiretory(audioArrow);
                    isAudioOpen = true;
                }
                break;
        }
    }

    /**
     * 打开文件夹动画；
     * */
    private void openDiretory(View v){
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(v,"rotation",0,90);
        objectAnimator.setDuration(300);
        objectAnimator.start();
    }

    /**
     * 关闭文件夹动画
     * */
    private void closeDiretory(View v){
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(v,"rotation",90,0);
        objectAnimator.setDuration(300);
        objectAnimator.start();
    }
}
