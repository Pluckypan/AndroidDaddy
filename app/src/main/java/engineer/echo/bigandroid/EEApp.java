package engineer.echo.bigandroid;

import android.app.Application;

import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsListener;

/**
 * EEApp.java.java
 * Info: EEApp.java
 * Created by Plucky<plucky@echo.engineer> on 2018/1/16 - 20:29
 * More about me: http://www.1991th.com
 */
public class EEApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initX5Webview();
    }


    private void initX5Webview() {
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {

            }

            @Override
            public void onCoreInitFinished() {

            }
        };
        QbSdk.setTbsListener(new TbsListener() {
            @Override
            public void onDownloadFinish(int i) {

            }

            @Override
            public void onInstallFinish(int i) {

            }

            @Override
            public void onDownloadProgress(int i) {

            }
        });
        QbSdk.allowThirdPartyAppDownload(true);
        QbSdk.initX5Environment(getApplicationContext(), QbSdk.WebviewInitType.FIRSTUSE_AND_PRELOAD, cb);
    }
}
