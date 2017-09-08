package com.zwy.kutils.widget.loadingdialog.listener;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zwy.kutils.R;
import com.zwy.kutils.widget.loadingdialog.DialogUIUtils;
import com.zwy.kutils.widget.loadingdialog.bean.BuildBean;
import com.zwy.kutils.widget.loadingdialog.config.CommonConfig;
import com.zwy.kutils.widget.loadingdialog.holder.AlertDialogHolder;
import com.zwy.kutils.widget.loadingdialog.holder.SheetHolder;
import com.zwy.kutils.widget.loadingdialog.utils.ToolUtils;
import com.zwy.kutils.widget.loadingdialog.widget.DateSelectorWheelView;


public class Buildable {

    protected static int singleChosen;

    protected BuildBean buildByType(BuildBean bean) {
        ToolUtils.fixContext(bean);
        switch (bean.type) {
            case CommonConfig.TYPE_DATEPICK:
                buildDatePick(bean);
                break;
            case CommonConfig.TYPE_LOADING_HORIZONTAL:
                buildLoadingHorizontal(bean);
                break;
            case CommonConfig.TYPE_LOADING_VERTICAL:
                buildLoadingVertical(bean);
                break;
            case CommonConfig.TYPE_MD_LOADING_HORIZONTAL:
                buildMdLoadingHorizontal(bean);
                break;
            case CommonConfig.TYPE_MD_LOADING_VERTICAL:
                buildMdLoadingVertical(bean);
                break;
            case CommonConfig.TYPE_MD_ALERT:
                buildMdAlert(bean);
                break;
            case CommonConfig.TYPE_SINGLE_CHOOSE:
                buildSingleChoose(bean);
                break;
            case CommonConfig.TYPE_MD_MULTI_CHOOSE:
                buildMdMultiChoose(bean);
                break;
            case CommonConfig.TYPE_ALERT:
                buildAlert(bean);
                break;
            case CommonConfig.TYPE_CENTER_SHEET:
                buildCenterSheet(bean);
                break;
            case CommonConfig.TYPE_CUSTOM_ALERT:
                buildCustomAlert(bean);
                break;
            case CommonConfig.TYPE_CUSTOM_BOTTOM_ALERT:
                buildCustomBottomAlert(bean);
                break;
            case CommonConfig.TYPE_BOTTOM_SHEET:
                buildBottomSheet(bean);
                break;
            default:
                break;


        }
        ToolUtils.setDialogStyle(bean);
        ToolUtils.setCancelable(bean);
        return bean;
    }

