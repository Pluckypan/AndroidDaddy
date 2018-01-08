package engineer.echo.bigandroid;

import android.app.Application;

import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsListener;

/**
 * 分红全球购
 * 青岛芳林信息版权所有
 * author:Created by Plucky on 16/9/20-下午3:37.
 * 功能描述:
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
