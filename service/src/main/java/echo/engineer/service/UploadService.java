package echo.engineer.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/**
 * UploadService
 * Created by Plucky<plucky.pan@ubnt.com> on 5/22/17 2017 15:24.
 */

public class UploadService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return new UploadBinder();
    }


    public class UploadBinder extends Binder {

        public UploadService getService() {
            return UploadService.this;
        }
    }
}