    private BuildBean buildDatePick(final BuildBean bean) {
        AlertDialog.Builder builder = new AlertDialog.Builder(bean.mContext);
        View root = View.inflate(bean.mContext, R.layout.dialogui_datepick_layout, null);

        RelativeLayout rl_title_panel = (RelativeLayout) root
                .findViewById(R.id.rl_title_panel);
        FrameLayout flFirst = (FrameLayout) root
                .findViewById(R.id.fl_first);
        FrameLayout flNext = (FrameLayout) root
                .findViewById(R.id.fl_next);
        TextView tv_title = (TextView) root
                .findViewById(R.id.tv_title);
        TextView tv_first = (TextView) root
                .findViewById(R.id.tv_first);
        TextView tv_next = (TextView) root
                .findViewById(R.id.tv_next);
        FrameLayout fl_top_customPanel = (FrameLayout) root
                .findViewById(R.id.fl_top_customPanel);
        final DateSelectorWheelView dwvDate = (DateSelectorWheelView) root
                .findViewById(R.id.dwv_date);
        FrameLayout fl_bottom_customPanel = (FrameLayout) root
                .findViewById(R.id.fl_bottom_customPanel);
        dwvDate.setShowDate(bean.date);
        dwvDate.setShowDateType(bean.dateType);
        dwvDate.setTitleClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                if (id == R.id.rl_date_time_title) {
                    if (dwvDate.getDateSelectorVisibility() == View.VISIBLE) {
                        dwvDate.setDateSelectorVisiblility(View.GONE);
                    } else {
                        dwvDate.setDateSelectorVisiblility(View.VISIBLE);
                    }
                }
            }
        });

        builder.setView(root);
        final AlertDialog dialog = builder.create();
        bean.alertDialog = dialog;
        if (bean.gravity == Gravity.BOTTOM) {
            Window window = dialog.getWindow();
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        flFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        flNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != bean.dateTimeListener) {
                    bean.dateTimeListener.onSaveSelectedDate(bean.tag, dwvDate.getSelectedDate());
                }
                dialog.dismiss();
            }
        });
        return bean;
    }


    protected BuildBean buildMdLoadingVertical(BuildBean bean) {
        AlertDialog.Builder builder = new AlertDialog.Builder(bean.mContext);
        View root = View.inflate(bean.mContext, R.layout.dialogui_loading_vertical, null);
        View llBg = (View) root.findViewById(R.id.dialogui_ll_bg);
        ProgressBar pbBg = (ProgressBar) root.findViewById(R.id.pb_bg);
        TextView tvMsg = (TextView) root.findViewById(R.id.dialogui_tv_msg);
        tvMsg.setText(bean.msg);
        if (bean.isWhiteBg) {
            llBg.setBackgroundResource(R.drawable.dialogui_shape_wihte_round_corner);
            pbBg.setIndeterminateDrawable(bean.mContext.getResources().getDrawable(R.drawable.dialogui_rotate_mum));
            tvMsg.setTextColor(bean.mContext.getResources().getColor(R.color.text_black));
        } else {
            llBg.setBackgroundResource(R.drawable.dialogui_shape_gray_round_corner);
            pbBg.setIndeterminateDrawable(bean.mContext.getResources().getDrawable(R.drawable.dialogui_rotate_mum_light));
            tvMsg.setTextColor(Color.WHITE);
        }
        builder.setView(root);
        AlertDialog dialog = builder.create();
        bean.alertDialog = dialog;
        return bean;
    }


    protected BuildBean buildMdLoadingHorizontal(BuildBean bean) {
        AlertDialog.Builder builder = new AlertDialog.Builder(bean.mContext);
        View root = View.inflate(bean.mContext, R.layout.dialogui_loading_horizontal, null);
        View llBg = (View) root.findViewById(R.id.dialogui_ll_bg);
        ProgressBar pbBg = (ProgressBar) root.findViewById(R.id.pb_bg);
        TextView tvMsg = (TextView) root.findViewById(R.id.dialogui_tv_msg);
        tvMsg.setText(bean.msg);
        if (bean.isWhiteBg) {
            llBg.setBackgroundResource(R.drawable.dialogui_shape_wihte_round_corner);
            pbBg.setIndeterminateDrawable(bean.mContext.getResources().getDrawable(R.drawable.dialogui_shape_progress));
            tvMsg.setTextColor(bean.mContext.getResources().getColor(R.color.text_black));
        } else {
            llBg.setBackgroundResource(R.drawable.dialogui_shape_gray_round_corner);
            pbBg.setIndeterminateDrawable(bean.mContext.getResources().getDrawable(R.drawable.dialogui_shape_progress_light));
            tvMsg.setTextColor(Color.WHITE);
        }
        builder.setView(root);
        AlertDialog dialog = builder.create();
        bean.alertDialog = dialog;
        return bean;
    }

    protected BuildBean buildLoadingVertical(BuildBean bean) {
        Dialog dialog = new Dialog(bean.mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        bean.dialog = dialog;

        View root = View.inflate(bean.mContext, R.layout.dialogui_loading_vertical, null);
        View llBg = (View) root.findViewById(R.id.dialogui_ll_bg);
        ProgressBar pbBg = (ProgressBar) root.findViewById(R.id.pb_bg);
        TextView tvMsg = (TextView) root.findViewById(R.id.dialogui_tv_msg);
        tvMsg.setText(bean.msg);
        if (bean.isWhiteBg) {
            llBg.setBackgroundResource(R.drawable.dialogui_shape_wihte_round_corner);
            pbBg.setIndeterminateDrawable(bean.mContext.getResources().getDrawable(R.drawable.dialogui_rotate_mum));
            tvMsg.setTextColor(bean.mContext.getResources().getColor(R.color.text_black));
        } else {
            llBg.setBackgroundResource(R.drawable.dialogui_shape_gray_round_corner);
            pbBg.setIndeterminateDrawable(bean.mContext.getResources().getDrawable(R.drawable.dialogui_rotate_mum_light));
            tvMsg.setTextColor(Color.WHITE);
        }
        bean.dialog.setContentView(root);
        return bean;
    }

    protected BuildBean buildLoadingHorizontal(BuildBean bean) {
        Dialog dialog = new Dialog(bean.mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        bean.dialog = dialog;

        View root = View.inflate(bean.mContext, R.layout.dialogui_loading_horizontal, null);
        View llBg = (View) root.findViewById(R.id.dialogui_ll_bg);
        ProgressBar pbBg = (ProgressBar) root.findViewById(R.id.pb_bg);
        TextView tvMsg = (TextView) root.findViewById(R.id.dialogui_tv_msg);
        tvMsg.setText(bean.msg);
        if (bean.isWhiteBg) {
            llBg.setBackgroundResource(R.drawable.dialogui_shape_wihte_round_corner);
            pbBg.setIndeterminateDrawable(bean.mContext.getResources().getDrawable(R.drawable.dialogui_shape_progress));
            tvMsg.setTextColor(bean.mContext.getResources().getColor(R.color.text_black));
        } else {
            llBg.setBackgroundResource(R.drawable.dialogui_shape_gray_round_corner);
            pbBg.setIndeterminateDrawable(bean.mContext.getResources().getDrawable(R.drawable.dialogui_shape_progress_light));
            tvMsg.setTextColor(Color.WHITE);
        }
        bean.dialog.setContentView(root);
        return bean;
    }

    protected BuildBean buildMdAlert(final BuildBean bean) {
        AlertDialog.Builder builder = new AlertDialog.Builder(bean.mContext);
        builder.setTitle(bean.title)
                .setMessage(bean.msg)
                .setPositiveButton(bean.text1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        bean.listener.onPositive();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(bean.text2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        bean.listener.onNegative();
                        dialog.dismiss();
                    }
                }).setNeutralButton(bean.text3, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                bean.listener.onNeutral();
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                bean.listener.onCancle();
            }
        });
        bean.alertDialog = dialog;
        return bean;
    }

    protected BuildBean buildSingleChoose(final BuildBean bean) {
        AlertDialog.Builder builder = new AlertDialog.Builder(bean.mContext);
        singleChosen = bean.defaultChosen;
        builder.setTitle(bean.title)
                .setSingleChoiceItems(bean.wordsMd, bean.defaultChosen, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        singleChosen = i;
                        if (bean.itemListener != null) {
                            bean.itemListener.onItemClick(bean.wordsMd[i], i);
                        }

                        if (bean.listener == null) {
                            DialogUIUtils.dismiss(dialogInterface);
                        }

                    }
                });

        AlertDialog dialog = builder.create();
        bean.alertDialog = dialog;
        return bean;
    }

    protected BuildBean buildMdMultiChoose(final BuildBean bean) {
        AlertDialog.Builder builder = new AlertDialog.Builder(bean.mContext);
        builder.setTitle(bean.title)
                .setCancelable(true)
                .setPositiveButton(bean.text1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (bean.listener != null) {
                            DialogUIUtils.dismiss(dialogInterface);
                            bean.listener.onPositive();
                            bean.listener.onGetChoose(bean.checkedItems);
                        }
                    }
                })
                .setNegativeButton(bean.text2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (bean.listener != null) {
                            DialogUIUtils.dismiss(dialogInterface);
                            bean.listener.onNegative();
                        }
                    }
                })
                .setMultiChoiceItems(bean.wordsMd, bean.checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {

                    }
                })
        ;

        AlertDialog dialog = builder.create();
        bean.alertDialog = dialog;
        return bean;
    }

    protected BuildBean buildAlert(BuildBean bean) {
        AlertDialog.Builder builder = new AlertDialog.Builder(bean.mContext);
        AlertDialogHolder holder = new AlertDialogHolder(bean.mContext);
        builder.setView(holder.rootView);
        AlertDialog dialog = builder.create();
        bean.alertDialog = dialog;
        holder.assingDatasAndEvents(bean.mContext, bean);
        return bean;
    }

    private void buildCustomAlert(BuildBean bean) {
        AlertDialog.Builder builder = new AlertDialog.Builder(bean.mContext);
        builder.setView(bean.customView);
        AlertDialog dialog = builder.create();
        bean.alertDialog = dialog;
        dialog.getWindow().getDecorView().setPadding(0, 0, 0, 0);
    }

    private void buildCustomBottomAlert(BuildBean bean) {
        BottomSheetDialog dialog = new BottomSheetDialog(bean.mContext);
        dialog.setContentView(bean.customView);
        bean.dialog = dialog;
    }

    protected BuildBean buildCenterSheet(BuildBean bean) {
        AlertDialog.Builder builder = new AlertDialog.Builder(bean.mContext);
        SheetHolder holder = new SheetHolder(bean.mContext);
        builder.setView(holder.rootView);
        AlertDialog dialog = builder.create();
        bean.alertDialog = dialog;
        if (bean.isVertical && !TextUtils.isEmpty(bean.bottomTxt)) {
            Window window = dialog.getWindow();
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        holder.assingDatasAndEvents(bean.mContext, bean);
        return bean;
    }

    private void buildBottomSheet(BuildBean bean) {
        BottomSheetDialog dialog = new BottomSheetDialog(bean.mContext);
        SheetHolder sheetHolder = new SheetHolder(bean.mContext);
        dialog.setContentView(sheetHolder.rootView);
        sheetHolder.assingDatasAndEvents(bean.mContext, bean);
        bean.dialog = dialog;
    }


}
