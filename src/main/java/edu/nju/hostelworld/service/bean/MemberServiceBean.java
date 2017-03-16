package edu.nju.hostelworld.service.bean;

import edu.nju.hostelworld.DAO.BookBillDAO;
import edu.nju.hostelworld.DAO.HostelDAO;
import edu.nju.hostelworld.DAO.MemberDAO;
import edu.nju.hostelworld.DAO.UserDAO;
import edu.nju.hostelworld.entity.*;
import edu.nju.hostelworld.service.HostelService;
import edu.nju.hostelworld.service.MemberService;
import edu.nju.hostelworld.service.UserService;
import edu.nju.hostelworld.util.DateHandler;
import edu.nju.hostelworld.util.MemberState;
import edu.nju.hostelworld.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vo.BookVO;

import java.util.Date;
import java.util.List;

import static edu.nju.hostelworld.util.Constants.*;

/**
 * Created by Administrator on 17/3/3.
 */
@Transactional
@Service
public class MemberServiceBean implements MemberService {
    @Autowired
    MemberService memberService;

    @Override
    public void init(int memberId) {

        Member vip = getById(memberId);
        System.out.println(vip.getState());
        MemberState vipState = MemberState.strToMemberState(vip.getState());
        if (vipState == MemberState.UNACTIVATED || vipState == MemberState.STOP) {
            //未激活or停卡 不涉及根据时间改变状态
            return;
        } else {
            long today = new Date().getTime();
            if (vipState == MemberState.PAUSED) {
                //卡被暂停了。若时间超过一年 则变为stop
                long pauseDate = vip.getPauseDate();
                if (DateHandler.milliSecondToDay(today - pauseDate) >= DAY_OF_PAUSE_TO_STOP) {
                    //暂停超过？天
                    vip.setState(MemberState.STOP.toString());
                    vipDao.update(vip);
                }
            } else if (vipState == MemberState.NORMAL) {
                //卡是正常状态。检测激活时间
                long activateDate = vip.getActivateDate();
                double moneyLeft = vip.getMoneyLeft();
                double dayDifference = DateHandler.milliSecondToDay(today - activateDate);
                if (dayDifference >= DAY_OF_NORMAL_TO_PAUSE) {
                    if (moneyLeft < MONEY_LEAST) {
                        //激活超过?天,但不超过?1+?2天
                        //卡余额不足，状态变成暂停，并记录暂停时间
                        if (dayDifference <= (DAY_OF_NORMAL_TO_PAUSE + DAY_OF_PAUSE_TO_STOP)) {
                            vip.setState(MemberState.PAUSED.toString());
                            vip.setPauseDate(activateDate + DateHandler.dayToMilliSecond(1));
                        } else {//激活超过?1+?2天，卡余额不足，直接停卡！
                            vip.setState(MemberState.STOP.toString());
                        }
                    } else {//余额还足~ 更新激活日期为当前时间
                        vip.setActivateDate(new Date().getTime());
                    }
                    vipDao.update(vip);
                }
            }
        }

    }

    @Override
    public ResultMessage charge(double money, int memberId, String bankPassword) {
        //System.out.println("In VIPServiceBean--topUp");

        Member member = memberService.getById(memberId);
        int id = 0;
        List<User> list = userDao.getByRestrictEqual("userName", member.getRealName());
        if (list != null || list.size() != 0) {
            id = list.get(0).getId();
        }
        User user = userService.getById(id);
        System.out.println("VIP 充值金额" + money + " id:" + id + " 密码:" + bankPassword);
        //已停卡，则不能充值
        if (member.getState().equals(MemberState.STOP.toString())) {
            System.out.println("停卡");
            return ResultMessage.VIP_STATE_STOP;
        }
        //银行卡密码错误
        if (!user.getBankPassword().equals(bankPassword)) {
            System.out.println("密码错误");
            return ResultMessage.WRONG_PASSWORD;
        }
        //银行卡余额不足
        if (user.getBankMoney() < money) {
            System.out.println("钱不够");
            return ResultMessage.NOT_ENOUGH_MONEY;
        }
        //        ----真的可以开始充值啦！------
        ResultMessage msg = userService.changeBankMoneyMinus(id, money);
        member.setMoneyLeft(member.getMoneyLeft() + money);
        if (msg == ResultMessage.SUCCESS) {
            if (money >= MONEY_ACTIVATE) {//一次性交费大于？，需要的话去激活
                activate(member);
            }
            if (member.getMoneyLeft() >= MONEY_LEAST) {//累计交费大于？，需要的话去恢复
                restore(member);
            }
            return vipDao.update(member);
        } else {
            return ResultMessage.FAILURE;
        }
    }


    @Override
    public ResultMessage stop(int memberId) {
        Member vip = vipDao.load(memberId);
        System.out.println();
        System.out.println();
        System.out.println(vip.getState());
        if(vip.getState().equals("STOP")){
            return ResultMessage.VIP_STATE_STOP;
        }
        vip.setState(MemberState.STOP.toString());

        return vipDao.update(vip);
    }

    @Override
    public Member getById(int memberId) {
        return vipDao.get(memberId);
    }

    @Override
    public ResultMessage update(Member vip) {
        return vipDao.update(vip);
    }

