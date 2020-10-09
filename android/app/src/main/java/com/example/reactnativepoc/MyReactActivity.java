package com.example.reactnativepoc;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.shell.MainReactPackage;
import com.microsoft.codepush.react.CodePush;

public class MyReactActivity extends Activity implements DefaultHardwareBackBtnHandler {
    private ReactRootView mReactRootView;
    public ReactInstanceManager mReactInstanceManager;
    private Button mBtn;
    private Button mJSBtn;
    private static TextView mResponseView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this,"Starting react application", Toast.LENGTH_LONG).show();
        mReactRootView = new ReactRootView(this);
        mReactInstanceManager = ReactInstanceManager.builder()
                .addPackage(new MainReactPackage())
                .addPackage(new CodePush("nuLxj9fUy1s6RONd8W4YZBqu9mMI9-kEp1JmJ", getApplicationContext(), BuildConfig.DEBUG))
                .addPackage(new BridgingPackage())
                .setApplication(getApplication())
                //.setBundleAssetName("index.android.bundle")
                .setJSMainModulePath("index")
                .setUseDeveloperSupport(BuildConfig.DEBUG)
                .setInitialLifecycleState(LifecycleState.BEFORE_RESUME)
                .setJSBundleFile(CodePush.getJSBundleFile())
                .build();

        mReactRootView.startReactApplication(mReactInstanceManager, "awesomenativeapp", null);
        setContentView(R.layout.activity_2);

        mBtn = (Button) findViewById(R.id.btn1);
        mJSBtn = (Button) findViewById(R.id.btn2);
        mResponseView = (TextView) findViewById(R.id.responseView) ;



        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BridgingModule.getComments();
            }
        });

        mJSBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(mReactRootView);
            }
        });



    }

    public static void displayData(String response){
        mResponseView.setText(response);
    }

    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostPause(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostResume(this, this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostDestroy(this);
        }
    }

    @Override
    public void onBackPressed() {
        if (mReactInstanceManager != null) {
            mReactInstanceManager.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU && mReactInstanceManager != null) {
            mReactInstanceManager.showDevOptionsDialog();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

}