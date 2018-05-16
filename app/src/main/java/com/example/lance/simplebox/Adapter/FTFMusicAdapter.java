package com.example.lance.simplebox.Adapter;

import android.content.Context;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lance.simplebox.Content.Content;
import com.example.lance.simplebox.DataBean.MusicBean;
import com.example.lance.simplebox.DataBean.SendFileBean;
import com.example.lance.simplebox.R;

import java.util.List;

import static com.example.lance.simplebox.View.FTFTransfer.FTFContent.FTFContent.sendFileBeans;

/**
 * Created by Lance
 * on 2017/11/29.
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ftf_media_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final MusicBean musicBean = musicBeans.get(musicBeans.size()-1-position);
        if(musicBean.isSelected()){
            holder.musicSelected.setImageResource(R.mipmap.ftf_select2);
        }else{
            holder.musicSelected.setImageResource(R.mipmap.ftf_select1);
        }

        Glide.with(context).load(musicBean.getAlbum_id()).error(R.mipmap.ftf_music).into(holder.musicImage);

        holder.musicName.setText(musicBean.getMusicName());
        holder.musicSize.setText(musicBean.getMusicSize() + "M");
        holder.musicLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SendFileBean sendFileBean = new SendFileBean();

                if(musicBean.isSelected()){
                    holder.musicSelected.setImageResource(R.mipmap.ftf_select1);
                    musicBeans.get(musicBeans.size()-1-position).setSelected(false);

                    for(int i =0;i<sendFileBeans.size();i++){
                        if(sendFileBeans.get(i).getSendPath().equals(musicBean.getMusicPath())){
                            sendFileBeans.remove(i);
                        }
                    }
                }else{
                    holder.musicSelected.setImageResource(R.mipmap.ftf_select2);
                    musicBeans.get(musicBeans.size()-1-position).setSelected(true);

                    sendFileBean.setSendName(musicBean.getMusicName());
                    sendFileBean.setSendPath(musicBean.getMusicPath());
                    sendFileBean.setSendSize(musicBean.getMusicSize());
                    sendFileBean.setSendIcon(R.mipmap.ftf_music + "");
                    sendFileBean.setSendType("music");
                    sendFileBeans.add(sendFileBean);
                }

                Message message = Message.obtain();
                message.what = Content.SEND_FILE_LIST;
                Content.FTFhandler.sendMessage(message);
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

        ViewHolder(View view) {
            super(view);
            musicLayout = (LinearLayout) view.findViewById(R.id.media_layout);
            musicSelected = (ImageView) view.findViewById(R.id.media_isSeleted);
            musicImage = (ImageView) view.findViewById(R.id.media_image);
            musicName = (TextView) view.findViewById(R.id.media_name);
            musicSize = (TextView) view.findViewById(R.id.media_size);
        }
    }
}
