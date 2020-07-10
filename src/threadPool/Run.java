package threadPool;

import javax.swing.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Run {
    public static void main(String[] args) throws InterruptedException {

        ExecutorService es = Executors.newCachedThreadPool();

//      Thread[] threads = new Thread[10];
//        for (int i = 1; i <= 1; i++) {
//            threads[i-1] = new Thread(new MyThread(String.valueOf(i)));
//            es.submit(threads[i-1]);
//        }

//        es.submit(() -> {
//            System.out.println("MyCallable-call()-start");
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("MyCallable-call()-end");
//        });
        Timer timer = new Timer(5000, System.out::println);
        timer.start();

        Thread.sleep(6000);
        es.shutdown();
//        timer.stop();
    }
}

