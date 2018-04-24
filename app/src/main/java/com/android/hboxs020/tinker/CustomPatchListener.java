package com.android.hboxs020.tinker;

import android.content.Context;

import com.tencent.tinker.lib.listener.DefaultPatchListener;

/**
 * 验证patch文件，正确的话启动服务安装patch文件
 *
 * Created by hboxs020 on 2018/4/24.
 */

public class CustomPatchListener extends DefaultPatchListener {
    public CustomPatchListener(Context context) {
        super(context);
    }

    @Override
    protected int patchCheck(String path, String patchMd5) {

        //可在此处加入自己的验证规则

        return super.patchCheck(path, patchMd5);
    }
}
