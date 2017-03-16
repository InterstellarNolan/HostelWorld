package edu.nju.hostelworld.controller;

import edu.nju.hostelworld.entity.*;
import edu.nju.hostelworld.service.HostelService;
import edu.nju.hostelworld.service.MemberService;
import edu.nju.hostelworld.service.UserService;
import edu.nju.hostelworld.util.Constants;
import edu.nju.hostelworld.util.MemberState;
import edu.nju.hostelworld.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import vo.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/14.
 */
@Controller
@RequestMapping(value = "/member")
public class MemberController {
    @Autowired
    MemberService memberService;
    @Autowired
    UserService userService;
    @Autowired
    HostelService hostelService;

    @RequestMapping(value = "/home")
    public ModelAndView homePage(Model model, HttpServletRequest request) {
        OnLineUserVO userVO = (OnLineUserVO) request.getSession().getAttribute("userVO");
        User user = userService.getById(userVO.getId());
        Member member = memberService.getById(user.getUserid());
        model.addAttribute("member", member);
        model.addAttribute("user", user);
        double discount = Constants.VIP_LEVEL_TO_DISCOUNT(member.getLevel());
        model.addAttribute("discount", discount);
        return new ModelAndView("memberHome");
    }

    @RequestMapping(value = "/charge", method = RequestMethod.GET)
    public ModelAndView chargePage(Model model, HttpServletRequest request) {
        OnLineUserVO userVO = (OnLineUserVO) request.getSession().getAttribute("userVO");
        User user = userService.getById(userVO.getId());
        Member member = memberService.getById(user.getUserid());
        if (member.getState().equals(MemberState.STOP.toString())) {
            //System.out.println(member.getRealName());
            return new ModelAndView("/error/memberStop");
        } else {
            model.addAttribute("member", member);
            return new ModelAndView("memberCharge");
        }

    }

