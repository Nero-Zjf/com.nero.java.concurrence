package dispather.threadGroup.comm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * 维护所有线程组
 */
public class CommManager {
    public static final Logger logger = LogManager.getLogger();

    public CommManager() throws InterruptedException {
        openListener();
    }

    /**
     * 车辆socket监听线程
     */
    private Thread commCarListenerThread;

    /**
     * 客户socket监听线程
     */
    private Thread commCustListenerThread;

    /**
     * 开始监听线程
     *
     * @throws InterruptedException
     */
    private void openListener() throws InterruptedException {
        //通信线程组开启
        this.commCarListenerThread = new Thread(new CommListenerThread<CommRecCarThread>(8081, CommRecCarThread.class));
        this.commCustListenerThread = new Thread(new CommListenerThread<CommRecCustThread>(8082, CommRecCustThread.class));
        this.commCarListenerThread.start();
        this.commCustListenerThread.start();
        logger.info("通信监听开启");
    }

    /**
     * 中断所有监听线程
     */
    public void interrupt() {
        this.commCarListenerThread.interrupt();
        this.commCustListenerThread.interrupt();
    }

    /**
     * 给车辆发送数据
     *
     * @param carId 车辆ID
     * @param msg 发送数据
     */
    public static void sendCarMsg(long carId, String msg) {
        CommRecThread crt = CommRecCarThread.sockets.get(carId);
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(crt.getSocket().getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        pw.println(msg);
        pw.flush();
    }

    /**
     * 给客户发送数据
     *
     * @param custId 客户ID
     * @param msg 发送数据
     */
    public static void sendCustMsg(long custId, String msg) {
         //预留接口
    }


}
