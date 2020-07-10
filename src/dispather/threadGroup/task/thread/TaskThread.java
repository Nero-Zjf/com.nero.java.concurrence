package dispather.threadGroup.task.thread;

import dispather.threadGroup.task.domain.Task;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * 任务线程抽象类
 */
public abstract class TaskThread {
    public static final Logger logger = LogManager.getLogger();

    /**
     * 当前线程执行的任务
     */
     Task task;

    /**
     * 管道输入流(用于阻塞等待)
     */
    PipedInputStream pipedIn;

    public TaskThread(Task task, PipedOutputStream pipedOut){
        logger.info("充电任务线程初始化");
        this.task = task;
        try {
            this.pipedIn = new PipedInputStream(pipedOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 阻塞获取通信接收线程消息
     *
     * @return
     */
     String waitMsg() {
        byte[] byteArray = new byte[64];
        StringBuilder sb = new StringBuilder();
        try {
            logger.info("任务线程-异步等待");
            int readLength;
            while ((readLength = pipedIn.read(byteArray)) != -1) {//异步等待
                boolean isEnd = false;
                for (int i = 0; i < byteArray.length; i++) {
                    if (byteArray[i] == 10) {//换行
                        isEnd = true;
                        byteArray[i] = 0;
                        readLength--;
                        break;
                    }
                }
                sb.append(new String(byteArray,0,readLength));
                if (isEnd) {
                    break;
                }
            }
            logger.info("任务线程-异步等待返回信息：" + sb.toString());
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
