package nero;

public class HelloThread implements Runnable {

    Service service;

    public HelloThread(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        try {
            service.service1();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
