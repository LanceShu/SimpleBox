package com.example.lance.simplebox.View.FTFTransfer.Fragment;

import android.animation.ObjectAnimator;
import android.content.pm.PackageManager;
import android.os.Bundle;
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

import com.example.lance.simplebox.Adapter.FTFApkAdapter;
import com.example.lance.simplebox.R;
import com.example.lance.simplebox.View.FTFTransfer.Utils.ScanAPKUtil;

import java.io.File;
import java.util.Date;
import java.util.List;

import static com.example.lance.simplebox.View.FTFTransfer.FTFContent.FTFContent.*;

/**
 * Created by Lance on 2017/11/21.
 */

public class ApkFragment extends Fragment implements View.OnClickListener{

    private View view;
    private LinearLayout apkLayout;
    private LinearLayout sysApkLayout;
    private ImageView apkArrow;
    private ImageView sysApkArrow;
    private RecyclerView apkRecycler;
    private RecyclerView sysApkRecycler;

    private boolean isApkOpen;
    private boolean isSysApkOpen;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.list_apk,container,false);
        //初始化数据;
        initData();
        //初始化控件;
        initWight(view);
        return view;
    }

    private void initData() {
        /**
         * 初始化数据部分;
         * */
        isApkOpen = true;
        isSysApkOpen = false;
    }

    private void initWight(View view) {
        apkLayout = (LinearLayout) view.findViewById(R.id.ftf_apk);
        sysApkLayout = (LinearLayout) view.findViewById(R.id.ftf_sysApk);
        apkArrow = (ImageView) view.findViewById(R.id.ftf_apk_arrow);
        sysApkArrow = (ImageView) view.findViewById(R.id.ftf_sysApk_arrow);
        apkRecycler = (RecyclerView) view.findViewById(R.id.ftf_apk_recycle);
        sysApkRecycler = (RecyclerView) view.findViewById(R.id.ftf_sysApk_recycle);

        /**
         * 初始化文件夹状态;
         * */
        if(isApkOpen){
            openDiretory(apkArrow);
        }else{
            closeDiretory(apkArrow);
        }

        if(isSysApkOpen){
            openDiretory(sysApkArrow);
        }else {
            closeDiretory(sysApkArrow);
        }

        /**
         * 点击事件;
         * */
        apkLayout.setOnClickListener(this);
        sysApkLayout.setOnClickListener(this);

        apkRecycler.setVisibility(View.VISIBLE);
        sysApkRecycler.setVisibility(View.GONE);

        /**
         * 设置RecycleView的布局与adapter;
         * */
        LinearLayoutManager apkManager = new LinearLayoutManager(getContext());
        apkManager.setOrientation(LinearLayoutManager.VERTICAL);
        apkRecycler.setLayoutManager(apkManager);
        FTFApkAdapter apkAdapter = new FTFApkAdapter(getContext(),apkList);
        apkRecycler.setAdapter(apkAdapter);

        LinearLayoutManager sysApkManager = new LinearLayoutManager(getContext());
        sysApkManager.setOrientation(LinearLayoutManager.VERTICAL);
        sysApkRecycler.setLayoutManager(sysApkManager);
        FTFApkAdapter sysApkAdapter = new FTFApkAdapter(getContext(),sysApkList);
        sysApkRecycler.setAdapter(sysApkAdapter);
    }

    //打开文件夹动画;
    private void openDiretory(View v){
        ObjectAnimator animator = ObjectAnimator.ofFloat(v,"rotation",0,90);
        animator.setDuration(400);
        animator.start();
    }

    //关闭文件夹的动画;
    private void closeDiretory(View v){
        ObjectAnimator animator = ObjectAnimator.ofFloat(v,"rotation",90,0);
        animator.setDuration(400);
        animator.start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ftf_apk:
                if(isApkOpen){
                    apkRecycler.setVisibility(View.GONE);
                    closeDiretory(apkArrow);
                    isApkOpen = false;
                }else {
                    apkRecycler.setVisibility(View.VISIBLE);
                    openDiretory(apkArrow);
                    isApkOpen = true;
                }
                break;
            case R.id.ftf_sysApk:
                if(isSysApkOpen){
                    apkLayout.setVisibility(View.VISIBLE);

                    sysApkRecycler.setVisibility(View.GONE);
                    closeDiretory(sysApkArrow);
                    isSysApkOpen = false;
                }else{
                    apkLayout.setVisibility(View.GONE);

                    sysApkRecycler.setVisibility(View.VISIBLE);
                    openDiretory(sysApkArrow);
                    isSysApkOpen = true;
                }
                break;
        }
    }
}
