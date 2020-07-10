package threadgroup;

public class Run {
    public static void main(String[] args) throws InterruptedException {
        ThreadGroup threadGroup = new ThreadGroup("main");

        Thread threadA = new Thread(threadGroup,new MyThread("A"));
        Thread threadB = new Thread(threadGroup,new MyThread("B"));

        threadA.start();
        threadB.setDaemon(true);
        threadB.start();

        Thread.sleep(2000);
        threadA.interrupt();
    }
}
