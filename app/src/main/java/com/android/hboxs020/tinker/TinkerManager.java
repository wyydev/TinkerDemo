package com.android.hboxs020.tinker;

import android.content.Context;

import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.app.ApplicationLike;

/**
 * Tinker api 管理
 * Created by hboxs020 on 2018/4/23.
 */

public class TinkerManager {

    private static boolean installed = false;

    private static ApplicationLike mApplicationLike;

    public static void installTinker(ApplicationLike applicationLike){
        mApplicationLike = applicationLike;
        if (installed){
            //已经初始化过
            return;
        }
        //完成Tinker初始化
        TinkerInstaller.install(mApplicationLike);
    }

    public static void loadPatch(String path){
        if (Tinker.isTinkerInstalled()){
            TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(),path);
        }
    }

    //获取ApplicationContext
    private static Context getApplicationContext(){
        if (mApplicationLike != null) {
            return mApplicationLike.getApplication().getApplicationContext();
        }
        return null;
    }
}
