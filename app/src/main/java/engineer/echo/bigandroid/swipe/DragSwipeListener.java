package engineer.echo.bigandroid.swipe;

/**
 * DragSwipeListener.java.java
 * Info: DragSwipeListener.java
 * Created by Plucky<plucky@echo.engineer> on 2018/1/16 - 20:25
 * More about me: http://www.1991th.com
 */
public interface DragSwipeListener {
    void onChange(int position);

    void onDrag(int from, int to);
}
