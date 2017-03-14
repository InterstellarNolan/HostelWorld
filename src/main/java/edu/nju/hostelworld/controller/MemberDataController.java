package edu.nju.hostelworld.controller;

import edu.nju.hostelworld.entity.User;
import edu.nju.hostelworld.service.MemberService;
import edu.nju.hostelworld.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import vo.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2017/3/13.
 */
@Controller
@SessionAttributes(types = {MemberVO.class, OnLineUserVO.class})
@RequestMapping(value = "/data/member")//,produces = "text/html;charset=UTF-8")
@ResponseBody
public class MemberDataController {
    @Autowired
    MemberService memberService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/getBookList")
    public List<BookBillVO> getBookList(HttpSession session) {
        OnLineUserVO user = (OnLineUserVO) session.getAttribute("user");
        int id = user.getId();
        User use = userService.getById(id);
        int member_id = use.getUserid();
        return BookBillVO.entityToVO(memberService.getAllBookBills(member_id));
    }

    @RequestMapping(value = "/getHostelList")
    public List<HostelVO> getHostelList() {
        return HostelVO.entityToVO(memberService.getAllPermittedHostels());
    }

    @RequestMapping(value = "/getLiveList")
    public List<LiveBillVO> getLiveList(HttpSession session) {
        OnLineUserVO user = (OnLineUserVO) session.getAttribute("user");
        int id = user.getId();
        User use = userService.getById(id);
        int member_id = use.getUserid();
        return LiveBillVO.entityToVO(memberService.getAllLiveBills(member_id));
    }

    @RequestMapping(value = "/getPayList")
    public List<PayBillVO> getPayList(HttpSession session) {
        OnLineUserVO user = (OnLineUserVO) session.getAttribute("user");
        int id = user.getId();
        User use = userService.getById(id);
        int member_id = use.getUserid();
        return PayBillVO.entityToVO(memberService.getAllPayBills(member_id));
    }

    @RequestMapping(value = "/getInfo")
    public MemberVO getVipInfo(HttpSession session) {
        OnLineUserVO user = (OnLineUserVO) session.getAttribute("user");
        int id = user.getId();
        User use = userService.getById(id);
        int hostel_id = use.getUserid();
        return new MemberVO(memberService.getById(hostel_id));
    }
}
