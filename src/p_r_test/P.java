package p_r_test;

public class P {
    private String lock;

    public P(String lock) {
        this.lock = lock;
    }

    public void setValue() {
        try {
            synchronized (lock) {
                if (!ValueObject.value.equals("")) {
                    System.out.println("生产者" + Thread.currentThread().getName() + " waiting 了 *");
                    lock.wait();
                }

                System.out.println("生产者" + Thread.currentThread().getName() + " RUNNABLE 了 ");
                String value = System.currentTimeMillis() + "_" + System.nanoTime();
                System.out.println("set的值是" + value);
                ValueObject.value = value;
                lock.notify();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
