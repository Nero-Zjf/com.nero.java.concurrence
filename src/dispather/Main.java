package dispather;

import dispather.threadGroup.comm.CommManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    public static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws InterruptedException {
        CommManager commManage = new CommManager();
        logger.info("通信模块初始化");
        logger.info("是否退出？");
//        Scanner read = new Scanner(System.in);
        System.out.println("enter something");
//        if (read.nextLine().equals("Y")) {
//            logger.info("退出");
//        }
    }
}
