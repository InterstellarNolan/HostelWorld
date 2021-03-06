package vo;

import edu.nju.hostelworld.entity.BookBill;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Administrator on 2017/3/11.
 */
public class BookBillVO {
    private int id;
    private String liveInDate;
    private String createDate;
    private int hostelId;
    private int vipId;
    private int roomId;
    private boolean valid;

    private String hostelName;
    private String hostelAddress;
    private String roomImg;
    private String roomName;
    private double roomPrice;

    public static List<BookBillVO> entityToVO(List<BookBill> bills) {
        List<BookBillVO> res = new ArrayList<BookBillVO>();
        for (BookBill bill : bills) {
            res.add(new BookBillVO(bill));
        }
        return res;
    }

    public BookBillVO(BookBill bookBillEntity) {
        this.id = bookBillEntity.getId();
        this.liveInDate = bookBillEntity.getCheckInDateStr();
        this.createDate = bookBillEntity.getCreateDateStr();
        this.hostelId = bookBillEntity.getHostel().getUserid();
        this.hostelAddress = bookBillEntity.getHostel().getAddress();
        this.hostelName = bookBillEntity.getHostel().getName();
        this.vipId = bookBillEntity.getMember().getUserid();
        this.roomId = bookBillEntity.getRoom().getId();
        this.roomImg = bookBillEntity.getRoom().getImg();
        this.roomName = bookBillEntity.getRoom().getName();
        this.roomPrice = bookBillEntity.getRoom().getPrice();
        this.valid = bookBillEntity.isValid();
    }

    public int getId() {
        return id;
    }

    public String getLiveInDate() {
        return liveInDate;
    }

    public String getCreateDate() {
        return createDate;
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

    public boolean isValid() {
        return valid;
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
