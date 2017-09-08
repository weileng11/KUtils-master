package com.zwy.kutils.widget.loadingdialog.bean;

public class TieBean {

    private int id;
    private String title;
    private boolean isSelect;

    public TieBean(String title) {
        this.title = title;
    }

    public TieBean(String title, int id) {
        this.title = title;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
