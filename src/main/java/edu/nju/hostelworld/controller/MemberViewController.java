package edu.nju.hostelworld.controller;

import edu.nju.hostelworld.DAO.UserDAO;
import edu.nju.hostelworld.entity.User;
import edu.nju.hostelworld.service.HostelService;
import edu.nju.hostelworld.service.MemberService;
import edu.nju.hostelworld.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import vo.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2017/3/13.
 */
@Controller
@SessionAttributes(types = {OnLineUserVO.class})
@RequestMapping("/member")
public class MemberViewController {
    @Autowired
    MemberService memberService;
    @Autowired
    HostelService hostelService;
    @Autowired
    UserDAO userDao;
    @Autowired
    UserService userService;


    @RequestMapping(value = "/hostels")
    public ModelAndView showHostels(@ModelAttribute("user") OnLineUserVO user, Model model, HttpServletRequest request) {
        List<HostelVO> list = HostelVO.entityToVO(memberService.getAllPermittedHostels());
        model.addAttribute("hostelList", list);
        return new ModelAndView("member/hostelListPage");
    }

    @RequestMapping(value = "/rooms")
    public ModelAndView showValidRooms() {
        return new ModelAndView("member/hostelDetailPage");
    }

    @RequestMapping(value = "/bookList")
    public ModelAndView showBookList(@ModelAttribute("user") OnLineUserVO user, Model model, HttpServletRequest request) {
        int id = user.getId();
        User use = userService.getById(id);
        int memberid = use.getUserid();
        List<BookBillVO> list = BookBillVO.entityToVO(memberService.getAllBookBills(memberid));
        model.addAttribute("bookbilllist", list);
        return new ModelAndView("member/bookListPage");
    }

    @RequestMapping(value = "/payList")
    public ModelAndView showPayList(@ModelAttribute("user") OnLineUserVO user, Model model, HttpServletRequest request) {
        int id = user.getId();
        User use = userService.getById(id);
        int memberid = use.getUserid();
        List<PayBillVO> list = PayBillVO.entityToVO(memberService.getAllPayBills(memberid));
        model.addAttribute("paybilllist", list);
        return new ModelAndView("member/payListPage");
    }

    @RequestMapping(value = "/liveList")
    public ModelAndView showLiveList(@ModelAttribute("user") OnLineUserVO user, Model model, HttpServletRequest request) {
        int id = user.getId();
        User use = userService.getById(id);
        int memberid = use.getUserid();
        List<LiveBillVO> list = LiveBillVO.entityToVO(memberService.getAllLiveBills(memberid));
        model.addAttribute("livebilllist", list);
        return new ModelAndView("member/liveListPage");
    }

    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public ModelAndView showBookPage() {
        return new ModelAndView("member/bookPage");
    }


    @RequestMapping(value = "/charge", method = RequestMethod.GET)
    public ModelAndView showTopUpPage() {
        return new ModelAndView("member/chargePage");
    }


    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public ModelAndView showModifyInfoPage() {
        return new ModelAndView("member/modifyInfoPage");
    }

    @RequestMapping(value = "/convert", method = RequestMethod.GET)
    public ModelAndView showConvertScoreToMoneyPage() {
        return new ModelAndView("member/convertScorePage");
    }
}
