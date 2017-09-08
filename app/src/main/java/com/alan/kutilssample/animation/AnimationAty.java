package com.alan.kutilssample.animation;

import android.animation.Animator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alan.kutilssample.MyBaseActivity;
import com.alan.kutilssample.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zwy.kutils.animations.Techniques;
import com.zwy.kutils.animations.YoYo;
import com.zwy.kutils.utils.HideUtil;
import com.zwy.kutils.widget.customview.circleimageview.CircleImageView;
import com.zwy.kutils.widget.loadingdialog.DialogUIUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * ================================================================
 * 创建时间：2017/8/2 上午9:18
 * 创建人：Alan
 * 文件描述：
 * 至尊宝：长夜漫漫无心睡眠，我以为只有我睡不着，原来晶晶姑娘你也睡不着 ！
 * ================================================================
 */
public class AnimationAty extends MyBaseActivity {
    @Bind(R.id.tv_actionbar)
    TextView mTvActionbar;
    @Bind(R.id.cv)
    CircleImageView mCv;
    @Bind(R.id.hello_world)
    TextView mHelloWorld;
    @Bind(R.id.wrapper)
    LinearLayout mWrapper;
    @Bind(R.id.rv)
    RecyclerView mRv;
    private MyAdpter mAdpter;
    private YoYo.YoYoString rope;

    /**
     * 自己进行相关逻辑拓展
     */
    @Override
    protected void test() {

    }

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
        return R.layout.animation;
    }

    /**
     * 初始化View
     *
     * @param savedInstanceState aty销毁时保存的临时参数
     */
    @Override
    protected void initView(Bundle savedInstanceState) {
        HideUtil.init(this);
        mTvActionbar.setText("动画测试");
        mAdpter = new MyAdpter(null);
        mAdpter.openLoadAnimation();
        mRv.setLayoutManager(new LinearLayoutManager(mContext));
        mRv.setAdapter(mAdpter);

        Techniques[] techniques = Techniques.values();
        for (int i = 0; i < techniques.length; i++) {
            mAdpter.addData(techniques[i].getAnimator().getClass().getName());
        }
        mAdpter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (rope != null) {
                    rope.stop(true);
                }
                Techniques technique = (Techniques) view.getTag();
                rope = YoYo.with(technique)
                        .duration(1200)
                        .repeat(YoYo.INFINITE)
                        .pivot(YoYo.CENTER_PIVOT, YoYo.CENTER_PIVOT)
                        .interpolate(new AccelerateDecelerateInterpolator())
                        .withListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                            }

                            @Override
                            public void onAnimationCancel(Animator animation) {
                                showToast("动画被取消");
                            }

                            @Override
                            public void onAnimationRepeat(Animator animation) {

                            }
                        })
                        .playOn(mWrapper);
            }
        });
    }

    /**
     * 初始化数据源
     */
    @Override
    protected void initData() {

    }

    /**
     * Bundle  传递数据
     *
     * @param extras
     */
    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @OnClick({R.id.hello_world})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.hello_world:
                if (rope != null) {
                    rope.stop(true);
                }
                break;
        }
    }

    private class MyAdpter extends BaseQuickAdapter<String, BaseViewHolder> {

        public MyAdpter(@Nullable List<String> data) {
            super(R.layout.item_rv_animation, data);
        }

        /**
         * Implement this method and use the helper to adapt the view to the given item.
         *
         * @param helper A fully initialized helper.
         * @param item   The item that needs to be displayed.
         */
        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.tv_title, item.substring(item.lastIndexOf(".") + 1));
            helper.setTag(R.id.tv_title,Techniques.values()[helper.getPosition()]);
        }
    }
}
