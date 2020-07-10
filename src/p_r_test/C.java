package p_r_test;

public class C {
    private String lock;

    public C(String lock) {
        this.lock = lock;
    }

    public void getValue() {
        try {
            synchronized (lock) {
                if (ValueObject.value.equals("")) {
                    System.out.println("消费者" + Thread.currentThread().getName() + " waiting 了 *");
                    lock.wait();
                }

                System.out.println("消费者" + Thread.currentThread().getName() + " RUNNABLE 了 ");
                System.out.println("get的值是"+ ValueObject.value);
                ValueObject.value = "";
                lock.notify();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
