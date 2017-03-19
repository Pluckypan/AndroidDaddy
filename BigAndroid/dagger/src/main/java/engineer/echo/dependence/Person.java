package engineer.echo.dependence;

import android.content.Context;
import android.util.Log;

/**
 * Person
 * Created by Plucky<plucky.pan@ubnt.com> on 2017/3/19 下午4:15.
 */

public class Person {


    public Person(Context context) {
        Log.d("Preson", toString() + " context:" + context.getClass().getSimpleName());
    }

    public Person(String name) {
        Log.d("Preson", toString() + " name:" + name);
    }
}
