package vo;

import edu.nju.hostelworld.entity.HostelRoom;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/11.
 */
public class HostelRoomVO {
    private double price = 299;
    private String img;
    private String name;
    private int roomId;

    public HostelRoomVO(double price, String img, String name, int roomId) {
        this.price = price;
        this.img = img;
        this.name = name;
        this.roomId = roomId;
    }

    public HostelRoomVO(HostelRoom room) {
        this.price = room.getPrice();
        this.img = room.getImg();
        this.name = room.getName();
        this.roomId = room.getId();
    }

    public static List<HostelRoomVO> entityToVO(List<HostelRoom> hostels) {
        List<HostelRoomVO> res = new ArrayList<HostelRoomVO>();
        for (HostelRoom hostel : hostels) {
            //System.out.println(hostel.getUserid());
            res.add(new HostelRoomVO((hostel)));
        }
        return res;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }
}
