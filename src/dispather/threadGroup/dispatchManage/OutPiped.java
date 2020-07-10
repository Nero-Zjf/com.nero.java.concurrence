package dispather.threadGroup.dispatchManage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.PipedOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 输出管道管理类(用于异步等待)
 */
public class OutPiped {
    public static final Logger logger = LogManager.getLogger();

    /**
     * 任务线程对应的管道流
     * @Key 车辆ID
     * @Value 管道输出流
     */
    private Map<Long, PipedOutputStream> taskOutPiped = new HashMap<>();

    /**
     * 向任务管道发送数据
     *
     * @param key 任务ID
     * @param msg
     */
    void sendTaskPipedMsg(long key,String msg) {
        try {
            logger.info("向管道发送数据");
            taskOutPiped.get(key).write(msg.getBytes());
            taskOutPiped.get(key).flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Map<Long, PipedOutputStream> getTaskOutPiped() {
        return taskOutPiped;
    }
}
