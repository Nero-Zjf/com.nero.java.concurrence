package threadPool;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<Boolean> {
    @Override
    public Boolean call() throws Exception {
        System.out.println("MyCallable-call()-start");
        Thread.sleep(2000);
        System.out.println("MyCallable-call()-end");
        return true;
    }
}
