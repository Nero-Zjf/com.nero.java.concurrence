package dispather.threadGroup.dto;

/**
 * 任务请求POJO
 */
public class TaskRequest {
    /**
     * 请求类型(0-请求任务 1-任务确认 2-任务中断 3-任务结束 4-任务更新)
     */
    private int requestType;
    /**
     * 任务类型
     */
    private int taskType;

    /**
     * 任务ID
     */
    private long taskId;

    /**
     * 请求的具体内容
     */
    private Object content;

    public int getRequestType() {
        return requestType;
    }

    public void setRequestType(int requestType) {
        this.requestType = requestType;
    }

    public int getTaskType() {
        return taskType;
    }

    public void setTaskType(int taskType) {
        this.taskType = taskType;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
