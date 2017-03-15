package edu.nju.hostelworld.controller;

import edu.nju.hostelworld.entity.Hostel;
import edu.nju.hostelworld.entity.Member;
import edu.nju.hostelworld.entity.User;
import edu.nju.hostelworld.service.HostelService;
import edu.nju.hostelworld.service.MemberService;
import edu.nju.hostelworld.service.UserService;
import edu.nju.hostelworld.service.bean.HostelServiceBean;
import edu.nju.hostelworld.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import vo.HostelVO;
import vo.MemberVO;
import vo.OnLineUserVO;
import vo.UserVO;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/3/11.
 */
@Controller
@SessionAttributes(types = {OnLineUserVO.class})
public class LoginController {
    @Autowired
    UserService userService;
    @Autowired
    MemberService vipService;
    @Autowired
    HostelService hostelService = new HostelServiceBean();

    @RequestMapping(value = "/logout")
    public ModelAndView logout(HttpSession session) {
        session.removeAttribute("vip");
        session.removeAttribute("user");
        return new ModelAndView("login", "command", new UserVO());
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView showLoginPage(ModelMap model) {
        return new ModelAndView("login", "user", new UserVO());
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView handleLoginRequest(HttpSession session, HttpServletResponse response, UserVO userVO) {
        String name = userVO.getUserName();
        String password = userVO.getPassword();
        ResultMessage msg = userService.checkPassword(name, password);
        if (msg != ResultMessage.SUCCESS) {
            return new ModelAndView("/loginFail");
        } else {//验证通过
            User user = userService.login(name, password);
            OnLineUserVO onLineUserVO = new OnLineUserVO(user.getId(), user.getUserName(), user.getType());
            session.setAttribute("userVO", onLineUserVO);
            if (user.getType().equals("member")) {
                vipService.init(user.getId());
            }
            return roleToHomePage(session, onLineUserVO);
        }
    }

    public ModelAndView roleToHomePage(HttpSession session, OnLineUserVO user) {
        User use = userService.getById(user.getId());
        int id = use.getUserid();
        switch (user.getType()) {
            case "member":
                vipService.init(id);
                Member vip = vipService.getById(id);
                MemberVO vipVO = new MemberVO(vip);
                session.setAttribute("memberVO", vipVO);
                return new ModelAndView("redirect:/member/home");
            case "hostel":
                ResultMessage initMsg = hostelService.init(id);
                Hostel hostel = hostelService.getById(id);
                HostelVO hostelVO = new HostelVO(hostel);
                session.setAttribute("hostelVO", hostelVO);
                return new ModelAndView("redirect:/hostel/home");
            case "manager":
                return new ModelAndView("manager/index");
            default:
                return new ModelAndView("404");
        }
    }

}