package threadgroup;

public class MyThread implements Runnable{
    String output = "";
    public MyThread(String output) {
        this.output = output;
    }
    @Override
    public void run() {

        while (true) {
            if (Thread.interrupted()) {
                return;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(output);

        }
    }
}
