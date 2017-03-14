package vo;

/**
 *
 */
public class BookVO {
    private String checkInDate;
    private String checkOutDate;
    private int vipId;
    private int roomId;

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String liveInDate) {
        this.checkInDate = liveInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public int getVipId() {
        return vipId;
    }

    public void setVipId(int vipId) {
        this.vipId = vipId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public BookVO(String liveInDate, String checkOutDate, int vipId, int roomId) {
        this.checkInDate = liveInDate;
        this.checkOutDate = checkOutDate;
        this.vipId = vipId;
        this.roomId = roomId;
    }
}
