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
import com.example.lance.simplebox.DataBean.ApkBean;
import com.example.lance.simplebox.DataBean.FileBean;
import com.example.lance.simplebox.DataBean.SendFileBean;
import com.example.lance.simplebox.R;

import java.util.List;

import static com.example.lance.simplebox.View.FTFTransfer.FTFContent.FTFContent.sendFileBeans;

/**
 * Created by Lance on 2017/11/29.
 */

public class FTFApkAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<ApkBean> apkBeans;

    public FTFApkAdapter(Context context, List<ApkBean> apkBeans){
        this.context = context;
        this.apkBeans = apkBeans;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ftf_media_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int position) {
        final ViewHolder holder = (ViewHolder) viewHolder;
        final ApkBean apkBean = apkBeans.get(apkBeans.size()-1-position);
        if(apkBean.getApkSelected()){
            holder.apkSelected.setImageResource(R.mipmap.ftf_select2);
        }else{
            holder.apkSelected.setImageResource(R.mipmap.ftf_select1);
        }

        holder.apkImage.setImageDrawable(apkBean.getApkIcon());

        holder.apkName.setText(apkBean.getApkName());
        holder.apkSize.setText(apkBean.getApkDate() + "  " + apkBean.getApkSize() + "M");
        holder.apkLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SendFileBean sendFileBean = new SendFileBean();

                if(apkBean.getApkSelected()){
                    holder.apkSelected.setImageResource(R.mipmap.ftf_select1);
                    apkBeans.get(apkBeans.size()-1-position).setApkSelected(false);

                    for(int i =0;i<sendFileBeans.size();i++){
                        if(sendFileBeans.get(i).getSendPath().equals(apkBean.getApkPath())){
                            sendFileBeans.remove(i);
                        }
                    }
                }else{
                    holder.apkSelected.setImageResource(R.mipmap.ftf_select2);
                    apkBeans.get(apkBeans.size()-1-position).setApkSelected(true);

                    sendFileBean.setSendName(apkBean.getApkName());
                    sendFileBean.setSendPath(apkBean.getApkPath());
                    sendFileBean.setSendSize(apkBean.getApkSize());
                    sendFileBean.setSendIcon(R.mipmap.ic_launcher_round+"");
                    sendFileBean.setSendType("apk");
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
        return apkBeans.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout apkLayout;
        private ImageView apkSelected;
        private ImageView apkImage;
        private TextView apkName;
        private TextView apkSize;

        public ViewHolder(View view) {
            super(view);
            apkLayout = (LinearLayout) view.findViewById(R.id.media_layout);
            apkSelected = (ImageView) view.findViewById(R.id.media_isSeleted);
            apkImage = (ImageView) view.findViewById(R.id.media_image);
            apkName = (TextView) view.findViewById(R.id.media_name);
            apkSize = (TextView) view.findViewById(R.id.media_size);
        }
    }
}
