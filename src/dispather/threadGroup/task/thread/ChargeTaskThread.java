package dispather.threadGroup.task.thread;

import dispather.threadGroup.comm.CommManager;
import dispather.threadGroup.task.domain.ChargeTask;

import java.io.PipedOutputStream;

/**
 * 充电任务线程
 */
public class ChargeTaskThread extends TaskThread implements Runnable {

    public ChargeTaskThread(ChargeTask chargeTask, PipedOutputStream pipedOut) {
        super(chargeTask, pipedOut);
    }

    /**
     * 执行任务
     */
    void doTask(){
        while (true) {
            String result;
            switch (task.getTaskState()) {
                case 0://等待
                    logger.info("任务状态：等待");
                    return;
                case 1://开始确认
                    logger.info("任务状态：开始确认");
                    try {
                        Thread.sleep(20000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    CommManager.sendCarMsg(task.getCarId(), "1");

                    waitMsg();
                    break;
                case 2://执行
                    logger.info("任务状态：执行");
                    result = waitMsg();

                    break;
                case 3://中断
                    logger.info("任务状态：中断");
                    return;
                case 4://结束
                    logger.info("任务状态：结束");
                    return;
                case 5://取消
                    logger.info("任务状态：取消");
                    return;
                default:
                    return;
            }
        }
    }

    @Override
    public void run() {
       doTask();
    }
}
