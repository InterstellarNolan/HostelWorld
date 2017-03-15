package edu.nju.hostelworld.service.bean;

import edu.nju.hostelworld.DAO.*;
import edu.nju.hostelworld.entity.*;
import edu.nju.hostelworld.service.HostelService;
import edu.nju.hostelworld.service.MemberService;
import edu.nju.hostelworld.util.RequestState;
import edu.nju.hostelworld.util.ResultMessage;
import vo.CheckInVO;
import vo.CheckOutVO;
import vo.PayVO;
import vo.HostelRoomVO;
import vo.HostelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static edu.nju.hostelworld.util.Constants.*;

//import static edu.nju.hostelworld.util.Constants.RATE_MONEY_TO_SCORE;

/**
 * Created by disinuo on 17/3/3.
 */
@Transactional
@Service
public class HostelServiceBean implements HostelService {

    @Override
    public ResultMessage init(int hostelId) {
        Hostel hostel = getById(hostelId);
        if (!hostel.getPermitted()) {//店还没通过审核
            List<RequestOpen> requests = requestDao.getOpenRequestByRestrictEqual("hostel", hostel);
            if (requests == null || requests.size() == 0) {//客栈没提交过申请，提醒
                return ResultMessage.REMIND_REQUEST;
            } else {
                for (RequestOpen req : requests) {//有一个拒绝的，就返回拒绝
                    if (req.getState().equals(RequestState.DENIED.toString())) {
                        return ResultMessage.REQUEST_DENIED;
                    }
                }//否则返回 等待中
                return ResultMessage.REQUEST_UNCHECKED;
            }
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage delete(int hostelId) {
        return null; //TODO delete
    }

    @Override
    public ResultMessage requestManager(int hostelId) {
        RequestOpen requestOpen = new RequestOpen();
        requestOpen.setHostel(getById(hostelId));
        try {
            requestDao.addOpenRequest(requestOpen);
            return ResultMessage.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMessage.FAILURE;
        }
    }

    @Override
    public Hostel getById(int hostelId) {
        return hostelDao.get(hostelId);
    }

    @Override
    public ResultMessage update(HostelVO hostelVO) {
        //TODO 这里有问题，Request表只能存当前存在的hostel。。
//        // 是否考虑新建一个存储hostel临时信息的表
//        RequestModify requestModify=new RequestModify();
//        requestModify.setHostelOriginal(getById(hostel.getId()));
//        requestModify.setHostelNew(hostel);
//        try {
//            requestDao.addModifyRequest(requestModify);
//        }catch (Exception e){
//            e.printStackTrace();
//            return ResultMessage.FAILURE;
//        }
        return ResultMessage.SUCCESS;
//        return hostelDao.update(hostel);
    }

    @Override
    public double enrollPay(PayVO payVO) {
        PayBill payBill = new PayBill();
        HostelRoom room = roomDao.get(payVO.getRoomId());
        double moneyToPay = payVO.getMoney();
        payBill.setRoom(room);
        payBill.setHostel(room.getHostel());
        payBill.setUserRealName(payVO.getUserRealName());
        payBill.setIdCard(payVO.getIdCard());
        payBill.setCreateDate(new Date().getTime());
        if (payVO.getVipId() != 0) {//顾客是会员
            Member vip = vipDao.get(payVO.getVipId());
            payBill.setMember(vip);
            //看会员级别~要打折的！
            int level = vip.getLevel();
            double discount = VIP_LEVEL_TO_DISCOUNT(level);
            moneyToPay *= discount;
            payBill.setMoney(moneyToPay);
            /*
             *消费要积分的！还要升级！
             */
            //会员算上这笔消费后的累计消费
            double vipPaidAll = vip.getMoneyPaid() + moneyToPay;
            vip.setMoneyPaid(vipPaidAll);
            vip.setScore(vip.getScore() + moneyToPay * RATE_MONEY_TO_SCORE);
            vip.setLevel(VIP_MONEY_TO_LEVEL(vipPaidAll));
            vipDao.update(vip);
        } else {//顾客不是会员，直接生成账单
            payBill.setMoney(moneyToPay);
        }
        try {
            payBillDao.add(payBill);
            Hostel hostel = room.getHostel();
            hostel.setMoneyUncounted(hostel.getMoneyUncounted() + moneyToPay);
            hostelDao.update(hostel);
            return moneyToPay;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public ResultMessage vipPay(int vipId, double money) {
        ResultMessage msg = vipService.payMoney(vipId, money);
        if (msg != ResultMessage.SUCCESS) {
            return msg;
        }
        return unVipPay(money);
    }

    @Override
    public ResultMessage unVipPay(double money) {
        User manager = userDao.get(MANAGER_ID);
        manager.setBankMoney(manager.getBankMoney() + money);
        return userDao.update(manager);
    }

    @Override
    public ResultMessage liveIn(CheckInVO liveInVO) {
        LiveBill liveBill = new LiveBill();
        if (liveInVO.getVipId() != 0) {
            Member vip = vipDao.get(liveInVO.getVipId());
            liveBill.setMember(vip);
        }
        HostelRoom room = roomDao.get(liveInVO.getRoomId());
        liveBill.setType(true);
        liveBill.setRoom(room);
        liveBill.setHostel(room.getHostel());
        liveBill.setIdCard(liveInVO.getIdCard());
        liveBill.setUserRealName(liveInVO.getUserRealName());
        liveBill.setDate(new Date().getTime());
        try {
            liveBillDao.add(liveBill);
            return ResultMessage.SUCCESS;
        } catch (Exception e) {
//            e.printStackTrace();
            return ResultMessage.FAILURE;
        }
    }

    @Override
    public ResultMessage depart(CheckOutVO liveOutVO) {
        LiveBill liveBill = new LiveBill();
        Member vip = vipDao.get(liveOutVO.getVipId());
        HostelRoom room = roomDao.get(liveOutVO.getRoomId());
        liveBill.setType(false);
        liveBill.setRoom(room);
        liveBill.setHostel(room.getHostel());
        liveBill.setMember(vip);
        liveBill.setIdCard(liveOutVO.getIdCard());
        liveBill.setUserRealName(liveOutVO.getUserRealName());
        liveBill.setDate(new Date().getTime());
        try {
            liveBillDao.add(liveBill);
            return ResultMessage.SUCCESS;
        } catch (Exception e) {
//            e.printStackTrace();
            return ResultMessage.FAILURE;
        }
    }

    @Override
    public ResultMessage addRoom(int hostelId, List<HostelRoomVO> roomVOs) {
        ResultMessage msg;
        for (HostelRoomVO roomVO : roomVOs) {
            msg = addRoom(hostelId, roomVO);
            if (msg == ResultMessage.FAILURE) {
                return ResultMessage.FAILURE;
            }
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage addRoom(int hostelId, HostelRoomVO roomVO) {
        HostelRoom room = new HostelRoom();
        room.setHostel(hostelDao.get(hostelId));
        room.setName(roomVO.getName());
        room.setPrice(roomVO.getPrice());
        room.setImg(roomVO.getImg());
        try {
            roomDao.add(room);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMessage.FAILURE;
        }
        return ResultMessage.SUCCESS;

    }

    @Override
    public ResultMessage updateRoom(int hostelId, HostelRoomVO roomVO) {
//        TODO updateRoom!!!!
        return null;
//        return roomDao.update(room);
    }

    @Override
    public List<BookBill> getAllBookBills(int hostelId) {
        return getById(hostelId).getBookBills();
    }

    @Override
    public List<PayBill> getAllPayBills(int hostelId) {

        return getById(hostelId).getPayBills();
    }

    @Override
    public double getIncome(int hostelId) {
        double total = 0;
        List<PayBill> payBills = getAllPayBills(hostelId);
        for (PayBill payBill : payBills) {
            total += payBill.getMoney();
        }
        return total;
    }

    @Override
    public List<LiveBill> getAllLiveBills(int hostelId) {
        return getById(hostelId).getLiveBills();
    }

    @Override
    public List<HostelRoom> getAllRooms(int hostelId) {
        return getById(hostelId).getRooms();
    }

    @Override
    public List<HostelRoom> getAllValidRooms(int hostelId) {
        Map map = new HashMap<String, Object>();
        map.put("hostel.id", hostelId);
        map.put("valid", true);
        return roomDao.getByRestrictEqual(map);
    }

    @Override
    public HostelRoom getRoomById(int roomId) {
        return roomDao.get(roomId);
    }

    @Override
    public List<Hostel> getAllPermittedHostels() {

        System.out.println("HERE TO SERVICE GET");
        return hostelDao.getByRestrictEqual("permitted", true);
    }

    @Override
    public List<PayBill> getAllUncountedPayBills(int hostelId) {
        Map map = new HashMap<String, Object>();
        map.put("hostel.id", hostelId);
        map.put("counted", false);
        return payBillDao.getByRestrictEqual(map);
    }

    //   ----------------------------------------
    @Autowired
    HostelDAO hostelDao;
    @Autowired
    HostelRoomDAO roomDao;
    @Autowired
    RequestDAO requestDao;
    @Autowired
    UserDAO userDao;
    @Autowired
    MemberDAO vipDao;
    @Autowired
    LiveBillDAO liveBillDao;
    @Autowired
    PayBillDAO payBillDao;
    @Autowired
    MemberService vipService;
}
