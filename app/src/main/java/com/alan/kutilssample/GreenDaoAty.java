package com.alan.kutilssample;

import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alan.kutilssample.bean.User;
import com.alan.kutilssample.greendao.UserDao;
import com.zwy.kutils.utils.Utils;
import com.zwy.kutils.widget.baseview.BaseActivity;
import com.zwy.kutils.widget.customview.circleimageview.CircleImageView;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * ================================================================
 * 创建时间：2017/7/31 上午10:04
 * 创建人：Alan
 * 文件描述：GreenDao测试页面
 * 至尊宝：长夜漫漫无心睡眠，我以为只有我睡不着，原来晶晶姑娘你也睡不着 ！
 * ================================================================
 */
public class GreenDaoAty extends BaseActivity {
    @Bind(R.id.tv_actionbar)
    TextView mTvActionbar;
    @Bind(R.id.tv_apped)
    TextView mTvApped;
    @Bind(R.id.tv_add)
    TextView mTvAdd;
    @Bind(R.id.tv_delete)
    TextView mTvDelete;
    @Bind(R.id.tv_updata)
    TextView mTvUpdata;
    @Bind(R.id.tv_seletor)
    TextView mTvSeletor;
    @Bind(R.id.cv)
    CircleImageView mCv;
    @Bind(R.id.sv)
    ScrollView mScrollView;
    private UserDao userDao;

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
        return R.layout.greendao;
    }

    /**
     * 初始化View
     *
     * @param savedInstanceState aty销毁时保存的临时参数
     */
    @Override
    protected void initView(Bundle savedInstanceState) {
        mTvActionbar.setText("GreenDao使用");
    }

    /**
     * 初始化数据源
     */
    @Override
    protected void initData() {
        try {
            userDao = App.getDaoSession().getUserDao();
            List<User> list = userDao.loadAll();
            apped("当前User表中所有的数据：");
            for (int i = 0; i < list.size(); i++) {
                apped(list.get(i).toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            mTvAdd.setEnabled(false);
            mTvDelete.setEnabled(false);
            mTvUpdata.setEnabled(false);
            mTvSeletor.setEnabled(false);
            apped("数据库当前无法操作");
        }
    }

    /**
     * Bundle  传递数据
     *
     * @param extras
     */
    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @OnClick({R.id.tv_add, R.id.tv_delete, R.id.tv_updata, R.id.tv_seletor})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_add:
                String userName = Utils.getRandomName(1);
                int age = new Random().nextInt(30);
                User user = new User();
                user.setName(userName);
                user.setAge(age);
                userDao.insert(user);
                apped("已成功插入一条(name = " + userName + ",age = " + age + ")的数据");
                break;
            case R.id.tv_delete:
//                //删除一般配和查询完成
//                QueryBuilder<User> userQueryBuilder = userDao.queryBuilder();
//                List<User> userList = userQueryBuilder.where(UserDao.Properties.Name.eq("志文")).list();
//                User u = (userList != null && userList.size() > 0) ? userList.get(0) : null;
//                if (u == null) {
//                    apped("当前要删除的用户不存在");
//                    return;
//                }
//                userDao.delete(u);
//                apped("删除成功");
                userDao.deleteAll();
                apped("已删除全部数据");
                break;
            case R.id.tv_updata:
                QueryBuilder<User> userQueryBuilder = userDao.queryBuilder();
                List<User> userList = userQueryBuilder.where(UserDao.Properties.Name.eq("志强")).list();
                if (userList != null && userList.size() > 0) {
                    userList.get(0).setName("张三");
                    userDao.update(userList.get(0));
                    apped("已将姓名为志强的数据修改姓名为张三");
                } else {
                    apped("要修改的数据不存在");
                }
                break;
            case R.id.tv_seletor:
                mTvApped.setText("");
                apped("当前User表中所有的数据：");
                for (int i = 0; i < userDao.loadAll().size(); i++) {
                    apped(userDao.loadAll().get(i).toString());
                }
                break;
        }
    }


    private void apped(String msg) {
        mTvApped.append(msg + "\n");
        mScrollView.fullScroll(View.FOCUS_DOWN);
    }
}
