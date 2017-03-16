package vo;

import edu.nju.hostelworld.entity.PayBill;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/11.
 */


public class PayBillVO {
    private int id;
    private boolean counted;
    private double money;
    private String createDate;
    private String userRealName;
    private String idCard;
    private int hostelId;
    private int vipId;
    private int roomId;
    private String hostelName;
    private String hostelAddress;
    private String roomImg;
    private String roomName;
    private double roomPrice;

    public PayBillVO(PayBill payBillEntity) {
        this.id = payBillEntity.getId();
        this.counted = payBillEntity.getCounted();
        this.money = payBillEntity.getMoney();
        this.createDate = payBillEntity.getCreateDateStr();
        this.userRealName = payBillEntity.getUserRealName();
        this.idCard = payBillEntity.getIdCard();
        this.hostelId = payBillEntity.getHostel().getUserid();
        this.hostelAddress = payBillEntity.getHostel().getAddress();
        this.hostelName = payBillEntity.getHostel().getName();
        this.vipId = payBillEntity.getMember().getUserid();
        this.roomId = payBillEntity.getRoom().getId();
        this.roomImg = payBillEntity.getRoom().getImg();
        this.roomName = payBillEntity.getRoom().getName();
        this.roomPrice = payBillEntity.getRoom().getPrice();
    }

    public static List<PayBillVO> entityToVO(List<PayBill> bills) {
        List<PayBillVO> res = new ArrayList<PayBillVO>();
        for (PayBill bill : bills) {
            res.add(new PayBillVO(bill));
        }
        return res;
    }

    public int getId() {
        return id;
    }

    public boolean isCounted() {
        return counted;
    }

    public double getMoney() {
        return money;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public String getIdCard() {
        return idCard;
    }

    public int getHostelId() {
        return hostelId;
    }

    public int getVipId() {
        return vipId;
    }

    public int getRoomId() {
        return roomId;
    }

    public String getHostelName() {
        return hostelName;
    }

    public String getHostelAddress() {
        return hostelAddress;
    }

    public String getRoomImg() {
        return roomImg;
    }

    public String getRoomName() {
        return roomName;
    }

    public double getRoomPrice() {
        return roomPrice;
    }
}
