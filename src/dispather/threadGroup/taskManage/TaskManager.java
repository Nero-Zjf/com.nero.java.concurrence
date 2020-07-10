package dispather.threadGroup.taskManage;

import dispather.threadGroup.task.domain.ChargeTask;
import dispather.threadGroup.task.domain.Task;

/**
 * 任务管理
 */
public class TaskManager {
    /**
     * 新建任务
     *
     * @param carId    车辆ID
     * @param taskType 车辆类型
     * @return 任务
     */
    public static Task createTask(long carId, int taskType) {
        Task task;
        switch (taskType) {
            case 0:
                task = new ChargeTask(carId);
                break;
            default:
                task = new Task(carId, taskType);
                break;
        }
        return task;
    }

    /**
     * 变更任务
     */
    public static void changeTask(Task task) {

    }

    /**
     * 中断任务
     *
     * @param task 任务
     */
    public static void interruptTask(Task task) {
        if (task != null) {
            task.setTaskState(3);
        }
    }

    /**
     * 结束任务
     *
     * @param task 任务
     */
    public static void closeTask(Task task) {
        if (task != null) {
            task.setTaskState(4);
        }
    }

    /**
     * 更新任务
     */
    public static void updateTask() {

    }

    /**
     * 绑定充电任务
     */
    public static void bindChargeTask(ChargeTask task, long chargePileId) {
        if (task != null) {
            task.setChargePileId(chargePileId);
        }
    }
}
