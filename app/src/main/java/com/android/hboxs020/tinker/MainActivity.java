package com.android.hboxs020.tinker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final String SUFFIX = ".apk";
    private String mPatchDir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPatchDir = getExternalCacheDir().getAbsolutePath()+"/tpacth/";
        File file = new File(mPatchDir);

        if (!file.exists()){
            file.mkdir();
        }
    }

    public void testTinker(View view) {

        Toast.makeText(this,"热更新成功",Toast.LENGTH_SHORT).show();
    }

    private String getPatchName() {
        return mPatchDir.concat("patch_signed").concat(SUFFIX);
    }

    public void loadPatch(View view) {
        TinkerManager.loadPatch(getPatchName());
    }
}