    @RequestMapping(value = "/charge", method = RequestMethod.POST)
    public ModelAndView charge(ModelMap model, String amount, String score, String bankpassword, HttpServletRequest request) {
        double money = Double.parseDouble(amount);
        double sc = Double.parseDouble(score);
        OnLineUserVO userVO = (OnLineUserVO) request.getSession().getAttribute("userVO");
        User user = userService.getById(userVO.getId());
        Member member = memberService.getById(user.getUserid());
        ResultMessage r1, r2;
        if (money == 0 && sc == 0) {
            model.addAttribute("message", r1 = ResultMessage.NOT_CHANGE);
            model.addAttribute("member", member);
            return new ModelAndView("memberCharge");
        } else if (money != 0 && sc == 0) {
            r1 = memberService.charge(money, member.getId(), bankpassword);
            Member member1 = memberService.getById(user.getUserid());
            model.addAttribute("member", member1);
            model.addAttribute("message", r1.toShow());
            return new ModelAndView("memberCharge");
        } else if (money == 0) {
            r1 = memberService.scoreToMoney(member.getId(), sc);
            Member member2 = memberService.getById(user.getUserid());
            model.addAttribute("member", member2);
            model.addAttribute("message", r1.toShow());
            return new ModelAndView("memberCharge");
        } else {
            r1 = memberService.charge(money, member.getId(), bankpassword);
            r2 = memberService.scoreToMoney(member.getId(), sc);
            Member member3 = memberService.getById(user.getUserid());
            model.addAttribute("member", member3);
            model.addAttribute("message", r1.toShow() + "  " + r2.toShow());
            return new ModelAndView("memberCharge");
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView editPage(Model model, HttpServletRequest request) {
        OnLineUserVO userVO = (OnLineUserVO) request.getSession().getAttribute("userVO");
        User user = userService.getById(userVO.getId());
        Member member = memberService.getById(user.getUserid());
        model.addAttribute("member", member);
        model.addAttribute("user", user);
        return new ModelAndView("memberEdit");
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView edit(ModelMap model, String name, String bankcard, HttpServletRequest request) {
        OnLineUserVO userVO = (OnLineUserVO) request.getSession().getAttribute("userVO");
        User user = userService.getById(userVO.getId());
        Member member = memberService.getById(user.getUserid());
        member.setRealName(name);
        user.setBankId(bankcard);
        ResultMessage r1 = memberService.update(member);
        ResultMessage r2 = userService.update(user);
        model.addAttribute("member", member);
        model.addAttribute("user", user);
        model.addAttribute("message1", "更改姓名" + r1.toShow());
        model.addAttribute("message2", "更改银行账号" + r2.toShow());
        return new ModelAndView("memberEdit");
    }

    @RequestMapping(value = "/analysis")
    public ModelAndView analysisPage(Model model, HttpServletRequest request) {
        OnLineUserVO userVO = (OnLineUserVO) request.getSession().getAttribute("userVO");
        User user = userService.getById(userVO.getId());
        Member member = memberService.getById(user.getUserid());
        List<BookBill> bookbilllist = memberService.getAllBookBills(member.getId());
        //model.addAttribute("booklistState", bookbillstatelist);
        model.addAttribute("bookbillVO", BookBillVO.entityToVO(bookbilllist));
        model.addAttribute("paybillVO", PayBillVO.entityToVO(memberService.getAllPayBills(member.getId())));
        List<LiveBillVO> liveBillVOList = LiveBillVO.entityToVO(memberService.getAllLiveBills(member.getId()));
        List<LiveBillVO> checkInbillVO = new ArrayList<LiveBillVO>(), checkOutbillVO = new ArrayList<LiveBillVO>();
        for (int i = 0; i < liveBillVOList.size(); i++) {
            LiveBillVO vo = liveBillVOList.get(i);
            if (vo.isType()) checkInbillVO.add(vo);
            else checkOutbillVO.add(vo);
        }
        model.addAttribute("checkInbillVO", checkInbillVO);
        model.addAttribute("checkOutbillVO", checkOutbillVO);
        return new ModelAndView("memberAnalysis");
    }

    @RequestMapping(value = "/hostels")
    public ModelAndView hostelsList(Model model, HttpServletRequest request) {
        OnLineUserVO userVO = (OnLineUserVO) request.getSession().getAttribute("userVO");
        User user = userService.getById(userVO.getId());
        Member member = memberService.getById(user.getUserid());
        System.out.println(member.getUserid());
        List<Hostel> list = hostelService.getAllPermittedHostels();

        List<HostelVO> list1 = HostelVO.entityToVO(list);

        for (int i = 0; i < list1.size(); i++) {
            System.out.println(list1.get(i).getId());
         }
        if (member.getState().equals(MemberState.STOP.toString())) {
            //System.out.println(member.getRealName());
            return new ModelAndView("/error/memberStop");
        } else if (member.getState().equals(MemberState.PAUSED.toString())) {
            return new ModelAndView("/error/memberPaused");
        } else if (member.getState().equals(MemberState.UNACTIVATED.toString())) {
            return new ModelAndView("/error/memberUnactivated");
        } else {
            model.addAttribute("hostellist", list1);
            model.addAttribute("userVO", userVO);
            return new ModelAndView("memberHostels");
        }
    }

    @RequestMapping(value = "/unbook")
    public ModelAndView unbookPage(Model model, HttpServletRequest request) {
        OnLineUserVO userVO = (OnLineUserVO) request.getSession().getAttribute("userVO");
        User user = userService.getById(userVO.getId());
        Member member = memberService.getById(user.getUserid());
        List<BookBillVO> list = BookBillVO.entityToVO(memberService.getAllBookBills(member.getId()));
        List<BookBillVO> truelist = new ArrayList<BookBillVO>();
        for (int i = 0; i < list.size(); i++) {
            BookBillVO temp = list.get(i);
            if (temp.isValid()) {
                truelist.add(temp);
            }
        }
        if (member.getState().equals(MemberState.STOP.toString())) {
            return new ModelAndView("/error/memberStop");
        } else if (member.getState().equals(MemberState.PAUSED.toString())) {
            return new ModelAndView("/error/memberPaused");
        } else if (member.getState().equals(MemberState.UNACTIVATED.toString())) {
            return new ModelAndView("/error/memberUnactivated");
        } else {
            model.addAttribute("truelist", truelist);
            return new ModelAndView("memberUnbook");
        }
    }

    @RequestMapping(value = "/unbook/{id}")
    public ModelAndView unbook(ModelMap model, @PathVariable("id") String id, HttpServletRequest request) {
        OnLineUserVO userVO = (OnLineUserVO) request.getSession().getAttribute("userVO");
        User user = userService.getById(userVO.getId());
        Member member = memberService.getById(user.getUserid());
        ResultMessage rmsg=memberService.unbook(member.getId(), Integer.parseInt(id));
        model.addAttribute("message",rmsg.toShow());
        return new ModelAndView("redirect:/member/unbook");
    }

    @RequestMapping(value = "/room/{id}")
    public ModelAndView room(Model model, @PathVariable("id") String id, HttpServletRequest request) {
        OnLineUserVO userVO = (OnLineUserVO) request.getSession().getAttribute("userVO");
        User user = userService.getById(userVO.getId());
        Member member = memberService.getById(user.getUserid());
        model.addAttribute("member", member);
        HostelRoom room = hostelService.getRoomById(Integer.parseInt(id));
        model.addAttribute("room", room);
        return new ModelAndView("memberroomHome");
    }

    @RequestMapping(value = "/book")
    public ModelAndView book(Model model,  String roomid, String indate, String outdate, HttpServletRequest request) {
        OnLineUserVO userVO = (OnLineUserVO) request.getSession().getAttribute("userVO");
        User user = userService.getById(userVO.getId());
        Member member = memberService.getById(user.getUserid());
        BookVO bookVO = new BookVO(indate, outdate, member.getId(), Integer.parseInt(roomid));
        ResultMessage rmsg=memberService.book(bookVO);
        model.addAttribute("message",rmsg.toShow());
        return new ModelAndView("redirect:/member/hostels");
    }

    @RequestMapping(value = "/stop")
    public ModelAndView stop(ModelMap model, HttpServletRequest request) {
        OnLineUserVO userVO = (OnLineUserVO) request.getSession().getAttribute("userVO");
        User user = userService.getById(userVO.getId());
        Member member = memberService.getById(user.getUserid());
        ResultMessage rmsg = memberService.stop(member.getId());
        if (rmsg.equals(ResultMessage.VIP_STATE_STOP)) {
            return new ModelAndView("error/aleardStop");
        }
        return new ModelAndView("common/memberStop");
    }
}
