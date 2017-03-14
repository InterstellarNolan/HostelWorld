package edu.nju.hostelworld.controller;

import edu.nju.hostelworld.entity.Hostel;
import edu.nju.hostelworld.entity.Manager;
import edu.nju.hostelworld.service.MemberService;
import edu.nju.hostelworld.service.UserService;
import edu.nju.hostelworld.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vo.UserVO;


/**
 * Created by Administrator on 2017/3/11.
 */
@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    UserService userService;
    @Autowired
    MemberService vipService;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView showRegisterPage(ModelMap model) {
        return new ModelAndView("register", "user", new UserVO());
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ModelAndView handleRegisterRequest(@ModelAttribute("user") UserVO userVO, RedirectAttributes attr, ModelMap model) {
        ResultMessage rmsg = userService.register(userVO.getUserName(), userVO.getPassword());
        if (rmsg == ResultMessage.FAILURE) {
            System.out.println(rmsg.toShow());
            return new ModelAndView("error/404");
        } else if (rmsg == ResultMessage.DUPLICATE_NAME) {
            System.out.println(rmsg.toShow());
            return new ModelAndView("error/duplicate");
        } else {
            model.addAttribute("message", rmsg.toShow());
            return new ModelAndView("register");
        }
    }
    @RequestMapping(value = "/hostel", method = RequestMethod.GET)
    public ModelAndView showHostelRegisterPage(ModelMap model) {
        return new ModelAndView("registerHostel", "user", new UserVO());
    }
    @RequestMapping(value = "/hostel", method = RequestMethod.POST)
    public ModelAndView handleHostelRegisterRequest(@ModelAttribute("user") UserVO userVO, RedirectAttributes attr, ModelMap model) {
        ResultMessage rmsg = userService.register(Hostel.class,userVO.getUserName(), userVO.getPassword());
        if (rmsg == ResultMessage.FAILURE) {
            System.out.println(rmsg.toShow());
            return new ModelAndView("error/404");
        } else if (rmsg == ResultMessage.DUPLICATE_NAME) {
            System.out.println(rmsg.toShow());
            return new ModelAndView("error/duplicate");
        } else {
            model.addAttribute("message", rmsg.toShow());
            return new ModelAndView("registerHostel");
        }
    }
    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public ModelAndView showManagerRegisterPage(ModelMap model) {
        return new ModelAndView("registerManager", "user", new UserVO());
    }
    @RequestMapping(value = "/manager", method = RequestMethod.POST)
    public ModelAndView handleManagerRegisterRequest(@ModelAttribute("user") UserVO userVO, RedirectAttributes attr, ModelMap model) {
        ResultMessage rmsg = userService.register(Manager.class,userVO.getUserName(), userVO.getPassword());
        if (rmsg == ResultMessage.FAILURE) {
            System.out.println(rmsg.toShow());
            return new ModelAndView("error/404");
        } else if (rmsg == ResultMessage.DUPLICATE_NAME) {
            System.out.println(rmsg.toShow());
            return new ModelAndView("error/duplicate");
        } else {
            model.addAttribute("message", rmsg.toShow());
            return new ModelAndView("registerManager");
        }
    }
}