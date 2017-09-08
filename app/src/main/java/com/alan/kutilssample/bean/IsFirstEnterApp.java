package com.alan.kutilssample.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * ================================================================
 * 创建时间：2017/7/31 上午11:53
 * 创建人：Alan
 * 文件描述：用户是否第一次进入app
 * 至尊宝：长夜漫漫无心睡眠，我以为只有我睡不着，原来晶晶姑娘你也睡不着 ！
 * ================================================================
 */
@Entity
public class IsFirstEnterApp {
    @Id
    private Long Id;
    private boolean isFirstEnterApp;
    @Generated(hash = 1032443511)
    public IsFirstEnterApp(Long Id, boolean isFirstEnterApp) {
        this.Id = Id;
        this.isFirstEnterApp = isFirstEnterApp;
    }
    @Generated(hash = 682842891)
    public IsFirstEnterApp() {
    }
    public Long getId() {
        return this.Id;
    }
    public void setId(Long Id) {
        this.Id = Id;
    }
    public boolean getIsFirstEnterApp() {
        return this.isFirstEnterApp;
    }
    public void setIsFirstEnterApp(boolean isFirstEnterApp) {
        this.isFirstEnterApp = isFirstEnterApp;
    }
}
