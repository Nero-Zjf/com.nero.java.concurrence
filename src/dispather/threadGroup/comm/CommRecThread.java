package dispather.threadGroup.comm;

import com.alibaba.fastjson.JSONObject;
import dispather.threadGroup.dto.CommMsg;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 通信接收线程
 */
public abstract class CommRecThread implements Runnable {
    public static final Logger logger = LogManager.getLogger();

    /**
     * 当前接收线程监控的socket
     */
    private Socket socket;

    /**
     * 当前线程socket的唯一标识
     */
    private long key;

    public CommRecThread(Socket socket) {
        this.socket = socket;
    }

    /**
     * 接收
     *
     */
    private void dataReceive() {
        try {
            //获取socket输入流，并读取客户端信息
            InputStream is = null;
            is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String info = null;
            logger.info("等待接收数据");
            while ((info = br.readLine()) != null) {
                logger.info("接收数据：" + info);
                if (Thread.interrupted()) {
                    logger.info("通信接收线程中断");
                    return;
                }

                resolveMsg(info);
            }

            logger.info("通信接收线程退出");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        dataReceive();
    }

    /**
     * 处理接收的信息
     *
     * @param msg
     */
    public  void resolveMsg(String msg) {
        CommMsg commMsg = JSONObject.parseObject(msg, CommMsg.class);
        if (commMsg == null) {
            logger.info("数据解析失败");
            return;
        }
        if (commMsg.getKey().equals("0")) {
            setKey(Long.parseLong(commMsg.getContent().toString()));
        }

        resolveMsgDto(commMsg);
    }

    /**
     * 处理接收的消息对象
     *
     * @param commMsg
     */
    void resolveMsgDto(CommMsg commMsg) {
        logger.info("解析接收的dto对象");
    }
    public Socket getSocket() {
        return socket;
    }

    public Long getKey() {
        return key;
    }

    public void setKey(long key) {
        logger.info("设置当前接收线程的唯一标识：" + key);
        this.key = key;
    }
}
