package dispather.threadGroup.comm;

import com.alibaba.fastjson.JSONObject;
import dispather.threadGroup.dispatchManage.DispatchManager;
import dispather.threadGroup.dto.CommMsg;
import dispather.threadGroup.dto.TaskRequest;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * 车辆与B端通信线程
 */
public class CommRecCarThread extends CommRecThread {

    /**
     * 目前所拥有的线程
     */
    public static Map<Long, CommRecCarThread> sockets = new HashMap<>();

    @Override
    public void setKey(long key) {
        super.setKey(key);
        sockets.put(key, this);
    }

    public CommRecCarThread(Socket socket) {
        super(socket);
    }

    /**
     * 解析数据dto
     *
     * @param commMsg
     */
    @Override
    public void resolveMsgDto(CommMsg commMsg) {
        super.resolveMsgDto(commMsg);
        switch (commMsg.getKey()) {
            case "1"://任务请求
                logger.info("任务请求");
                DispatchManager.handleTaskRequest(this.getKey(), JSONObject.parseObject(commMsg.getContent().toString(),TaskRequest.class));
                break;
            default:
                break;
        }
    }


}
