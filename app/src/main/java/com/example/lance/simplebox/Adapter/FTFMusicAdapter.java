package com.example.lance.simplebox.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lance.simplebox.DataBean.MusicBean;
import com.example.lance.simplebox.R;

import java.util.List;

/**
 * Created by Lance on 2017/11/29.
 */

public class FTFMusicAdapter extends RecyclerView.Adapter<FTFMusicAdapter.ViewHolder> {

    private Context context;
    private List<MusicBean> musicBeans;

    public FTFMusicAdapter(Context context,List<MusicBean> musicBeans){
        this.context = context;
        this.musicBeans = musicBeans;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ftf_music_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final MusicBean musicBean = musicBeans.get(musicBeans.size()-1-position);
        if(musicBean.isSelected()){
            holder.musicSelected.setImageResource(R.mipmap.select2);
        }else{
            holder.musicSelected.setImageResource(R.mipmap.select1);
        }

        Glide.with(context).load(musicBean.getAlbum_id()).error(R.mipmap.ftf_music).into(holder.musicImage);

        holder.musicName.setText(musicBean.getMusicName());
        holder.musicSize.setText(musicBean.getMusicSize());
        holder.musicLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(musicBean.isSelected()){
                    holder.musicSelected.setImageResource(R.mipmap.select1);
                    musicBeans.get(musicBeans.size()-1-position).setSelected(false);
                }else{
                    holder.musicSelected.setImageResource(R.mipmap.select2);
                    musicBeans.get(musicBeans.size()-1-position).setSelected(true);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return musicBeans.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout musicLayout;
        private ImageView musicSelected;
        private ImageView musicImage;
        private TextView musicName;
        private TextView musicSize;

        public ViewHolder(View view) {
            super(view);
            musicLayout = (LinearLayout) view.findViewById(R.id.music_layout);
            musicSelected = (ImageView) view.findViewById(R.id.music_isSeleted);
            musicImage = (ImageView) view.findViewById(R.id.music_image);
            musicName = (TextView) view.findViewById(R.id.music_name);
            musicSize = (TextView) view.findViewById(R.id.music_size);
        }
    }
}
