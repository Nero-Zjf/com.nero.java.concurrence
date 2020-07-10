package waitnotify;

public class test2 {
    public static void main(String[] args) throws InterruptedException {
        IncreaceCount ic = new IncreaceCount();
        Object lock = new Object();
        MyThread1 myThread1 = new MyThread1(ic,lock);
        myThread1.setName("myThread1");
        MyThread2 myThread2 = new MyThread2(ic,lock);
        myThread2.setName("myThread2");
        MyThread3 myThread3 = new MyThread3(ic,lock);
        myThread3.setName("myThread3");
        myThread1.start();
        myThread3.start();
        myThread2.start();
    }
}
