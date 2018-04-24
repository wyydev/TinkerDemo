package com.android.hboxs020.tinker;

import android.content.Context;

import com.tencent.tinker.lib.patch.AbstractPatch;
import com.tencent.tinker.lib.patch.UpgradePatch;
import com.tencent.tinker.lib.reporter.DefaultLoadReporter;
import com.tencent.tinker.lib.reporter.DefaultPatchReporter;
import com.tencent.tinker.lib.reporter.LoadReporter;
import com.tencent.tinker.lib.reporter.PatchReporter;
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

    private static CustomPatchListener mCustomPatchListener;

    public static void installTinker(ApplicationLike applicationLike){
        mApplicationLike = applicationLike;
        if (installed){
            //已经初始化过
            return;
        }

        mCustomPatchListener = new CustomPatchListener(getApplicationContext());

        //日志上报，监听patch文件安装结果
        LoadReporter loadReporter = new DefaultLoadReporter(getApplicationContext());
        PatchReporter patchReporter = new DefaultPatchReporter(getApplicationContext());

        AbstractPatch abstractPatch = new UpgradePatch(); //决定tinker patch文件安装策略
        //完成Tinker初始化
//        TinkerInstaller.install(mApplicationLike);
        TinkerInstaller.install(mApplicationLike,loadReporter,patchReporter,
                mCustomPatchListener,CustomResultService.class,abstractPatch);
        installed = true;
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
