package engineer.echo.synchronize;

/**
 * Man
 * Created by Plucky<plucky.pan@ubnt.com> on 29/06/2017 8:55 PM.
 */

public class Man {

    /**
     * 对象锁 synchronized 修饰代码块
     */
    public void funcA() {
        synchronized (this) {
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
     * 对象锁 synchronized 修饰方法
     */
    public synchronized void funcB() {
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