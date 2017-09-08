package com.zwy.kutils.widget.loadingdialog.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.zwy.kutils.R;
import com.zwy.kutils.widget.loadingdialog.bean.TieBean;
import com.zwy.kutils.widget.loadingdialog.holder.SuperItemHolder;
import com.zwy.kutils.widget.loadingdialog.holder.TieItemHolder;

import java.util.List;


public class TieAdapter extends SuperAdapter<TieBean> {

    public TieAdapter(Context mContext, List<TieBean> list) {
        super(mContext, list);
    }

    @Override
    public SuperItemHolder getItemHolder(ViewGroup parent, int viewType) {
        return new TieItemHolder(mContext, mListener, LayoutInflater.from(mContext).
                inflate(R.layout.dialogui_holder_item_tie, parent, false));
    }
}
