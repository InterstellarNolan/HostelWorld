/**
 * Created by Administrator on 17/3/3.
 */
package edu.nju.hostelworld.controller;
import edu.nju.hostelworld.util.LoginDirector;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import vo.OnLineUserVO;

@Controller
@RequestMapping("/")
@SessionAttributes(types = {OnLineUserVO.class})
public class MainController {
    @RequestMapping(value = "/" ,method= RequestMethod.GET)
    public ModelAndView showHomePage(@ModelAttribute("user") OnLineUserVO onLineUserVO){
        try {
            System.out.print("用户已登录");
            return LoginDirector.roleToHomePage(onLineUserVO.getType());

        }catch (Exception e){
            return new ModelAndView("redirect:/login");
        }
    }

}