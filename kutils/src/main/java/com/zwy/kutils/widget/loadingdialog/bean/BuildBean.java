package com.zwy.kutils.widget.loadingdialog.bean;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.zwy.kutils.widget.loadingdialog.adapter.SuperAdapter;
import com.zwy.kutils.widget.loadingdialog.config.CommonConfig;
import com.zwy.kutils.widget.loadingdialog.listener.Buildable;
import com.zwy.kutils.widget.loadingdialog.listener.DialogUIDateTimeSaveListener;
import com.zwy.kutils.widget.loadingdialog.listener.DialogUIItemListener;
import com.zwy.kutils.widget.loadingdialog.listener.DialogUIListener;
import com.zwy.kutils.widget.loadingdialog.listener.Styleable;
import com.zwy.kutils.widget.loadingdialog.utils.ToolUtils;

import java.util.List;
import java.util.Map;


public class BuildBean extends Buildable implements Styleable {


    /**
     * 上下文
     */
    public Context mContext;
    /**
     * 构建dialog的类型
     */
    public int type;
    public boolean isVertical;

    public View customView;

    public int gravity;
    public int dateType;
    public long date;
    public String dateTitle;
    public int tag;

    public CharSequence title;
    public CharSequence msg;
    public CharSequence text1 = CommonConfig.dialogui_btnTxt1;
    public CharSequence text2 = CommonConfig.dialogui_btnTxt2;
    public CharSequence text3;
    public CharSequence bottomTxt = CommonConfig.dialogui_bottomTxt;

    public CharSequence hint1;
    public CharSequence hint2;


    public DialogUIListener listener;
    public DialogUIDateTimeSaveListener dateTimeListener;
    public DialogUIItemListener itemListener;

    /**
     * 是否是白色背景
     */
    public boolean isWhiteBg = true;
    /**
     * 是否可以取消
     */
    public boolean cancelable = true;
    /**
     * 面板外是否可以点击
     */
    public boolean outsideTouchable = true;

    public Dialog dialog;
    public AlertDialog alertDialog;


    public int viewHeight;


    //各类对话框特有的参数
    public CharSequence[] wordsMd;
    public int defaultChosen;//
    public boolean[] checkedItems;

    //bottomsheet
    public SuperAdapter mAdapter;
    public List<TieBean> mLists;
    public int gridColumns = 4;


    //样式

    //三个以下按钮,颜色按此顺序
    @ColorRes
    public int btn1Color = CommonConfig.iosBtnColor;
    @ColorRes
    public int btn2Color = CommonConfig.iosBtnColor;
    @ColorRes
    public int btn3Color = CommonConfig.iosBtnColor;


    @ColorRes
    public int titleTxtColor = CommonConfig.titleTxtColor;
    @ColorRes
    public int msgTxtColor = CommonConfig.msgTxtColor;
    @ColorRes
    public int lvItemTxtColor = CommonConfig.lvItemTxtColor;
    @ColorRes
    public int inputTxtColor = CommonConfig.inputTxtColor;

    public Map<Integer, Integer> colorOfPosition;//listview 的item的特殊颜色:ColorRes

    //字体大小
    public int btnTxtSize = 17;// in sp
    public int titleTxtSize = 14;
    public int msgTxtSize = 14;
    public int itemTxtSize = 14;
    public int inputTxtSize = 14;


    @Override
    public BuildBean setBtnColor(@ColorRes int btn1Color, @ColorRes int btn2Color, @ColorRes int btn3Color) {
        if (btn1Color > 0)
            this.btn1Color = btn1Color;
        if (btn2Color > 0)
            this.btn2Color = btn2Color;
        if (btn3Color > 0)
            this.btn3Color = btn3Color;
        return this;
    }

    @Override
    public BuildBean setListItemColor(@ColorRes int lvItemTxtColor, Map<Integer, Integer> colorOfPosition) {
        if (lvItemTxtColor > 0)
            this.lvItemTxtColor = lvItemTxtColor;
        if (colorOfPosition != null && colorOfPosition.size() > 0) {
            this.colorOfPosition = colorOfPosition;
        }
        return this;
    }

    @Override
    public BuildBean setTitleColor(@ColorRes int colorRes) {
        if (colorRes > 0) {
            this.titleTxtColor = colorRes;
        }
        return this;
    }

    @Override
    public BuildBean setMsgColor(@ColorRes int colorRes) {
        if (colorRes > 0) {
            this.msgTxtColor = colorRes;
        }
        return this;
    }

    @Override
    public BuildBean seInputColor(@ColorRes int colorRes) {
        if (colorRes > 0) {
            this.inputTxtColor = colorRes;
        }
        return this;
    }

    @Override
    public BuildBean setTitleSize(int sizeInSp) {
        if (sizeInSp > 0 && sizeInSp < 30) {
            this.titleTxtSize = sizeInSp;
        }
        return this;
    }

    @Override
    public BuildBean setMsgSize(int sizeInSp) {
        if (sizeInSp > 0 && sizeInSp < 30) {
            this.msgTxtSize = sizeInSp;
        }
        return this;
    }

    @Override
    public BuildBean setBtnSize(int sizeInSp) {
        if (sizeInSp > 0 && sizeInSp < 30) {
            this.btnTxtSize = sizeInSp;
        }
        return this;
    }

    @Override
    public BuildBean setLvItemSize(int sizeInSp) {
        if (sizeInSp > 0 && sizeInSp < 30) {
            this.itemTxtSize = sizeInSp;
        }
        return this;
    }

    @Override
    public BuildBean setInputSize(int sizeInSp) {
        if (sizeInSp > 0 && sizeInSp < 30) {
            this.inputTxtSize = sizeInSp;
        }
        return this;
    }

    @Override
    public Dialog show() {
        buildByType(this);
        if (dialog != null && !dialog.isShowing()) {
            ToolUtils.showDialog(dialog);
            return dialog;
        } else if (alertDialog != null && !alertDialog.isShowing()) {
            ToolUtils.showDialog(alertDialog);
            return alertDialog;
        }
        return null;
    }

    @Override
    public BuildBean setBtnText(CharSequence btn1Text, @Nullable CharSequence btn2Text, @Nullable CharSequence btn3Text) {
        this.text1 = btn1Text;
        this.text2 = btn2Text;
        this.text3 = btn3Text;

        return this;
    }

    @Override
    public BuildBean setBtnText(CharSequence positiveTxt, @Nullable CharSequence negtiveText) {
        return setBtnText(positiveTxt, negtiveText, "");
    }

    @Override
    public BuildBean setListener(DialogUIListener listener) {
        if (listener != null) {
            this.listener = listener;
        }
        return this;
    }

    @Override
    public BuildBean setCancelable(boolean cancelable, boolean outsideCancelable) {
        this.cancelable = cancelable;
        this.outsideTouchable = outsideCancelable;
        return this;
    }


}
