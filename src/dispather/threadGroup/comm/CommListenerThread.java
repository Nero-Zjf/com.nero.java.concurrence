package dispather.threadGroup.comm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 通信监听线程
 */
public class CommListenerThread<T extends CommRecThread> implements Runnable {
    public static final Logger logger = LogManager.getLogger();
    /**
     * 接收线程的Class
     */
    private Class RecThreadCls;
    /**
     * 监听端口
     */
    private int port;
    /**
     * 线程池
     */
    private ThreadPoolExecutor threadPool = new ThreadPoolExecutor(50, 100,
            120, TimeUnit.MINUTES, new SynchronousQueue<>());

    /**
     * socket监听端口
     *
     * @param port
     */
    public CommListenerThread(int port,Class<T> cls) {
        this.port = port;
        this.RecThreadCls = cls;
    }

    /**
     * 通信监听
     */
    private void commListen() {
        logger.info("开启socket服务端");
        //实例化服务器socket对象
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);

            //等待客户端连接
            logger.info("等待客户端连接");
            Socket socket = null;
            while ((socket = serverSocket.accept()) != null && !Thread.currentThread().isInterrupted()) {
                threadPool.execute(createRecThread(socket));
                logger.info("客户端已连接，开启监听线程");
            }
            logger.info("通信监听线程退出");
        } catch (IOException | NoSuchMethodException | InstantiationException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 线程执行方法
     */
    @Override
    public void run() {
        commListen();
    }

    /**
     * 创建接收数据线程
     * @param socket
     * @return
     */
    private T createRecThread(Socket socket)  throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //通过反射创建实例
       Constructor<T> constructor = RecThreadCls.getConstructor(Socket.class);
       return constructor.newInstance(socket);
    }

    /**
     * 关闭线程池
     */
    public void shutdownPool(){
        this.threadPool.shutdownNow();
    }


}
