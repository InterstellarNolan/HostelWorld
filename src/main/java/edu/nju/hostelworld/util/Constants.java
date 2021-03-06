package edu.nju.hostelworld.util;

/**
 * Created by Administrator on 2017/3/11.
 */
public class Constants {
    public static final String ROLE_VIP = "vip";
    public static final String ROLE_HOSTEL = "hostel";
    public static final String ROLE_MANAGER = "manager";

    public static final int MANAGER_ID = 666;
    /**
     * 激活`DAY_OF_NORMAL_TO_PAUSE`天后，会员卡余额最小值，低于此，卡会被暂停
     * 暂停的时候（没有超过一年），一旦一次性充值大于此，卡会被复原
     */
    public static final double MONEY_LEAST = 100;
    /**
     * 一次性充值此值以上会激活会员卡
     */
    public static final double MONEY_ACTIVATE = 1000;
    /**
     * 预订扣费
     */
    public static final double MONEY_BOOK = 20;
    /**
     * 会员卡结算佣金
     */
    public static final double MANAGER_DISCOUNT = 0.98;

    /**
     * 积分与消费金额的转换比例
     * 即 1积分可兑换？钱
     */
    public static double RATE_SCORE_TO_MONEY = 0.01;
    /**
     * 消费金额与积分的转换比例
     * 即 1元钱可兑换?积分
     */
    public static double RATE_MONEY_TO_SCORE = 10;
    /**
     * 会员从激活开始，经过?天后 余额不足`MONEY_LEAST`会自动暂停
     */
    public static int DAY_OF_NORMAL_TO_PAUSE = 365;
    /**
     * 会员从暂停开始，经过?天后 余额不足`MONEY_LEAST`会自动暂停
     */
    public static int DAY_OF_PAUSE_TO_STOP = 365;

    /**
     * 会员等级与消费折扣的转换
     */
    public static final double VIP_LEVEL_TO_DISCOUNT(int level) {
        if (level <= 2) {
            return 0.95;
        } else if (level <= 4) {
            return 0.85;
        } else if (level <= 7) {
            return 0.8;
        } else {
            return 0.75;
        }
    }

    /**
     * 会员累计消费金额与等级的转换
     */
    public static final int VIP_MONEY_TO_LEVEL(double money) {
        if (money <= 100) {
            return 0;
        } else if (money <= 300) {
            return 1;
        } else if (money <= 500) {
            return 2;
        } else if (money <= 800) {
            return 3;
        } else if (money <= 1000) {
            return 4;
        } else if (money <= 1500) {
            return 5;
        } else if (money <= 1800) {
            return 6;
        } else if (money <= 5000) {
            return 7;
        } else {
            return 8;
        }
    }
}
