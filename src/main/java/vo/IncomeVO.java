package vo;

/**
 * Created by Administrator on 17/3/15.
 */
public class IncomeVO {
    private int hostelId;
    private double value = 0;
    private String name;

    public int getHostelId() {
        return hostelId;
    }

    public void setHostelId(int hostelId) {
        this.hostelId = hostelId;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
