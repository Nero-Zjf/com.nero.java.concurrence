package dispather.threadGroup.task.domain;

import java.util.Date;

/**
 * 任务基类
 */
public class Task {
    public Task(long carId,int taskType){
        this.carId = carId;
        this.taskType = taskType;
    }
    /**
     * 任务ID
     */
    private long taskId;
    /**
     * 车辆ID
     */
    private long carId;
    /**
     * 任务类型(0-充电任务 1- 2- 3- 4-)
     */
    private int taskType;
    /**
     * 任务状态(0-等待 1-开始确认 2-执行 3-中断 4-结束 5-任务取消)
     */
    private int taskState = 0;
    /**
     * 任务开始时间
     */
    private Date startTime;
    /**
     * 任务结束时间
     */
    private Date endTime;
    /**
     * 任务开始位置
     */
    private Position startPosition;
    /**
     * 任务目标位置
     */
    private Position endPosition;

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public int getTaskType() {
        return taskType;
    }

    public void setTaskType(int taskType) {
        this.taskType = taskType;
    }

    public int getTaskState() {
        return taskState;
    }

    public void setTaskState(int taskState) {
        this.taskState = taskState;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Position getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(Position startPosition) {
        this.startPosition = startPosition;
    }

    public Position getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(Position endPosition) {
        this.endPosition = endPosition;
    }
}
