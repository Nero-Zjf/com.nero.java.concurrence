package dispather.threadGroup.comm;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class CommRecCustThread extends CommRecThread {
    /**
     * 该接收线程目前所拥有的sockets
     */
    public static Map<Long, CommRecThread> sockets = new HashMap<>();

    @Override
    public void setKey(long key) {
        super.setKey(key);
        sockets.put(key, this);
    }

    public CommRecCustThread(Socket socket) {
        super(socket);
    }
}
