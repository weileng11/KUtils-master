package com.alan.kutilssample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alan.kutilssample.animation.AnimationAty;
import com.alan.kutilssample.bean.IsFirstEnterApp;
import com.alan.kutilssample.bean.TitleModel;
import com.alan.kutilssample.eventbus.Aty_1;
import com.alan.kutilssample.greendao.IsFirstEnterAppDao;
import com.alan.kutilssample.http.HttpAty;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zwy.kutils.eventbus.Subscribe;
import com.zwy.kutils.eventbus.ThreadMode;
import com.zwy.kutils.utils.Log;
import com.zwy.kutils.widget.baseview.BaseActivity;
import com.zwy.kutils.widget.customview.circleimageview.CircleImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.Bind;


public class MainActivity extends MyBaseActivity {
    @Bind(R.id.tv_actionbar)
    TextView mTvActionbar;
    @Bind(R.id.cv)
    CircleImageView mCv;
    @Bind(R.id.rv)
    RecyclerView mRv;
    @Bind(R.id.sw)
    SwipeRefreshLayout mSw;
    private String json = "{\"key_a\":999,\"key_b\":\"这是b的值\"}";
    private String text = "这是一条测试日志";
    private MyAdapter mAdapter;

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
        return true;
    }

    /**
     * 设置布局ID
     *
     * @return 资源文件ID
     */
    @Override
    protected int getLayoutId() {
        List<IsFirstEnterApp> isFirstEnterApps = App.getDaoSession().getIsFirstEnterAppDao().queryBuilder().where(IsFirstEnterAppDao.Properties.IsFirstEnterApp.eq(true)).list();
        if (isFirstEnterApps != null && isFirstEnterApps.size() > 0) {
            Log.d("检查到用户未第一次登录，已关闭也引导页面显示");
            isFirstEnterApps.get(0).setIsFirstEnterApp(false);
            App.getDaoSession().getIsFirstEnterAppDao().update(isFirstEnterApps.get(0));
        }
        return R.layout.activity_main;
    }

    /**
     * 初始化View
     *
     * @param savedInstanceState aty销毁时保存的临时参数
     */
    @Override
    protected void initView(Bundle savedInstanceState) {
        LogTestInfos();
        mTvActionbar.setText("KUtilsTestSample");
        mCv.setVisibility(View.VISIBLE);
        initAdapter();
        setListener();
        Snackbar.make(mRv, "SnackbarClicked", Snackbar.LENGTH_LONG).setAction("Action", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("I'm a toast");
            }
        }).setActionTextColor(Color.RED).show();

    }

    private void setListener() {
        //适配器Item点击事件
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (((TitleModel) adapter.getData().get(position)).getIndex()) {
                    toNext(position);
                } else {
                    showToast("您点击了第" + (position + 1) + "条数据");
                }
            }
        });
        //适配器子view点击事件
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.clicktestview:
                        showToast("您点击了第" + (position + 1) + "条数据的子view");
                        break;
                }
            }
        });
        //下拉刷新监听
        mSw.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setAdapterData();
            }
        });
        //加载更多监听
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                addData();
            }
        }, mRv);
    }

    private void toNext(int position) {
        switch (position) {
            case 0:
                readyGo(GreenDaoAty.class);
                break;
            case 1:
                //跳转前发出粘性事件
//                EventBus.getDefault().postSticky("我是从主页传过来的字符串");
                readyGo(Aty_1.class);
                break;
            case 2:
                readyGo(AnimationAty.class);
                break;
            case 3:
                readyGo(HttpAty.class);
                break;
//            case 4:
//                break;
//            case 5:
//                break;
        }
    }

    private synchronized void addData() {
        //模拟添加数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Random random = new Random();
                            int a = random.nextInt(3);
//                            Log.d("nextInt = " + a);
                            if (a == 0) {
                                Log.d("nextInt = " + a);
                                for (int i = 0; i < 5; i++) {
                                    mAdapter.addData(new TitleModel("我是新添加的第" + (i + 1) + "条数据", false));
                                }
                                mAdapter.loadMoreComplete();
                            } else if (a == 1) {
                                mAdapter.loadMoreEnd();
                            } else if (a == 2) {
                                mAdapter.loadMoreFail();
                            } else {
                                Log.d("nextInt = " + a);
                                for (int i = 0; i < 20; i++) {
                                    mAdapter.addData(new TitleModel("我是新添加的第" + (i + 1) + "条数据", false));
                                }
                                mAdapter.loadMoreComplete();
                            }
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void initAdapter() {
        mAdapter = new MyAdapter(null);//可以直接传入数据，数据未获取到的情况下可以传null
        mAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);//设置列表加载动画
        mAdapter.isFirstOnly(false);//是否仅在第一次加载列表时展示动画
        mRv.setLayoutManager(new LinearLayoutManager(mContext));
        mRv.setAdapter(mAdapter);
    }

    /**
     * 初始化数据源
     */
    @Override
    protected void initData() {
        Glide.with(mContext).load("http://img14.poco.cn/mypoco/myphoto/20130410/14/173420773201304101425203047950463653_010.jpg")
                .asBitmap().placeholder(R.drawable.nullimg).into(mCv);
        mSw.setRefreshing(true);
        setAdapterData();
    }

    private void setAdapterData() {
        //模拟延迟加载数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (mSw.isRefreshing()) {
                                mSw.setRefreshing(false);
                                List<TitleModel> list = new ArrayList<>();
                                list.add(new TitleModel("GreenDao使用", true));
                                list.add(new TitleModel("EventBus使用", true));
                                list.add(new TitleModel("Anination使用", true));
                                list.add(new TitleModel("OkGo网络请求使用", true));
//                                list.add(new TitleModel("viewpager+tab顶部使用", true));
//                                list.add(new TitleModel("viewpager+tab底部使用", true));

                                list.add(new TitleModel("...", false));
                                mAdapter.setNewData(list);//添加集合数据
                                return;
                            }
                            for (int i = 0; i < 6; i++) {
                                mAdapter.addData(new TitleModel("RV功能展示", false));//添加单条数据
                            }

                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
//        mAdapter.setEmptyView(getEmptyview());//设置没有数据时的空白页面
//        mAdapter.addFooterView(getFootView());//添加一个脚布局 有三个相应的方法
//        mAdapter.addHeaderView(getHeaderView());//添加一个头布局 有三个相应的方法
//        mAdapter.notify...();//内容发生改变时刷新方法
//        mAdapter.remove();
    }

    /**
     * Bundle  传递数据
     *
     * @param extras
     */
    @Override
    protected void getBundleExtras(Bundle extras) {

    }


    private void LogTestInfos() {
        Log.d(json);
        Log.e(json);
        Log.w(json);

        Log.d(text);
        Log.e(text);
        Log.w(text);
    }

    /**
     * 自己进行相关逻辑拓展
     */
    @Override
    protected void test() {

    }


    private class MyAdapter extends BaseQuickAdapter<TitleModel, BaseViewHolder> {

        public MyAdapter(@Nullable List<TitleModel> data) {
            super(R.layout.item_rv_main, data);
        }

        /**
         * Implement this method and use the helper to adapt the view to the given item.
         *
         * @param helper A fully initialized helper.
         * @param item   The item that needs to be displayed.
         */
        @Override
        protected void convert(BaseViewHolder helper, TitleModel item) {
            helper.setText(R.id.title, item.getTitle());
            helper.addOnClickListener(R.id.clicktestview);//给子view添加点击事件
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void event(String msg) {
        Log.d("收到第二个页面传入的参数：" + msg);
        mTvActionbar.setText(msg);
    }

}
