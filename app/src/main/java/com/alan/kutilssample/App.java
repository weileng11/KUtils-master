package com.alan.kutilssample;

import android.app.Application;

import com.alan.kutilssample.bean.IsFirstEnterApp;
import com.alan.kutilssample.greendao.DaoMaster;
import com.alan.kutilssample.greendao.DaoSession;
import com.zwy.kutils.KUtilLibs;
import com.zwy.kutils.http.HttpBuild;
import com.zwy.kutils.utils.Log;

import org.greenrobot.greendao.database.Database;

import java.util.HashMap;
import java.util.Map;

/**
 * ================================================================
 * 创建时间：2017/7/27 上午9:42
 * 创建人：Alan
 * 文件描述：
 * 至尊宝：长夜漫漫无心睡眠，我以为只有我睡不着，原来晶晶姑娘你也睡不着 ！
 * ================================================================
 */
public class App extends Application {
    public final String TAG = "KUtils测试Demo";
    private static DaoSession mDaoSession;
    private final String DBNAME = "kutils_db";

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化KUtils
        KUtilLibs.init(true, TAG, this, new HttpBuild.Build(null, 10, HttpBuild.CookieType.MemoryCookieStore));
        //初始化GreenDao
        initGreenDao();
        initAppInfos();
    }

    private void initAppInfos() {
        IsFirstEnterApp isFirstEnterApp = new IsFirstEnterApp();
        isFirstEnterApp.setId(0l);
        isFirstEnterApp.setIsFirstEnterApp(true);
        if (mDaoSession.getIsFirstEnterAppDao().load(0l) == null) {
            mDaoSession.getIsFirstEnterAppDao().insert(isFirstEnterApp);
        }
    }

    private void initGreenDao() {
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(getApplicationContext(), DBNAME);
        Database db = openHelper.getWritableDb();
        DaoMaster daoMaster = new DaoMaster(db);
        mDaoSession = daoMaster.newSession();
        Log.d("GreenDao初始化成功");
    }

    public static DaoSession getDaoSession() {
        return mDaoSession;
    }
}
