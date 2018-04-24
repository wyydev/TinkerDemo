package com.android.hboxs020.tinker;

import com.tencent.tinker.lib.service.DefaultTinkerResultService;
import com.tencent.tinker.lib.service.PatchResult;
import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.lib.util.TinkerServiceInternals;

import java.io.File;

/**
 * 决定patch文件安装完的后续操作，默认实现是杀进程
 *
 * Created by hboxs020 on 2018/4/24.
 */

public class CustomResultService extends DefaultTinkerResultService {

    private final String TAG = CustomResultService.class.getSimpleName();

    //返回patch文件的最终安装结果
    @Override
    public void onPatchResult(PatchResult result) {
        if (result == null) {
            TinkerLog.e(TAG, "DefaultTinkerResultService received null result!!!!");
            return;
        }
        TinkerLog.i(TAG, "DefaultTinkerResultService received a result:%s ", result.toString());

        //first, we want to kill the recover process
        TinkerServiceInternals.killTinkerPatchServiceProcess(getApplicationContext());

        // if success and newPatch, it is nice to delete the raw file, and restart at once
        // only main process can load an upgrade patch!
        if (result.isSuccess) {
            //成功之后只删除patch文件不杀掉进程
            deleteRawPatchFile(new File(result.rawPatchFilePath));
//            if (checkIfNeedKill(result)) {
//                android.os.Process.killProcess(android.os.Process.myPid());
//            } else {
//                TinkerLog.i(TAG, "I have already install the newly patch version!");
//            }
        }
    }
}
