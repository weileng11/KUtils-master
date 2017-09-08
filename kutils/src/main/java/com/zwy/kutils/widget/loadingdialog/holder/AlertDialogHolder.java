package com.zwy.kutils.widget.loadingdialog.holder;


import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zwy.kutils.R;
import com.zwy.kutils.widget.loadingdialog.DialogUIUtils;
import com.zwy.kutils.widget.loadingdialog.bean.BuildBean;
import com.zwy.kutils.widget.loadingdialog.utils.ToolUtils;


public class AlertDialogHolder extends SuperHolder {
    protected TextView tvTitle;
    public TextView tvMsg;
    public EditText et1;
    public EditText et2;
    protected View line;
    protected Button btn1;
    protected View lineBtn2;
    protected Button btn2;
    protected View lineBtn3;
    protected Button btn3;
    protected LinearLayout llContainerHorizontal;
    protected Button btn1Vertical;
    protected View lineBtn2Vertical;
    protected Button btn2Vertical;
    protected View lineBtn3Vertical;
    protected Button btn3Vertical;
    protected LinearLayout llContainerVertical;

    public AlertDialogHolder(Context context) {
        super(context);
    }

    @Override
    protected void findViews() {
        tvTitle = (TextView) rootView.findViewById(R.id.dialogui_tv_title);
        tvMsg = (TextView) rootView.findViewById(R.id.dialogui_tv_msg);
        et1 = (EditText) rootView.findViewById(R.id.et_1);
        et2 = (EditText) rootView.findViewById(R.id.et_2);
        line = (View) rootView.findViewById(R.id.line);
        btn1 = (Button) rootView.findViewById(R.id.btn_1);
        lineBtn2 = (View) rootView.findViewById(R.id.line_btn2);
        btn2 = (Button) rootView.findViewById(R.id.btn_2);
        lineBtn3 = (View) rootView.findViewById(R.id.line_btn3);
        btn3 = (Button) rootView.findViewById(R.id.btn_3);
        llContainerHorizontal = (LinearLayout) rootView.findViewById(R.id.ll_container_horizontal);
        btn1Vertical = (Button) rootView.findViewById(R.id.btn_1_vertical);
        lineBtn2Vertical = (View) rootView.findViewById(R.id.line_btn2_vertical);
        btn2Vertical = (Button) rootView.findViewById(R.id.btn_2_vertical);
        lineBtn3Vertical = (View) rootView.findViewById(R.id.line_btn3_vertical);
        btn3Vertical = (Button) rootView.findViewById(R.id.btn_3_vertical);
        llContainerVertical = (LinearLayout) rootView.findViewById(R.id.ll_container_vertical);
    }


    @Override
    protected int setLayoutRes() {
        return R.layout.dialogui_holder_alert;
    }

