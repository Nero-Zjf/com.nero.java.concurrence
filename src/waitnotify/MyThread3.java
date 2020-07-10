package waitnotify;

public class MyThread3 extends Thread {
    private  IncreaceCount ic;
    private Object lock;

    public MyThread3(IncreaceCount ic,Object lock) {
        super();
        this.ic = ic;
        this.lock = lock;
    }


    @Override
    public void run() {
        if (ic.getCount() < 5) {
            try {
                synchronized (ic) {
                    System.out.println("线程"+Thread.currentThread().getName()+"等待开始");
                    ic.wait();
                    System.out.println("线程"+Thread.currentThread().getName()+"等待结束");
                    Thread.sleep(3000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("线程"+Thread.currentThread().getName()+"退出");
    }
}
