package nero;

public class HelloThread1  implements Runnable{

    Service service;

    public HelloThread1(Service service) {
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