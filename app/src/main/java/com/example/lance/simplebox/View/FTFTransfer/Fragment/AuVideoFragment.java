package com.example.lance.simplebox.View.FTFTransfer.Fragment;

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
import com.example.lance.simplebox.Adapter.FTFMusicAdapter;
import com.example.lance.simplebox.R;
import com.example.lance.simplebox.View.FTFTransfer.Utils.ScanAudioUtil;
import com.example.lance.simplebox.View.FTFTransfer.Utils.ScanMusicUtil;

import java.io.File;
import static com.example.lance.simplebox.View.FTFTransfer.FTFContent.FTFContent.*;

/**
 * Created by Lance on 2017/11/21.
 */

public class AuVideoFragment extends Fragment {

    private View view;
    private LinearLayout musicExpand;
    private LinearLayout audioExpand;
    private ImageView musicArrow;
    private ImageView audioArrow;
    private RecyclerView musicRecycler;
    private RecyclerView audioRecycler;

    private String builder;

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
        if(musicBeans == null){
            musicBeans = ScanMusicUtil.INSTANCE.scanMusicFile(getContext());
            builder = "";
            for(int i =0 ;i<musicBeans.size();i++){
                builder += musicBeans.get(i).getMusicName()+"-----"+musicBeans.get(i).getMusicPath()+"-----"+musicBeans.get(i).getMusicSize()+"---"+musicBeans.get(i).getAlbum_id()+"\n";
                Log.e("music"+i,builder);
            }
        }else{
            builder = "";
            for(int i =0 ;i<musicBeans.size();i++){
                builder += musicBeans.get(i).getMusicName()+"-----"+musicBeans.get(i).getMusicPath()+"-----"+musicBeans.get(i).getMusicSize()+"\n";
                Log.e("music"+i,builder);
            }
        }

        if(audioBeans == null){
            audioBeans = ScanAudioUtil.INSTANCE.scanAudioFile(getContext());
            for(int i =0 ;i<audioBeans.size();i++){
                Log.e("music"+i, audioBeans.get(i).getAudioName()+"-----"+audioBeans.get(i).getAudioPath()+"-----"+audioBeans.get(i).getAudioSize());
            }
        }else{
            for(int i =0 ;i<audioBeans.size();i++){
                Log.e("music"+i, audioBeans.get(i).getAudioName()+"-----"+audioBeans.get(i).getAudioPath()+"-----"+audioBeans.get(i).getAudioSize());
            }
        }

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

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        musicRecycler.setLayoutManager(layoutManager);
        FTFMusicAdapter musicAdapter = new FTFMusicAdapter(getContext(),musicBeans);
        musicRecycler.setAdapter(musicAdapter);
    }
}
