package engineer.echo.bigandroid;

/**
 * Const.java.java
 * Info: Const.java
 * Created by Plucky<plucky@echo.engineer> on 2018/1/16 - 19:51
 * More about me: http://www.1991th.com
 */

public class Const {
    private static int[] EE_COLOR = {
            R.color.eecolor_1, R.color.eecolor_2, R.color.eecolor_3, R.color.eecolor_4, R.color.eecolor_5,
            R.color.eecolor_6, R.color.eecolor_7, R.color.eecolor_8, R.color.eecolor_9, R.color.eecolor_10,
            R.color.eecolor_11, R.color.eecolor_12, R.color.eecolor_13, R.color.eecolor_14, R.color.eecolor_15,
            R.color.eecolor_16, R.color.eecolor_17, R.color.eecolor_18, R.color.eecolor_19, R.color.eecolor_20,
    };

    public static int getColor(int index) {
        int c = EE_COLOR.length;
        int i = index < 0 ? 0 : (index > c - 1 ? c - 1 : index);
        return EE_COLOR[i];
    }
}
