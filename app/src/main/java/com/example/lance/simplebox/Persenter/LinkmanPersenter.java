package com.example.lance.simplebox.Persenter;

import android.content.Context;

import com.example.lance.simplebox.MVPContract.LinkmanContract;

/**
 * Created by Lance on 2017/10/24.
 */

public class LinkmanPersenter implements LinkmanContract.LinkmanPersenter {

    private LinkmanContract.LinkmanMode mode;
    private LinkmanContract.LinkmanView view;
    private Context context;

    public LinkmanPersenter(LinkmanContract.LinkmanMode mode, LinkmanContract.LinkmanView view, Context context){
        this.mode = mode;
        this.view = view;
        this.context = context;
    }

    @Override
    public void getLinkman() {
        view.showLinkman(mode.doLickman(context));
    }
}
