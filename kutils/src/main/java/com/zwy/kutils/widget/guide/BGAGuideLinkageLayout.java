package com.zwy.kutils.widget.guide;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * ================================================================
 * 创建时间：2017/7/27 下午2:46
 * 创建人：Alan
 * 文件描述：:引导界面联动布局，将每一个触摸事件分发给所有的子控件。
 * 至尊宝：长夜漫漫无心睡眠，我以为只有我睡不着，原来晶晶姑娘你也睡不着 ！
 * ================================================================
 */
public class BGAGuideLinkageLayout extends FrameLayout {
    public BGAGuideLinkageLayout(Context context) {
        super(context);
    }

    public BGAGuideLinkageLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BGAGuideLinkageLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            try {
                child.dispatchTouchEvent(ev);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
