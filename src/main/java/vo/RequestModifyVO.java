package vo;

import edu.nju.hostelworld.entity.RequestModify;
import edu.nju.hostelworld.util.RequestState;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 17/3/12.
 */

public class RequestModifyVO {
    private int id;
    private int hostelId;


    private int hostelLookId;
    private String img_original;
    private String phone_original;
    private String address_original;
    private String name_original;

    private String img_new;
    private String phone_new;
    private String address_new;
    private String name_new;

    private RequestState state;

    public RequestModifyVO(RequestModify requestModify) {
        this.id = requestModify.getId();
        this.hostelLookId = requestModify.getHostelOriginal().getUserid();
        this.hostelId = requestModify.getHostelOriginal().getId();
        this.img_original = requestModify.getHostelOriginal().getImg();
        this.phone_original = requestModify.getHostelOriginal().getPhone();
        this.address_original = requestModify.getHostelOriginal().getAddress();
        this.name_original = requestModify.getHostelOriginal().getName();

        this.img_new = requestModify.getNewImg();
        this.phone_new = requestModify.getNewPhone();
        this.address_new = requestModify.getNewAddress();
        this.name_new = requestModify.getNewName();
        this.state = RequestState.strToRequestState(requestModify.getState());
    }

    public static List<RequestModifyVO> entityToVO(List<RequestModify> requests) {
        List<RequestModifyVO> res = new ArrayList<RequestModifyVO>();
        for (RequestModify request : requests) {
            res.add(new RequestModifyVO(request));
        }
        return res;
    }

    public int getId() {
        return id;
    }

    public int getHostelId() {
        return hostelId;
    }

    public String getImg_original() {
        return img_original;
    }

    public String getPhone_original() {
        return phone_original;
    }

    public String getAddress_original() {
        return address_original;
    }

    public String getName_original() {
        return name_original;
    }

    public String getImg_new() {
        return img_new;
    }

    public String getPhone_new() {
        return phone_new;
    }

    public String getAddress_new() {
        return address_new;
    }

    public String getName_new() {
        return name_new;
    }

    public RequestState getState() {
        return state;
    }

    public int getHostelLookId() {
        return hostelLookId;
    }
}
