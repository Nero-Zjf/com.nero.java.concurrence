package nero;

public class Service {
     public static void service1() throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+"-service1-start");
//        service2();
         Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName()+"-service1-end");

    }

    synchronized public void service2() throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+"-service2-start");
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName()+"-service2-end");
    }

    synchronized public void service3() throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+"-service3-start");
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName()+"-service3-end");
    }
}
