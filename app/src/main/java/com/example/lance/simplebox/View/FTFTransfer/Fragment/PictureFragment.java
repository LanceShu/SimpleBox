package com.example.lance.simplebox.View.FTFTransfer.Fragment;

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
import com.example.lance.simplebox.Adapter.FTFPictureAdapter;
import com.example.lance.simplebox.R;
import com.example.lance.simplebox.View.FTFTransfer.Utils.ScanImageUtil;
import static com.example.lance.simplebox.View.FTFTransfer.FTFContent.FTFContent.*;

/**
 * Created by Lance on 2017/11/21.
 */

public class PictureFragment extends Fragment {

    private View view;
    private RecyclerView picRecycler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.list_picture,container,false);
        initData();
        initWight(view);
        return view;
    }

    private void initData() {
        if(pictureBeans == null){
            pictureBeans = ScanImageUtil.INSTANCE.scanImageFile(getContext());
            for(int i = 0;i<pictureBeans.size();i++){
                Log.e("picturesSize:",pictureBeans.get(i).getImagePath());
            }
        }else{
            for(int i = 0;i<pictureBeans.size();i++){
                Log.e("picturesSize:",pictureBeans.get(i).getImagePath());
            }
        }
    }

    private void initWight(View view){
        picRecycler = (RecyclerView) view.findViewById(R.id.picture_recycler);

        if(pictureBeans.size() != 0){
            StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
            picRecycler.setLayoutManager(layoutManager);
            FTFPictureAdapter adapter = new FTFPictureAdapter(pictureBeans,getContext());
            picRecycler.setAdapter(adapter);
        }

    }
}
