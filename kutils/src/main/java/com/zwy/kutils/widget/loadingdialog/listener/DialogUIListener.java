package com.zwy.kutils.widget.loadingdialog.listener;

/**
 * Created by Administrator on 2016/5/4 0004.
 */
public abstract class DialogUIListener {

    /**
     * 确定
     */
    public abstract void onPositive();

    /**
     * 否定
     */
    public abstract void onNegative();

    /**
     * 中立
     */
    public void onNeutral() {
    }

    /**
     * 取消
     */
    public void onCancle() {
    }

    /**
     * 获取输入内容
     */
    public void onGetInput(CharSequence input1, CharSequence input2) {
    }

    /**
     * 获取选择集合
     */
    public void onGetChoose(boolean[] states) {

    }


}
