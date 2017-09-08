package com.zwy.kutils.widget.loadingdialog.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;

import com.zwy.kutils.widget.loadingdialog.listener.OnItemClickListener;


/**
 * ========================================
 * <p>
 * 版 权：dou361.com 版权所有 （C） 2015
 * <p>
 * 作 者：陈冠明
 * <p>
 * 个人网站：http://www.dou361.com
 * <p>
 * 版 本：1.0
 * <p>
 * 创建日期：2016/10/5 17:57
 * <p>
 * 描 述：RecyclerView的ViewHolder的基类
 * <p>
 * <p>
 * 修订历史：
 * <p>
 * ========================================
 */
public abstract class SuperItemHolder<T> extends ViewHolder implements View.OnClickListener {

    /**
     * 上下文
     */
    protected Context mContext;
    /**
     * 加载得到的数据
     */
    public T mDatas;
    /**
     * 只有当该holder作为item使用，并且使用带参构造函数实例化position才有意义，使用无参构造函数则position没有意义
     */
    protected int position;
    private OnItemClickListener mlistener;

    public SuperItemHolder(Context mContext, OnItemClickListener listener, View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        this.mContext = mContext;
        this.mlistener = listener;
    }

    /**
     * 设置数据
     */
    public void setData(T data) {
        this.mDatas = data;
        refreshView();
    }

    /**
     * 刷新数据
     */
    public abstract void refreshView();

    /**
     * 获得数据
     */
    public T getData() {
        return mDatas;
    }

    /**
     * 当复用holder的时候，需要调用该方法来同步holder对应的索引位置
     */
    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public void onClick(View view) {
        if (mlistener != null) {
            mlistener.onItemClick(position);
        }
    }
}