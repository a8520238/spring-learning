import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        Object mutex = new Object();
        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (mutex) {
                    System.out.println("b加锁成功");
                    try {
                        mutex.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    mutex.notify();
                    System.out.println("btest");
                }
                System.out.println("btest2");
            }
        });
        b.start();
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (mutex) {
                    System.out.println("a加锁成功");
                    //                        mutex.wait();
                    mutex.notify();
                    System.out.println("atest");
                }
                System.out.println("atest2");
                for (int i = 1; i <= 31; i++) {
                    System.out.println(i);
                }
            }
        });
        a.start();
    }
}
