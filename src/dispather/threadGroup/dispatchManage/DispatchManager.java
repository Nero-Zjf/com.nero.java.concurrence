package dispather.threadGroup.dispatchManage;

import dispather.threadGroup.dto.TaskRequest;
import dispather.threadGroup.task.domain.ChargeTask;
import dispather.threadGroup.task.domain.Task;
import dispather.threadGroup.task.thread.ChargeTaskThread;
import dispather.threadGroup.taskManage.TaskManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.PipedOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 调度管理
 */
public class DispatchManager {
    public static final Logger logger = LogManager.getLogger();

    /**
     * 任务列表
     * @Long 任务ID
     * @Task 任务
     */
    public static Map<Long, Task> taskMap = new HashMap<>();

    /**
     * 用于异步等待通知的管道流
     */
    private static OutPiped outPiped = new OutPiped();

    /**
     * 处理任务请求
     */
    public static void handleTaskRequest(long carId, TaskRequest taskRequest) {
        logger.info("处理任务请求");
        switch (taskRequest.getRequestType()) {
            case 0://申请任务
                createTask(carId, taskRequest.getTaskType());
                break;
            case 1://任务确认
               confirmTask(taskRequest.getTaskId(), (Boolean) taskRequest.getContent());
                break;
            case 2://任务中断
                interruptTask(taskRequest.getTaskId());
                break;
            case 3://任务结束
                endTask(taskRequest.getTaskId());
                break;
            case 4://任务更新
                updateTask(taskRequest.getTaskId());
                break;
            default:
                break;
        }
    }

    /**
     * 申请任务
     *
     * @param carId 车辆ID
     * @param taskType 任务类型
     */
    private static void createTask(long carId, int taskType) {
        logger.info("申请任务");
        Task task = isTaskExist(carId, taskType);
        if (task == null) {//任务是否存在
            task = TaskManager.createTask(carId, taskType);
            taskMap.put(1L, task);
        }
        switch (taskType) {//任务类型
            case 0://充电任务
                TaskManager.bindChargeTask((ChargeTask) task, 1);//绑定充电桩
                task.setTaskState(1);
                //新建管道流
                PipedOutputStream pipedOut = new PipedOutputStream();
                outPiped.getTaskOutPiped().put(carId, pipedOut);
                //新建任务线程
                new Thread(new ChargeTaskThread((ChargeTask) task, pipedOut)).start();
                break;
            default:
                break;
        }
    }

    /**
     * 确认任务
     * @param taskId 任务ID
     * @param isConfirm 是否确认
     */
    private static void confirmTask(long taskId,boolean isConfirm) {
        if (isConfirm) {
            logger.info("任务确认-是");
            getTaskById(taskId).setTaskState(2);
        } else {
            logger.info("任务确认-否");
            getTaskById(taskId).setTaskState(5);
        }

        outPiped.sendTaskPipedMsg(getTaskById(taskId).getCarId(), "true\n");
    }

    /**
     * 中断任务
     * @param taskId 任务ID
     */
    private static void interruptTask(long taskId) {
        logger.info("任务中断");
        getTaskById(taskId).setTaskState(3);
        outPiped.sendTaskPipedMsg(getTaskById(taskId).getCarId(), "true\n");
    }

    /**
     * 结束任务
     * @param taskId 任务ID
     */
    private static void endTask(long taskId) {
        logger.info("任务结束");
        getTaskById(taskId).setTaskState(4);
        outPiped.sendTaskPipedMsg(getTaskById(taskId).getCarId(), "true\n");
    }

    /**
     * 更新任务
     * @param taskId 任务ID
     */
    private static void updateTask(long taskId) {
        logger.info("任务更新");
        outPiped.sendTaskPipedMsg(getTaskById(taskId).getCarId(), "true\n");
    }

    /**
     * 判断同一辆车是否已存在同类型的任务
     *
     * @param carId 车辆ID
     * @param taskType 任务类型
     * @return
     */
    private static Task isTaskExist(long carId, int taskType) {
        for (Task task : taskMap.values()) {
            if (task.getCarId() == carId && task.getTaskType() == taskType) {
                return task;
            }
        }

        return null;
    }

    /**
     * 根据任务ID获取任务
     *
     * @param taskId
     * @return
     */
    public static Task getTaskById(long taskId) {
        return taskMap.get(taskId);
    }
}
