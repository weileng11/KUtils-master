package com.zwy.kutils.widget.loadingdialog.widget;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.zwy.kutils.R;
import com.zwy.kutils.widget.loadingdialog.adapter.PopuWindowAdapter;
import com.zwy.kutils.widget.loadingdialog.bean.PopuBean;
import com.zwy.kutils.widget.loadingdialog.listener.TdataListener;

import java.util.ArrayList;
import java.util.List;


public class PopuWindowView implements AdapterView.OnItemClickListener {

    View viewItem = null;
    ListView pupoListView;
    PopupWindow pullDownView;// 弹出窗口
    private List<PopuBean> popuLists = new ArrayList<PopuBean>();
    private PopuWindowAdapter mPopuWindowAdapter;
    private Context mContext;
    private TdataListener mTdataListener;
    private int maxLine = 5;

    public PopuWindowView(Context mContext, int widthGravity) {
        this.mContext = mContext;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        viewItem = inflater.inflate(R.layout.dialogui_popu_options, null);
        pupoListView = (ListView) viewItem.findViewById(R.id.customui_list);
        mPopuWindowAdapter = new PopuWindowAdapter(mContext, popuLists);
        pupoListView.setAdapter(mPopuWindowAdapter);
        pullDownView = new PopupWindow(viewItem, widthGravity,
                LayoutParams.WRAP_CONTENT, true);
        pullDownView.setOutsideTouchable(true);
        pullDownView.setBackgroundDrawable(new BitmapDrawable());
        pupoListView.setOnItemClickListener(this);
    }

    /**
     * 设置下拉框的数据
     */
    public void initPupoData(TdataListener tdataListener) {
        mTdataListener = tdataListener;
        if (mTdataListener != null) {
            mTdataListener.initPupoData(popuLists);
        }
        if (popuLists != null && popuLists.size() > maxLine) {
            pullDownView.setHeight(dip2px(maxLine * 40));
        }
        mPopuWindowAdapter.notifyDataSetChanged();
    }

    private int dip2px(int dip) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /**
     * 设置最大行popuWindow
     */
    public void setMaxLines(int maxLines) {
        maxLine = maxLines;
    }

    /**
     * 显示popuWindow
     */
    public void showing(View v) {
        pullDownView.showAsDropDown(v, 0, 0);
    }

    /**
     * 关闭popuWindow
     */
    public void dismiss() {
        pullDownView.dismiss();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        if (mTdataListener != null) {
            mTdataListener.onItemClick(adapterView, view, position);
        }
    }

    /**
     * 获取选择的名称
     */
    public String getTitle(int popuPosition) {
        return popuLists.get(popuPosition).getTitle();
    }

    /**
     * 获取选择的id
     */
    public int getId(int popuPosition) {
        return popuLists.get(popuPosition).getId();
    }

    /**
     * 获取选择的sid
     */
    public String getSid(int popuPosition) {
        return popuLists.get(popuPosition).getSid();
    }

}
