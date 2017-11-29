package com.example.lance.simplebox.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lance.simplebox.DataBean.AudioBean;
import com.example.lance.simplebox.DataBean.MusicBean;
import com.example.lance.simplebox.R;

import java.util.List;

/**
 * Created by Lance on 2017/11/29.
 */

public class FTFAudioAdapter extends RecyclerView.Adapter<FTFAudioAdapter.ViewHolder> {

    private Context context;
    private List<AudioBean> audioBeans;

    public FTFAudioAdapter(Context context, List<AudioBean> audioBeans){
        this.context = context;
        this.audioBeans = audioBeans;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ftf_media_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final AudioBean audioBean = audioBeans.get(audioBeans.size()-1-position);
        if(audioBean.isSelected()){
            holder.audioSelected.setImageResource(R.mipmap.ftf_select2);
        }else{
            holder.audioSelected.setImageResource(R.mipmap.ftf_select1);
        }

        Glide.with(context)
                .load(audioBean.getAudioPath())
                .asBitmap()
                .error(R.mipmap.ftf_audio)
                .centerCrop()
                .into(holder.audioImage);

        holder.audioName.setText(audioBean.getAudioName());
        holder.audioSize.setText(audioBean.getAudioSize());
        holder.audioLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(audioBean.isSelected()){
                    holder.audioSelected.setImageResource(R.mipmap.ftf_select1);
                    audioBeans.get(audioBeans.size()-1-position).setSelected(false);
                }else{
                    holder.audioSelected.setImageResource(R.mipmap.ftf_select2);
                    audioBeans.get(audioBeans.size()-1-position).setSelected(true);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return audioBeans.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout audioLayout;
        private ImageView audioSelected;
        private ImageView audioImage;
        private TextView audioName;
        private TextView audioSize;

        public ViewHolder(View view) {
            super(view);
            audioLayout = (LinearLayout) view.findViewById(R.id.media_layout);
            audioSelected = (ImageView) view.findViewById(R.id.media_isSeleted);
            audioImage = (ImageView) view.findViewById(R.id.media_image);
            audioName = (TextView) view.findViewById(R.id.media_name);
            audioSize = (TextView) view.findViewById(R.id.media_size);
        }
    }
}
