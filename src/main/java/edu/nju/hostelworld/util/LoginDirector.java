package edu.nju.hostelworld.util;

import org.springframework.web.servlet.ModelAndView;
import vo.OnLineUserVO;

/**
 * Created by Administrator on 2017/3/11.
 */
public class LoginDirector {
    public static ModelAndView roleToHomePage(String role){
        switch (role){
            case "member":
                return new ModelAndView("redirect:member/hostels");
            case "hostel":return new ModelAndView("error/404");
            case "manager":return new ModelAndView("manager/index");
            default: return new ModelAndView("error/404");
        }
    }

    /**
     * 未登录则返回登录界面的ModelAndView
     * 已登录则返回null
     * @param user
     * @return
     */
    public static ModelAndView checkLoggedIn(OnLineUserVO user){
        if(user==null) return new ModelAndView("redirect:/login");
        else return null;
    }
}
