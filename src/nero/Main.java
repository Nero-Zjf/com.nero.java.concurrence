package nero;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Service service  = new Service();
        HelloThread helloThread = new HelloThread(service);
        HelloThread1 helloThread1 = new HelloThread1(service);
        Thread threada = new Thread(helloThread,"A");
        Thread threadb = new Thread(helloThread1,"B");
        threadb.start();
        threada.start();
    }
}
