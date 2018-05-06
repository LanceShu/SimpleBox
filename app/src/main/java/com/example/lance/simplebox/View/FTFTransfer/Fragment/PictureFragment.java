package com.example.lance.simplebox.View.FTFTransfer.Fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.example.lance.simplebox.Adapter.FTFPictureAdapter;
import com.example.lance.simplebox.Content.Content;
import com.example.lance.simplebox.R;
import com.example.lance.simplebox.View.FTFTransfer.Utils.ScanAPKUtil;
import com.example.lance.simplebox.View.FTFTransfer.Utils.ScanAudioUtil;
import com.example.lance.simplebox.View.FTFTransfer.Utils.ScanImageUtil;
import com.example.lance.simplebox.View.FTFTransfer.Utils.ScanMusicUtil;
import com.example.lance.simplebox.View.FTFTransfer.Utils.ScanOfficeFileUtil;

import static com.example.lance.simplebox.View.FTFTransfer.FTFContent.FTFContent.*;

/**
 * Created by Lance
 * on 2017/11/21.
 */

public class PictureFragment extends Fragment {

    private View view;
    private RecyclerView picRecycler;
    private ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.list_picture,container,false);

        initData();
        initWight(view);
        return view;
    }

    private void initData() {

        progressDialog = new ProgressDialog(getContext());
//        progressDialog.setContentView(R.layout.loading_layout);
//        ImageView imageView = (ImageView) progressDialog.findViewById(R.id.load_image);
        progressDialog.setMessage("扫描数据ing...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        if(pictureBeans == null){
            pictureBeans = ScanImageUtil.INSTANCE.scanImageFile(getContext());
        }

        if(musicBeans == null){
            musicBeans = ScanMusicUtil.INSTANCE.scanMusicFile(getContext());
        }

        if(audioBeans == null){
            audioBeans = ScanAudioUtil.INSTANCE.scanAudioFile(getContext());
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                if(wordList == null){
                    wordList = ScanOfficeFileUtil.INSTANCE.scanWordFile(getContext());
                }

                if(pptList == null){
                    pptList = ScanOfficeFileUtil.INSTANCE.scanPPTFile(getContext());
                }

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                if(excelList == null){
                    excelList = ScanOfficeFileUtil.INSTANCE.scanExcelFile(getContext());
                }
                if(pdfList == null){
                    pdfList = ScanOfficeFileUtil.INSTANCE.scanPDFFile(getContext());
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                if(apkList == null){
                    apkList = ScanAPKUtil.scanAPKsFile(getContext());
                }

                if(sysApkList == null){
                    sysApkList = ScanAPKUtil.scanSysAPKsFile(getContext());
                }

                progressDialog.dismiss();
            }
        }).start();

    }

    private void initWight(View view){
        picRecycler = (RecyclerView) view.findViewById(R.id.picture_recycler);

        if(pictureBeans != null){
            StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
            picRecycler.setLayoutManager(layoutManager);
            FTFPictureAdapter adapter = new FTFPictureAdapter(pictureBeans,getContext());
            picRecycler.setAdapter(adapter);
        }

    }
}
