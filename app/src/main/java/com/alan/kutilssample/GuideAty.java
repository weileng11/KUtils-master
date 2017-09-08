package com.alan.kutilssample;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;

import com.alan.kutilssample.bean.IsFirstEnterApp;
import com.alan.kutilssample.greendao.IsFirstEnterAppDao;
import com.zwy.kutils.widget.guide.BGABanner;
import com.zwy.kutils.widget.loadingdialog.DialogUIUtils;
import com.zwy.kutils.widget.loadingdialog.alertdialog.ActionSheetDialog;

import java.util.List;
import java.util.Random;

import butterknife.Bind;

/**
 * ================================================================
 * 创建时间：2017/7/27 下午2:49
 * 创建人：Alan
 * 文件描述：引导页面
 * 至尊宝：长夜漫漫无心睡眠，我以为只有我睡不着，原来晶晶姑娘你也睡不着 ！
 * ================================================================
 */
public class GuideAty extends MyBaseActivity {
    @Bind(R.id.banner_guide_background)
    BGABanner mBannerGuideBackground;
    @Bind(R.id.banner_guide_foreground)
    BGABanner mBannerGuideForeground;
    private Dialog dialog;


    /**
     * 是否需要沉浸式状态栏 不需要时返回0 需要时返回颜色
     *
     * @return StatusBarTintModle(boolean isTranslucentStatus, int color);
     */
    @Override
    protected int isTranslucentStatus() {
        return R.color.trans;
    }

    /**
     * 是否需要注册eventBus
     *
     * @return 需要时返回true 页面销毁时会自动注销 子类无需重复注销
     */
    @Override
    protected boolean isNeedEventBus() {
        return false;
    }

    /**
     * 设置布局ID
     *
     * @return 资源文件ID
     */
    @Override
    protected int getLayoutId() {

        List<IsFirstEnterApp> isFirstEnterApps = App.getDaoSession().getIsFirstEnterAppDao().queryBuilder().where(IsFirstEnterAppDao.Properties.IsFirstEnterApp.eq(false)).list();
        if (isFirstEnterApps != null && isFirstEnterApps.size() > 0) {
            readyGo(MainActivity.class);
            finish();
        }
        return R.layout.guideaty;
    }

    /**
     * 初始化View
     *
     * @param savedInstanceState aty销毁时保存的临时参数
     */
    @Override
    protected void initView(Bundle savedInstanceState) {
        initListener();
    }

    /**
     * 初始化数据源
     */
    @Override
    protected void initData() {
        setData();
    }

    /**
     * Bundle  传递数据
     *
     * @param extras
     */
    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    private void initListener() {
        //将跳过按钮与进入按钮加入控制器设置点击事件
        mBannerGuideForeground.setEnterSkipViewIdAndDelegate(R.id.btn_guide_enter, R.id.tv_guide_skip, new BGABanner.GuideDelegate() {
            @Override
            public void onClickEnterOrSkip() {
                dialog = DialogUIUtils.showLoadingHorizontal(mContext, "请稍后……", true, false, true).show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (dialog.isShowing()) {
                                        dialog.dismiss();
                                        showDialog(new Random().nextBoolean());
                                    }
                                }
                            });
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }

    private void showDialog(boolean b) {
        if (b) {
            DialogUIUtils.showTwoButtonAlertDialog(mContext, "提示", "是否进入主页", "取消", new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            }, "进入", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    readyGoThenKill(MainActivity.class);
                }
            }, false);
        } else {
            new ActionSheetDialog(mContext)
                    .builder()
                    .setCancelable(false)
                    .setCanceledOnTouchOutside(false)
                    .setTitle("提示")
                    .addSheetItem("删除", ActionSheetDialog.SheetItemColor.Red, new ActionSheetDialog.OnSheetItemClickListener() {
                        @Override
                        public void onClick(int which) {

                        }
                    })
                    .addSheetItem("进入", ActionSheetDialog.SheetItemColor.Blue, new ActionSheetDialog.OnSheetItemClickListener() {
                        @Override
                        public void onClick(int which) {
                            readyGoThenKill(MainActivity.class);
                        }
                    }).show();
        }
    }

    private void setData() {
        //开启自动播放
//        mBannerGuideBackground.setAutoPlayAble(true);
//        mBannerGuideForeground.setAutoPlayAble(true);

        //设置背景图
        mBannerGuideBackground.setData(R.mipmap.uoko_guide_background_1, R.mipmap.uoko_guide_background_2, R.mipmap.uoko_guide_background_3);
        //设置背景图对应的文案
        mBannerGuideForeground.setData(R.mipmap.uoko_guide_foreground_1, R.mipmap.uoko_guide_foreground_2, R.mipmap.uoko_guide_foreground_3);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 如果开发者的引导页主题是透明的，需要在界面可见时给背景 Banner 设置一个白色背景，避免滑动过程中两个 Banner 都设置透明度后能看到 Launcher
        mBannerGuideBackground.setBackgroundResource(android.R.color.white);
    }

    /**
     * 自己进行相关逻辑拓展
     */
    @Override
    protected void test() {

    }
}
