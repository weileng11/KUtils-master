package com.zwy.kutils.widget.loadingdialog.bean;

import java.io.Serializable;

public class PopuBean implements Serializable {

    /**
     * 序列化id
     */
    private static final long serialVersionUID = -6482229637703795368L;
    /**
     * 下拉条目id
     */
    int id;
    /**
     * 下拉条目sid
     */
    String sid;
    /**
     * 下拉条目标题
     */
    String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
