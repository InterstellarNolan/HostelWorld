package edu.nju.hostelworld.controller;

import edu.nju.hostelworld.entity.Member;
import edu.nju.hostelworld.entity.User;
import edu.nju.hostelworld.service.MemberService;
import edu.nju.hostelworld.service.UserService;
import edu.nju.hostelworld.util.Constants;
import edu.nju.hostelworld.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import vo.BookBillVO;
import vo.LiveBillVO;
import vo.OnLineUserVO;
import vo.PayBillVO;

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
        //System.out.println(member.getRealName());
        model.addAttribute("member", member);
        return new ModelAndView("memberCharge");
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
            return new ModelAndView("memberCharge");

        } else if (money != 0 && sc == 0) {


            r1 = memberService.charge(money, member.getId(), bankpassword);
            model.addAttribute("message", r1.toShow());
            return new ModelAndView("memberCharge");
        } else if (money == 0) {

            r1 = memberService.scoreToMoney(member.getId(), sc);
            model.addAttribute("message", r1.toShow());
            return new ModelAndView("memberCharge");
        } else {

            r1 = memberService.charge(money, member.getId(), bankpassword);
            r2 = memberService.scoreToMoney(member.getId(), sc);
            model.addAttribute("message", r1.toShow() + "  " + r2.toShow());
            return new ModelAndView("memberCharge");
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editPage(Model model, HttpServletRequest request) {
        OnLineUserVO userVO = (OnLineUserVO) request.getSession().getAttribute("userVO");
        User user = userService.getById(userVO.getId());
        Member member = memberService.getById(user.getUserid());
        model.addAttribute("member", member);
        model.addAttribute("user", user);
        return "memberEdit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(String name, String bankcard, HttpServletRequest request) {
        OnLineUserVO userVO = (OnLineUserVO) request.getSession().getAttribute("userVO");
        User user = userService.getById(userVO.getId());
        Member member = memberService.getById(user.getUserid());
        member.setRealName(name);
        user.setBankId(bankcard);
        memberService.update(member);
        userService.update(user);
        return "memberEdit";
    }

    @RequestMapping(value = "/analysis")
    public String analysisPage(Model model, HttpServletRequest request) {
        OnLineUserVO userVO = (OnLineUserVO) request.getSession().getAttribute("userVO");
        User user = userService.getById(userVO.getId());
        Member member = memberService.getById(user.getUserid());
        model.addAttribute("bookbillVO", BookBillVO.entityToVO(memberService.getAllBookBills(member.getId())));
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
        return "memberAnalysis";
    }
}
