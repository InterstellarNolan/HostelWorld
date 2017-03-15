package edu.nju.hostelworld.controller;

import edu.nju.hostelworld.entity.Hostel;
import edu.nju.hostelworld.entity.User;
import edu.nju.hostelworld.service.HostelService;
import edu.nju.hostelworld.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import vo.OnLineUserVO;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/3/15.
 */
@Controller
@RequestMapping(value = "/hostel")
public class HostelController {
    @Autowired
    HostelService hostelService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/home")
    public ModelAndView homePage(Model model, HttpServletRequest request) {
        OnLineUserVO userVO = (OnLineUserVO) request.getSession().getAttribute("userVO");
        User user = userService.getById(userVO.getId());
        Hostel hostel = hostelService.getById(user.getUserid());
        model.addAttribute("hostel", hostel);
        model.addAttribute("user", user);
        model.addAttribute("roomnumber", hostelService.getAllRooms(hostel.getId()).size());
        return new ModelAndView("hostelHome");
    }
}
