package vo;

import edu.nju.hostelworld.entity.RequestOpen;
import edu.nju.hostelworld.util.RequestState;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 17/3/12.
 */

public class RequestOpenVO {
    private int id;


    private int hostel_lookid;
    private int hostel_id;
    private String hostel_img;
    private String hostel_phone;
    private String hostel_address;
    private String hostel_name;

    private RequestState state;

    public RequestOpenVO(RequestOpen request) {
        this.hostel_id = request.getHostel().getId();
        this.id = request.getId();
        this.hostel_lookid = request.getHostel().getUserid();
        this.hostel_img = request.getHostel().getImg();
        this.hostel_phone = request.getHostel().getPhone();
        this.hostel_address = request.getHostel().getAddress();
        this.hostel_name = request.getHostel().getName();
    }

    public static List<RequestOpenVO> entityToVO(List<RequestOpen> requests) {
        List<RequestOpenVO> res = new ArrayList<RequestOpenVO>();
        for (RequestOpen request : requests) {
            res.add(new RequestOpenVO(request));
        }
        return res;
    }

    public int getId() {
        return id;
    }

    public String getHostel_img() {
        return hostel_img;
    }

    public String getHostel_phone() {
        return hostel_phone;
    }

    public String getHostel_address() {
        return hostel_address;
    }

    public String getHostel_name() {
        return hostel_name;
    }

    public RequestState getState() {
        return state;
    }

    public int getHostel_id() {
        return hostel_id;
    }

    public int getHostel_lookid() {
        return hostel_lookid;
    }
}