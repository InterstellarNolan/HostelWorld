package vo;

/**
 * Created by disinuo on 17/3/11.
 */
public class RoomVO_input {
    private double price=299;
    private String img = "无";
    private String name;

    public RoomVO_input(double price, String img, String name) {
        this.price = price;
        this.img = img;
        this.name = name;
    }

    public RoomVO_input() {
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
}
