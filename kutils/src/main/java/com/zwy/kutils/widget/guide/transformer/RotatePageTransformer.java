package com.zwy.kutils.widget.guide.transformer;

import android.support.v4.view.ViewCompat;
import android.view.View;

/**
 * ================================================================
 * 创建时间：2017/7/27 下午2:44
 * 创建人：Alan
 * 文件描述：
 * 至尊宝：长夜漫漫无心睡眠，我以为只有我睡不着，原来晶晶姑娘你也睡不着 ！
 * ================================================================
 */
public class RotatePageTransformer extends BGAPageTransformer {
    private float mMaxRotation = 15.0f;

    public RotatePageTransformer() {
    }

    public RotatePageTransformer(float maxRotation) {
        setMaxRotation(maxRotation);
    }

    @Override
    public void handleInvisiblePage(View view, float position) {
        ViewCompat.setPivotX(view, view.getMeasuredWidth() * 0.5f);
        ViewCompat.setPivotY(view, view.getMeasuredHeight());
        ViewCompat.setRotation(view, 0);
    }

    @Override
    public void handleLeftPage(View view, float position) {
        float rotation = (mMaxRotation * position);
        ViewCompat.setPivotX(view, view.getMeasuredWidth() * 0.5f);
        ViewCompat.setPivotY(view, view.getMeasuredHeight());
        ViewCompat.setRotation(view, rotation);
    }

    @Override
    public void handleRightPage(View view, float position) {
        handleLeftPage(view, position);
    }

    public void setMaxRotation(float maxRotation) {
        if (maxRotation >= 0.0f && maxRotation <= 40.0f) {
            mMaxRotation = maxRotation;
        }
    }
}