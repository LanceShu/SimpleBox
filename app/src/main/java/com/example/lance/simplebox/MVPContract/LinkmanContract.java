package com.example.lance.simplebox.MVPContract;

import android.content.Context;

import com.example.lance.simplebox.DataBean.LinkmanBean;

import java.util.List;

/**
 * Created by Lance on 2017/10/24.
 */

public class LinkmanContract {

    public interface LinkmanMode{
        List<LinkmanBean> doLickman(Context context);
    }

    public interface LinkmanView{
        void showLinkman(List<LinkmanBean> linkmanBeanList);
    }

    public interface LinkmanPersenter{
        void getLinkman();
    }
}
