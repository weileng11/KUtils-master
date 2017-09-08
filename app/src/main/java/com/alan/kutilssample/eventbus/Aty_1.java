package com.alan.kutilssample.eventbus;

import android.os.Bundle;
import android.widget.TextView;

import com.alan.kutilssample.R;
import com.zwy.kutils.eventbus.EventBus;
import com.zwy.kutils.eventbus.Subscribe;
import com.zwy.kutils.eventbus.ThreadMode;
import com.zwy.kutils.utils.Log;
import com.zwy.kutils.widget.baseview.BaseActivity;
import com.zwy.kutils.widget.customview.circleimageview.CircleImageView;


import butterknife.Bind;

/**
 * ================================================================
 * 创建时间：2017/8/1 上午9:17
 * 创建人：Alan
 * 文件描述：
 * 至尊宝：长夜漫漫无心睡眠，我以为只有我睡不着，原来晶晶姑娘你也睡不着 ！
 * ================================================================
 */
public class Aty_1 extends BaseActivity {
    @Bind(R.id.tv_actionbar)
    TextView mTvActionbar;
    @Bind(R.id.cv)
    CircleImageView mCv;
    @Bind(R.id.tv_aty_1)
    TextView mTvAty1;

    /**
     * 是否需要沉浸式状态栏 不需要时返回0 需要时返回颜色
     *
     * @return StatusBarTintModle(boolean isTranslucentStatus, int color);
     */
    @Override
    protected int isTranslucentStatus() {
        return 0;
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
        return R.layout.aty_1;
    }

    /**
     * 初始化View
     *
     * @param savedInstanceState aty销毁时保存的临时参数
     */
    @Override
    protected void initView(Bundle savedInstanceState) {
        mTvActionbar.setText("EventBus测试");
    }

    /**
     * 初始化数据源
     */
    @Override
    protected void initData() {
        EventBus.getDefault().post("第二个页面发出的一般事件，我是事件内容str");
    }

    /**
     * Bundle  传递数据
     *
     * @param extras
     */
    @Override
    protected void getBundleExtras(Bundle extras) {

    }
//
//    @Subscribe(threadMode = ThreadMode.MAIN ,sticky = true,tag = "tag1111")
//    public void onEventBus_123(String msg) {
//        mTvAty1.setText(msg);
//        Log.d("onEventBus_123 收到参数：" + msg);
//    }

}
