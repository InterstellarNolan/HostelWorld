package vo;

import edu.nju.hostelworld.entity.LiveBill;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/3/11.
 */


public class LiveBillVO {
    private int id;
    //true代表入店，false代表离店
    private boolean type;
    private String userRealName;
    private String idCard;
    private String date;
    private int hostelId;
    private int vipId;
    private int roomId;
    private String hostelName;
    private String hostelAddress;
    private String roomImg;
    private String roomName;
    private double roomPrice;

    public LiveBillVO(LiveBill liveBillEntity) {
        this.id = liveBillEntity.getId();
        this.type = liveBillEntity.getType();
        this.userRealName = liveBillEntity.getUserRealName();
        this.idCard = liveBillEntity.getIdCard();
        this.date = liveBillEntity.getDateStr();
        this.hostelId = liveBillEntity.getHostel().getUserid();
        this.hostelAddress = liveBillEntity.getHostel().getAddress();
        this.hostelName = liveBillEntity.getHostel().getName();
        if (liveBillEntity.getMember() == null) {
            this.vipId = 0;
        } else {
            this.vipId = liveBillEntity.getMember().getUserid();
        }
        this.roomId = liveBillEntity.getRoom().getId();
        this.roomImg = liveBillEntity.getRoom().getImg();
        this.roomName = liveBillEntity.getRoom().getName();
        this.roomPrice = liveBillEntity.getRoom().getPrice();
    }

    public static List<LiveBillVO> entityToVO(List<LiveBill> bills) {
        List<LiveBillVO> res = new ArrayList<LiveBillVO>();
        for (LiveBill bill : bills) {
            res.add(new LiveBillVO((bill)));
        }
        return res;
    }

    public int getId() {
        return id;
    }

    public boolean isType() {
        return type;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public String getIdCard() {
        return idCard;
    }

    public String getDate() {
        return date;
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
