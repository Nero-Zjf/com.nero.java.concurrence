package waitnotify;

public class MyThread1 extends Thread {
    private IncreaceCount ic;
    private Object lock;

    public MyThread1(IncreaceCount ic, Object lock) {
        super();
        this.ic = ic;
        this.lock = lock;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ic.addCount();
            System.out.println("线程" + Thread.currentThread().getName() + "                 " + ic.getCount());
            synchronized (ic) {
                if (ic.getCount() == 5) {
                    ic.notifyAll();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程" + Thread.currentThread().getName() + "                 即将退出同步");
                }


            }
        }
    }
}
