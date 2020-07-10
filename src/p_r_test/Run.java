package p_r_test;

public class Run {
    public static void main(String[] args) throws InterruptedException {
        String lock = new String();
        P p = new P(lock);
        C c = new C(lock);
        Thread[] threads_P = new Thread[5];
        for (int i = 0; i < threads_P.length; i++) {
            threads_P[i] = new ThreadP(p);
            threads_P[i].setName(""+(i+1));
            threads_P[i].start();
        }
        Thread[] threads_C = new Thread[5];
        for (int i = 0; i < threads_C.length; i++) {
            threads_C[i] = new ThreadC(c);
            threads_C[i].setName(""+(i+1));
            threads_C[i].start();
        }

        Thread.sleep(5000);
        Thread[] threadArray = new Thread[Thread.currentThread().getThreadGroup().activeCount()];
        Thread.currentThread().getThreadGroup().enumerate(threadArray);
        for (int i = 0; i < threadArray.length; i++) {
            System.out.println(threadArray[i].getName()+" "+threadArray[i].getState());
        }
    }
}
