package com.zwy.kutils.widget.loadingdialog.holder;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zwy.kutils.R;
import com.zwy.kutils.widget.loadingdialog.bean.TieBean;
import com.zwy.kutils.widget.loadingdialog.listener.OnItemClickListener;


public class TieItemHolder extends SuperItemHolder<TieBean> {


    LinearLayout llBg;
    TextView tvTitle;

    public TieItemHolder(Context mContext, OnItemClickListener listener, View itemView) {
        super(mContext, listener, itemView);
        llBg = (LinearLayout) itemView.findViewById(R.id.ll_bg);
        tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
    }

    @Override
    public void refreshView() {
        TieBean data = getData();
        llBg.setSelected(data.isSelect());
        tvTitle.setText("" + data.getTitle());
    }
}
