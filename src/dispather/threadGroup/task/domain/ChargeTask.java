package dispather.threadGroup.task.domain;

/**
 * 充电任务
 */
public class ChargeTask extends Task {
    public ChargeTask(long carId) {
        super(carId, 0);
    }

    /**
     * 充电桩ID
     */
    private long chargePileId;

    /**
     * 充电状态(0-等待接入 1-正在充电 2-充电完毕)
     */
    private int chargeState;

    public long getChargePileId() {
        return chargePileId;
    }

    public void setChargePileId(long chargePileId) {
        this.chargePileId = chargePileId;
    }
}