    @Override
    public ResultMessage book(BookVO bookVO) {
        Member vip = getById(bookVO.getVipId());
        String vipState = vip.getState();
        if (vipState.equals(MemberState.STOP.toString())) {
            return ResultMessage.VIP_STATE_STOP;
        } else if (vipState.equals(MemberState.PAUSED.toString())) {
            return ResultMessage.VIP_STATE_PAUSED;
        } else if (vipState.equals(MemberState.UNACTIVATED.toString())) {
            return ResultMessage.VIP_STATE_UNACTIVATED;
        } else {//该会员有权利预订房间
            ResultMessage msg = payMoney(vip.getId(), MONEY_BOOK);
            if (msg == ResultMessage.NOT_ENOUGH_MONEY) {//会员卡余额不足，无法预订
                return msg;
            } else {//这次万事俱备！生成预订订单
                BookBill bookBill = new BookBill();
                HostelRoom room = hostelService.getRoomById(bookVO.getRoomId());
                bookBill.setMember(vip);
                bookBill.setRoom(room);
                bookBill.setHostel(room.getHostel());
                bookBill.setCreateDate(new Date().getTime());
                bookBill.setCheckInDate(DateHandler.strToLong(bookVO.getCheckInDate()));
                bookBill.setCheckOutDate(DateHandler.strToLong(bookVO.getCheckOutDate()));
                try {
                    bookBillDao.add(bookBill);
                    return ResultMessage.SUCCESS;
                } catch (Exception e) {
                    return ResultMessage.FAILURE;
                }
            }

        }

    }

    @Override
    public ResultMessage unbook(int memberId, int bookId) {
        BookBill bookBill = bookBillDao.get(bookId);
        long nowDate = new Date().getTime();
        if (bookBill.getMemberId() != memberId) {//只能取消预订自己的订单
            return ResultMessage.NO_AUTHORITY;
        } else if (nowDate >= bookBill.getCheckInDate()) {//超过了订单的入住时间
            return ResultMessage.LATE_TIME;
        } else {
            //退钱
            payMoney(memberId, -MONEY_BOOK);
            //使该预订订单失效！~~~
            bookBill.setValid(false);
            return bookBillDao.update(bookBill);
        }
    }

    @Override
    public List<BookBill> getAllBookBills(int memberId) {
        Member vip = getById(memberId);
        return vip.getBookBills();
    }

    @Override
    public List<PayBill> getAllPayBills(int memberId) {

        Member vip = getById(memberId);
        return vip.getPayBills();

    }

    @Override
    public List<LiveBill> getAllLiveBills(int memberId) {
        Member vip = getById(memberId);
        return vip.getLiveBills();
    }

    @Override
    public ResultMessage scoreToMoney(int memberId, double score) {
        Member vip = getById(memberId);
        double originalScore = vip.getScore();
        if (originalScore < score) {
            return ResultMessage.NOT_ENOUGH_SCORE;
        } else {
            double moneyGot = score * RATE_SCORE_TO_MONEY;
            vip.setMoneyLeft(vip.getMoneyLeft() + moneyGot);
            vip.setScore(originalScore - score);
            return vipDao.update(vip);
        }
    }

    @Override
    public List<Hostel> getAllPermittedHostels() {
        return hostelDao.getByRestrictEqual("permitted", false);
    }

    /**
     * 会员状态由【暂停】恢复到【正常】
     * 不会同步数据库！
     *
     * @param vip
     * @return
     */
    private ResultMessage restore(Member vip) {
        if (vip.getState().equals(MemberState.PAUSED.toString())) {
            vip.setState(MemberState.NORMAL.toString());
            vip.setActivateDate(new Date().getTime());
            return ResultMessage.SUCCESS;
        } else if (vip.getState().equals(MemberState.STOP.toString())) {
            //已停卡，不可恢复
            return ResultMessage.FAILURE;
        }
        //未激活不能【恢复】，正常无需恢复。
        return ResultMessage.FAILURE;
    }

    /**
     * 激活会员账号，会员卡状态由【未激活】改为【正常】
     * 不会同步数据库！
     *
     * @param vip
     * @return
     */
    private ResultMessage activate(Member vip) {
        if (vip.getState().equals(MemberState.UNACTIVATED.toString())) {
            vip.setState(MemberState.NORMAL.toString());
            vip.setActivateDate((new Date()).getTime());
            return ResultMessage.SUCCESS;
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage payMoney(int memberId, double money) {
        Member vip = vipDao.get(memberId);
        double moneyLeft = vip.getMoneyLeft();
        if (moneyLeft < money) {//会员卡余额不足，提示用户充钱~
            return ResultMessage.NOT_ENOUGH_MONEY;
        } else {
            vip.setMoneyLeft(moneyLeft - money);
            vip.setMoneyPaid(vip.getMoneyPaid() + money);
            return vipDao.update(vip);
        }

    }

    @Autowired
    MemberDAO vipDao;
    @Autowired
    HostelDAO hostelDao;
    @Autowired
    UserDAO userDao;
    @Autowired
    BookBillDAO bookBillDao;
    @Autowired
    UserService userService;
    @Autowired
    HostelService hostelService;
}