    @Override
    public void assingDatasAndEvents(Context context, final BuildBean bean) {

        //style
        tvMsg.setTextColor(ToolUtils.getColor(tvMsg.getContext(), bean.msgTxtColor));
        tvMsg.setTextSize(bean.msgTxtSize);

        tvTitle.setTextColor(ToolUtils.getColor(tvTitle.getContext(), bean.titleTxtColor));
        tvTitle.setTextSize(bean.titleTxtSize);

        btn3Vertical.setTextSize(bean.btnTxtSize);
        btn2Vertical.setTextSize(bean.btnTxtSize);
        btn1Vertical.setTextSize(bean.btnTxtSize);
        btn3.setTextSize(bean.btnTxtSize);
        btn2.setTextSize(bean.btnTxtSize);
        btn1.setTextSize(bean.btnTxtSize);

        btn1.setTextColor(ToolUtils.getColor(btn1.getContext(), bean.btn1Color));
        btn2.setTextColor(ToolUtils.getColor(btn1.getContext(), bean.btn2Color));
        btn3.setTextColor(ToolUtils.getColor(btn1.getContext(), bean.btn3Color));

        btn1Vertical.setTextColor(ToolUtils.getColor(btn1.getContext(), bean.btn1Color));
        btn2Vertical.setTextColor(ToolUtils.getColor(btn1.getContext(), bean.btn2Color));
        btn3Vertical.setTextColor(ToolUtils.getColor(btn1.getContext(), bean.btn3Color));

        //隐藏view
        if (bean.isVertical) {
            llContainerVertical.setVisibility(View.VISIBLE);
            llContainerHorizontal.setVisibility(View.GONE);
        } else {
            llContainerVertical.setVisibility(View.GONE);
            llContainerHorizontal.setVisibility(View.VISIBLE);
        }

        if (TextUtils.isEmpty(bean.title)) {
            tvTitle.setVisibility(View.GONE);
        } else {
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(bean.title);
        }

        if (TextUtils.isEmpty(bean.msg)) {
            tvMsg.setVisibility(View.GONE);
        } else {
            tvMsg.setVisibility(View.VISIBLE);
            tvMsg.setText(bean.msg);

            tvMsg.setTextColor(ToolUtils.getColor(tvMsg.getContext(), bean.msgTxtColor));
            tvMsg.setTextSize(bean.msgTxtSize);
        }

        if (TextUtils.isEmpty(bean.hint1)) {
            et1.setVisibility(View.GONE);
        } else {
            et1.setVisibility(View.VISIBLE);
            et1.setHint(bean.hint1);

            et1.setTextColor(ToolUtils.getColor(et1.getContext(), bean.inputTxtColor));
            et1.setTextSize(bean.inputTxtSize);
        }

        if (TextUtils.isEmpty(bean.hint2)) {
            et2.setVisibility(View.GONE);
        } else {
            et2.setVisibility(View.VISIBLE);
            et2.setHint(bean.hint2);
            et2.setTextColor(ToolUtils.getColor(et2.getContext(), bean.inputTxtColor));
            et2.setTextSize(bean.inputTxtSize);
        }

        //按钮数量

        if (TextUtils.isEmpty(bean.text3)) {
            if (bean.isVertical) {
                btn3Vertical.setVisibility(View.GONE);
                lineBtn3Vertical.setVisibility(View.GONE);
                btn2Vertical.setBackgroundResource(R.drawable.dialogui_selector_btn_all_bottom);
            } else {
                btn3.setVisibility(View.GONE);
                lineBtn3.setVisibility(View.GONE);
                btn2.setBackgroundResource(R.drawable.dialogui_selector_btn_right_bottom);
            }

        } else {
            if (bean.isVertical) {
                btn3Vertical.setVisibility(View.VISIBLE);
                lineBtn3Vertical.setVisibility(View.VISIBLE);
                btn3Vertical.setText(bean.text3);


            } else {
                btn3.setVisibility(View.VISIBLE);
                lineBtn3.setVisibility(View.VISIBLE);
                btn3.setText(bean.text3);
            }
        }

        if (TextUtils.isEmpty(bean.text2)) {
            if (bean.isVertical) {
                btn2Vertical.setVisibility(View.GONE);
                lineBtn2Vertical.setVisibility(View.GONE);
                btn1Vertical.setBackgroundResource(R.drawable.dialogui_selector_btn_all_bottom);
            } else {
                btn2.setVisibility(View.GONE);
                lineBtn2.setVisibility(View.GONE);
                btn1.setBackgroundResource(R.drawable.dialogui_selector_btn_right_bottom);
            }
        } else {
            if (bean.isVertical) {
                btn2Vertical.setVisibility(View.VISIBLE);
                lineBtn2Vertical.setVisibility(View.VISIBLE);
                btn2Vertical.setText(bean.text2);
            } else {
                btn2.setVisibility(View.VISIBLE);
                lineBtn2.setVisibility(View.VISIBLE);
                btn2.setText(bean.text2);
            }
        }
        if (TextUtils.isEmpty(bean.text1)) {
            line.setVisibility(View.GONE);
            llContainerHorizontal.setVisibility(View.GONE);
            llContainerVertical.setVisibility(View.GONE);
        } else {
            if (bean.isVertical) {
                btn1Vertical.setText(bean.text1);
            } else {
                btn1.setText(bean.text1);
            }
        }


        //事件

        if (bean.isVertical) {
            btn1Vertical.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DialogUIUtils.dismiss(bean.dialog, bean.alertDialog);
                    bean.listener.onPositive();

                    bean.listener.onGetInput(et1.getText().toString().trim(), et2.getText().toString().trim());


                }
            });

            btn2Vertical.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DialogUIUtils.dismiss(bean.dialog, bean.alertDialog);
                    bean.listener.onNegative();
                }
            });

            btn3Vertical.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DialogUIUtils.dismiss(bean.dialog, bean.alertDialog);
                    bean.listener.onNeutral();
                }
            });


        } else {
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DialogUIUtils.dismiss(bean.dialog, bean.alertDialog);
                    bean.listener.onPositive();
                    bean.listener.onGetInput(et1.getText().toString().trim(), et2.getText().toString().trim());
                }
            });

            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DialogUIUtils.dismiss(bean.dialog, bean.alertDialog);
                    bean.listener.onNegative();
                }
            });

            btn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DialogUIUtils.dismiss(bean.dialog, bean.alertDialog);
                    bean.listener.onNeutral();
                }
            });

        }


    }


}
