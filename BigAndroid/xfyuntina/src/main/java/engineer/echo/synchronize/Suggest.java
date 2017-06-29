package engineer.echo.synchronize;

/**
 * Suggest
 * Created by Plucky<plucky.pan@ubnt.com> on 29/06/2017 10:51 PM.
 */

public class Suggest {
    private Object object = new Object();

    public synchronized void notSuggestFunc() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        System.out.println("notSuggestFunc " + Thread.currentThread().getName() + " - " + object.toString());
    }

    public void suggestFunc(){
        synchronized (object){
            try {
                Thread.sleep(500);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            System.out.println("suggestFunc " + Thread.currentThread().getName() + " - " + object.toString());
        }
    }
}
