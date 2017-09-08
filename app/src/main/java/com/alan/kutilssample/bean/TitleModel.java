package com.alan.kutilssample.bean;

import java.io.Serializable;

/**
 * ================================================================
 * 创建时间：2017/7/31 上午9:17
 * 创建人：Alan
 * 文件描述：首页功能标题实体，填充适配器
 * 至尊宝：长夜漫漫无心睡眠，我以为只有我睡不着，原来晶晶姑娘你也睡不着 ！
 * ================================================================
 */
public class TitleModel implements Serializable {
    private String title;//标题
    private boolean isCanClick;

    public TitleModel(String title, boolean index) {
        this.title = title;
        this.isCanClick = index;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean getIndex() {
        return isCanClick;
    }

    public void setIndex(boolean index) {
        this.isCanClick = index;
    }
}
