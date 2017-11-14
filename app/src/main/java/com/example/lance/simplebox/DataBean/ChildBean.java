package com.example.lance.simplebox.DataBean;

import java.util.List;

/**
 * Created by xiyu0 on 2017/11/6.
 */

public class ChildBean {
    private List<String> fileUri;
    private List<String> fileName;
    private List<Boolean> select;

    public void setFileName(List<String> fileName) {
        this.fileName = fileName;
    }

    public List<String> getFileName() {
        return fileName;
    }

    public List<String> getFileUri() {
        return fileUri;
    }

    public void setFileUri(List<String> fileUri) {
        this.fileUri = fileUri;
    }

    public void setSelect(List<Boolean> select) {
        this.select = select;
    }

    public List<Boolean> getSelect() {
        return select;
    }

    public  void setSelect(int position ,Boolean boolBean){
        select.set(position,boolBean);
    }

}
