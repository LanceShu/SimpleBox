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
import com.example.lance.simplebox.DataBean.FileBean;
import com.example.lance.simplebox.DataBean.SendFileBean;
import com.example.lance.simplebox.R;

import java.util.List;

import static com.example.lance.simplebox.View.FTFTransfer.FTFContent.FTFContent.sendFileBeans;

/**
 * Created by Lance on 2017/11/29.
 */

public class FTFSendAdapter extends RecyclerView.Adapter<FTFSendAdapter.ViewHolder> {

    private Context context;
    private List<SendFileBean> sendFileBeans;

    public FTFSendAdapter(Context context, List<SendFileBean> sendFileBeans){
        this.context = context;
        this.sendFileBeans = sendFileBeans;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ftf_send_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final SendFileBean sendFileBean = sendFileBeans.get(position);

        if(sendFileBean.getSendType().equals("image") || sendFileBean.getSendType().equals("audio")){
            Glide.with(context)
                    .load(sendFileBean.getSendPath())
                    .centerCrop()
                    .error(R.mipmap.ic_launcher)
                    .into(holder.sendImage);
        }else {
            Glide.with(context)
                    .load(Integer.parseInt(sendFileBean.getSendIcon()))
                    .centerCrop()
                    .error(R.mipmap.ic_launcher)
                    .into(holder.sendImage);
        }

        holder.sendName.setText(sendFileBean.getSendName());
        holder.sendSize.setText(sendFileBean.getSendSize() + "M");
    }

    @Override
    public int getItemCount() {
        return sendFileBeans.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView sendImage;
        private TextView sendName;
        private TextView sendSize;

        public ViewHolder(View view) {
            super(view);
            sendImage = (ImageView) view.findViewById(R.id.media_image);
            sendName = (TextView) view.findViewById(R.id.media_name);
            sendSize = (TextView) view.findViewById(R.id.media_size);
        }
    }
}
