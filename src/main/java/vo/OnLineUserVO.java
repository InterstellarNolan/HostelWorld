package vo;

/**
 * Created by Administrator on 2017/3/11.
 */
public class OnLineUserVO {
    private int id;
    private String userName;
    private String type;

    public OnLineUserVO(int id, String userName, String type) {
        this.id = id;
        this.userName = userName;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public OnLineUserVO(){}
}
