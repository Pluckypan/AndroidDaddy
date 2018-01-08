package engineer.echo.synchronize;

/**
 * Synchronize
 * Created by Plucky<plucky.pan@ubnt.com> on 29/06/2017 8:34 PM.
 */

public class Synchronize {
    public static void main(String[] args) {
        final Man aman = new Man();
        Thread test1 = new Thread(new Runnable() {
            public void run() {
                aman.funcA();
            }
        }, "test1");
        Thread test2 = new Thread(new Runnable() {
            public void run() {
                aman.funcB();
            }
        }, "test2");
        test1.start();
        test2.start();

        final ClassLock lock = new ClassLock();

        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.funcA();
            }
        }, "thread3").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.funcB();
            }
        }, "thread4").start();

        final Suggest suggest=new Suggest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                suggest.notSuggestFunc();
            }
        }, "thread5").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                suggest.suggestFunc();
            }
        }, "thread6").start();

    }
}
