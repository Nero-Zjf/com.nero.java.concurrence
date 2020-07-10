package threadPool;

public class MyThread implements Runnable {
    String output = "";

    public MyThread(String output) {
        this.output = output;
    }

    @Override
    public void run() {
//        while (true) {
//            if (Thread.interrupted()) {
//                return;
//            }
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
//            System.out.println(output+"---"+System.currentTimeMillis());
//        }
        System.out.println("MyCallable-call()-start");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("MyCallable-call()-end");
    }
}
