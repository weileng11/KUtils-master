package com.alan.kutilssample.http;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alan.kutilssample.MyBaseActivity;
import com.alan.kutilssample.R;
import com.alan.kutilssample.http.bean.GithubModle;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.zwy.kutils.widget.customview.circleimageview.CircleImageView;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * ================================================================
 * 创建时间：2017/8/2 上午11:44
 * 创建人：Alan
 * 文件描述：网络请求测试
 * 至尊宝：长夜漫漫无心睡眠，我以为只有我睡不着，原来晶晶姑娘你也睡不着 ！
 * ================================================================
 */
public class HttpAty extends MyBaseActivity {
    private final String URL_Array = "https://api.github.com/orgs/octokit/repos";
    private final String URL_Object = "https://api.github.com/repos/octokit/octokit.rb";
    @Bind(R.id.tv_actionbar)
    TextView mTvActionbar;
    @Bind(R.id.cv)
    CircleImageView mCv;

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
        return R.layout.http;
    }

    /**
     * 初始化View
     *
     * @param savedInstanceState aty销毁时保存的临时参数
     */
    @Override
    protected void initView(Bundle savedInstanceState) {
        mTvActionbar.setText("网络请求测试");
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


    public void onClick(View view) {
        OkGo.<String>get(URL_Object).execute(new Callback<String>() {
            @Override
            public void onStart(Request<String, ? extends Request> request) {

            }

            @Override
            public void onSuccess(Response<String> response) {
//                showToast(response.body());
                GithubModle githubModle = null;
                try {
                    githubModle = new Gson().fromJson(response.getRawResponse().body().string(), GithubModle.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                showToast("数据解析成功：\n" + githubModle.toString());

            }

            @Override
            public void onCacheSuccess(Response<String> response) {

            }

            @Override
            public void onError(Response<String> response) {
                showToast(response.message());
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void uploadProgress(Progress progress) {

            }

            @Override
            public void downloadProgress(Progress progress) {

            }

            @Override
            public String convertResponse(okhttp3.Response response) throws Throwable {
                return null;
            }
        });
//
//        OkGo.<GithubModle>post(URL_Array)
//                .cacheMode(CacheMode.NO_CACHE)//缓存模式
//                .cacheKey(URL_Array)//缓存key
//                .headers("k", "v")//自定义headers
//                .params("k", "v")//参数
//                .cacheTime(10000)//缓存过期时间
//                .isMultipart(true)//是否强制表单上传
//                .upJson("json")//上送json串
//                .upFile(new File(""))//上送文件
//                .upBytes(new byte[]{})//上送字节数组
//                .tag("tag")//请求的tag，可用来取消该请求
////                .execute()
//                .execute(new AbsCallback<GithubModle>() {
//                    @Override
//                    public void onSuccess(Response<GithubModle> response) {
//
//                    }
//
//                    @Override
//                    public GithubModle convertResponse(okhttp3.Response response) throws Throwable {
//                        return null;
//                    }
//                });
//        OkGo.getInstance().cancelAll();//取消全部请求
//        OkGo.getInstance().cancelTag("tag");//根据tag取消请求

    }

}
