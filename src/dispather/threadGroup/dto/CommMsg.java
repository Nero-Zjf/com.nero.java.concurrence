package dispather.threadGroup.dto;

/**
 * 通信数据
 */
public class CommMsg {
    /**
     * 不同的key代表不同的通信内容
     */
    private String key;//1-任务请求
    /**
     * 数据内容(不同key不同的内容)
     */
    private Object Content;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getContent() {
        return Content;
    }

    public void setContent(Object content) {
        Content = content;
    }

    @Override
    public String toString() {
        return "{" +
                "\"key\":\"" + key + '\"' +
                ", \"Content\":" + Content +
                '}';
    }
}
