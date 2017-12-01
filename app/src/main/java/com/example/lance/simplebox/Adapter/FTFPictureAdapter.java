package com.example.lance.simplebox.Adapter;

import android.content.Context;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.example.lance.simplebox.Content.Content;
import com.example.lance.simplebox.DataBean.ImageBean;
import com.example.lance.simplebox.DataBean.SendFileBean;
import com.example.lance.simplebox.R;

import java.util.List;
import static com.example.lance.simplebox.View.FTFTransfer.FTFContent.FTFContent.*;

/**
 * Created by Lance on 2017/11/29.
 */

public class FTFPictureAdapter extends RecyclerView.Adapter<FTFPictureAdapter.ViewHolder> {

    private List<ImageBean> pictures;
    private Context context;

    public FTFPictureAdapter(List<ImageBean> pictures,Context context){
        this.pictures = pictures;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ftf_pic_item,parent,false);
        final ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final ImageBean imageBean = pictures.get(pictures.size()-1-position);
        Glide.with(context).load(imageBean.getImagePath()).centerCrop().into(holder.imageView);
        if(imageBean.isSelected()){
            holder.isSelected.setImageResource(R.mipmap.ftf_select2);
        }else{
            holder.isSelected.setImageResource(R.mipmap.ftf_select1);
        }
        holder.pictureLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SendFileBean sendFileBean = new SendFileBean();

                if(imageBean.isSelected()){
                    holder.isSelected.setImageResource(R.mipmap.ftf_select1);
                    pictures.get(pictures.size()-1-position).setSelected(false);

                    for(int i =0;i<sendFileBeans.size();i++){
                        if(sendFileBeans.get(i).getSendPath().equals(imageBean.getImagePath())){
                            sendFileBeans.remove(i);
                        }
                    }
                }else{
                    holder.isSelected.setImageResource(R.mipmap.ftf_select2);
                    pictures.get(pictures.size()-1-position).setSelected(true);

                    sendFileBean.setSendName(imageBean.getImageDisplayName());
                    sendFileBean.setSendPath(imageBean.getImagePath());
                    sendFileBean.setSendSize(imageBean.getImageSize());
                    sendFileBean.setSendIcon(imageBean.getImagePath());
                    sendFileBean.setSendType("image");
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
        return pictures.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout pictureLayout;
        private ImageView imageView;
        private ImageView isSelected;

        public ViewHolder(View view) {
            super(view);
            pictureLayout = (RelativeLayout) view.findViewById(R.id.picture_item);
            imageView = (ImageView) view.findViewById(R.id.picture_icon);
            isSelected = (ImageView) view.findViewById(R.id.pic_idSelected);
        }
    }
}
