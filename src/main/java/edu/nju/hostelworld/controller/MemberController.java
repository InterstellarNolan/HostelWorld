package edu.nju.hostelworld.controller;

import edu.nju.hostelworld.entity.Member;
import edu.nju.hostelworld.entity.User;
import edu.nju.hostelworld.service.MemberService;
import edu.nju.hostelworld.service.UserService;
import edu.nju.hostelworld.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    public String homePage(Model model, HttpServletRequest request) {
        OnLineUserVO userVO = (OnLineUserVO) request.getSession().getAttribute("userVO");
        User user = userService.getById(userVO.getId());
        Member member = memberService.getById(user.getUserid());

        model.addAttribute("member", member);
        model.addAttribute("user", user);
        double discount = Constants.VIP_LEVEL_TO_DISCOUNT(member.getLevel());
        model.addAttribute("discount", discount);
        return "memberHome";
    }

    @RequestMapping(value = "/charge", method = RequestMethod.GET)
    public String chargePage(Model model, HttpServletRequest request) {
        OnLineUserVO userVO = (OnLineUserVO) request.getSession().getAttribute("userVO");
        User user = userService.getById(userVO.getId());
        Member member = memberService.getById(user.getUserid());
        //System.out.println(member.getRealName());
        model.addAttribute("member", member);
        return "memberCharge";
    }

    @RequestMapping(value = "/charge", method = RequestMethod.POST)
    public String charge(ModelMap model, String amount, String score, String bankpassword, HttpServletRequest request) {
        double money = Double.parseDouble(amount);
        double sc = Double.parseDouble(score);
        OnLineUserVO userVO = (OnLineUserVO) request.getSession().getAttribute("userVO");
        User user = userService.getById(userVO.getId());
        Member member = memberService.getById(user.getUserid());
        if (money == 0 && sc == 0) {
            return "redirect:/member/charge";

        } else if (money != 0 && sc == 0) {
            //System.out.println(money);
            //System.out.println(member.getId());
            //System.out.println(bankpassword);

            memberService.charge(money, member.getId(), bankpassword);
            return "redirect:/member/charge";
        } else if (money == 0) {
            //System.out.println(sc);
            //System.out.println(member.getId());
            memberService.scoreToMoney(member.getId(), sc);
            return "redirect:/member/charge";
        } else {
            //System.out.println(money);
            //System.out.println(sc);
            //System.out.println(member.getId());
            //System.out.println(bankpassword);
            memberService.charge(money, member.getId(), bankpassword);
            memberService.scoreToMoney(member.getId(), sc);
            return "redirect:/member/charge";
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
        return "redirect:/member/edit";
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
            if (vo.isType())
                checkInbillVO.add(vo);
            else
                checkOutbillVO.add(vo);
        }
        model.addAttribute("checkInbillVO", checkInbillVO);
        model.addAttribute("checkOutbillVO", checkOutbillVO);
        return "memberAnalysis";
    }
}
