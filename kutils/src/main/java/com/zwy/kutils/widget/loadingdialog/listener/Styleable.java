package com.zwy.kutils.widget.loadingdialog.listener;

import android.app.Dialog;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;

import com.zwy.kutils.widget.loadingdialog.bean.BuildBean;

import java.util.Map;


/**
 * Created by Administrator on 2016/10/10.
 */
public interface Styleable {

    BuildBean setBtnColor(@ColorRes int btn1Color, @ColorRes int btn2Color, @ColorRes int btn3Color);

    BuildBean setListItemColor(@ColorRes int lvItemTxtColor, Map<Integer, Integer> colorOfPosition);

    BuildBean setTitleColor(@ColorRes int colorRes);
    BuildBean setMsgColor(@ColorRes int colorRes);
    BuildBean seInputColor(@ColorRes int colorRes);



    BuildBean setTitleSize(int sizeInSp);
    BuildBean setMsgSize(int sizeInSp);
    BuildBean setBtnSize(int sizeInSp);
    BuildBean setLvItemSize(int sizeInSp);
    BuildBean setInputSize(int sizeInSp);

    Dialog show();

    //内容设置
    BuildBean setBtnText(CharSequence btn1Text, @Nullable CharSequence btn2Text, @Nullable CharSequence btn3Text);

    BuildBean setBtnText(CharSequence positiveTxt, @Nullable CharSequence negtiveText);

    BuildBean setListener(DialogUIListener listener);



    BuildBean setCancelable(boolean cancelable, boolean outsideCancelable);







}
