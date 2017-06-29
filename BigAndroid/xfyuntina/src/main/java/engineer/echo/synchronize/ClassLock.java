package engineer.echo.synchronize;

/**
 * ClassLock
 * Created by Plucky<plucky.pan@ubnt.com> on 29/06/2017 10:31 PM.
 */

public class ClassLock {
    /**
     * 类锁 synchronized 修饰代码块
     */
    public void funcA() {
        synchronized (ClassLock.class) {
            int i = 5;
            while (i-- > 0) {
                System.out.println("funcA " + Thread.currentThread().getName() + " : " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        }
    }

    /**
     * 类锁 synchronized 修饰静态方法
     */
    public static synchronized void funcB() {
        int i = 5;
        while (i-- > 0) {
            System.out.println("funcB " + Thread.currentThread().getName() + " : " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }
}
