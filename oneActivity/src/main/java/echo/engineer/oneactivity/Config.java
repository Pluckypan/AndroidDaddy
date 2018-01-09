package echo.engineer.oneactivity;

import android.os.Environment;

import java.io.File;

/**
 * Config
 * Created by Plucky<plucky@echo.engineer> on 7/10/17 2017 11:37.
 */

public class Config {
    private final static String APP_FOLDER_NAME = "OneActivity";
    public static String APP_FILE_DIR = Environment.getExternalStorageDirectory() + File.separator + APP_FOLDER_NAME;
    public static String LOG_PATH = APP_FILE_DIR + File.separator + ".log" + File.separator;

    public static void makeSureDirExits() {
        File fr = new File(LOG_PATH);
        if (!fr.exists()) {
            fr.mkdirs();
        }
    }
}
