package p_r_test;

public class ThreadP extends Thread {
    private P p;

    public ThreadP(P p) {
        this.p = p;
    }
    @Override
    public void run() {
        while (true) {
            p.setValue();
        }
    }
}
