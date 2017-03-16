package edu.nju.hostelworld.controller;

import edu.nju.hostelworld.entity.Hostel;
import edu.nju.hostelworld.entity.Manager;
import edu.nju.hostelworld.entity.RequestOpen;
import edu.nju.hostelworld.entity.User;
import edu.nju.hostelworld.service.HostelService;
import edu.nju.hostelworld.service.ManagerService;
import edu.nju.hostelworld.service.UserService;
import edu.nju.hostelworld.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import vo.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/16.
 */
@Controller
@RequestMapping(value = "/HostelManager")
public class ManagerController {
    @Autowired
    ManagerService managerService;
    @Autowired
    HostelService hostelService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "home")
    public ModelAndView homePage(Model model, HttpServletRequest request) {
        OnLineUserVO userVO = (OnLineUserVO) request.getSession().getAttribute("userVO");
        User user = userService.getById(userVO.getId());
        Manager manager = managerService.getById(user.getUserid());
        List<LiveInNumVO> liveInNumVOList = managerService.getLiveInNums();
        List<IncomeVO> incomeVOList = managerService.getHostelIncomes();
        int num = 0;
        double income = 0;
        if (liveInNumVOList.size() != 0 && !liveInNumVOList.equals(null)) {
            for (int i = 0; i < liveInNumVOList.size(); i++) {
                num += liveInNumVOList.get(i).getY();
            }
            model.addAttribute("num", String.valueOf(num));
            model.addAttribute("liveIn", liveInNumVOList);
        }
        if (incomeVOList.size() != 0 && !incomeVOList.equals(null)) {
            for (int j = 0; j < incomeVOList.size(); j++) {
                income += incomeVOList.get(j).getValue();
            }
            model.addAttribute("income", String.valueOf(income));
            model.addAttribute("Income", incomeVOList);
        }
        //model.addAttribute("incomeList", incomeVOList);
        //model.addAttribute("liveInNumList", liveInNumVOList);
        model.addAttribute("managerboss", manager);
        return new ModelAndView("HostelManagerHome");
    }

    @RequestMapping(value = "requestOpen", method = RequestMethod.GET)
    public ModelAndView requestOpenPage(Model model, HttpServletRequest request) {
        List<RequestOpen> requestOpenList = managerService.getOpenRequests();
        List<RequestOpen> list = new ArrayList<RequestOpen>();
        for (int i = 0; i < requestOpenList.size(); i++) {
            RequestOpen open = requestOpenList.get(i);
            if (open.getState().equals("UNCHECK")) {
                list.add(open);
            }
        }
        //System.out.println();
        //System.out.println();
        //System.out.println();
        //System.out.println(list.size());
        List<RequestOpenVO> VO = RequestOpenVO.entityToVO(list);
        model.addAttribute("Openlist", VO);
        return new ModelAndView("HostelManagerOpen");
    }

    @RequestMapping(value = "requestOpen/{id}", method = RequestMethod.POST)
    public ModelAndView requestOpen(@PathVariable("id") String id, Model model, HttpServletRequest request, String state) {
        ResultMessage rmsg = managerService.updateOpenRequest(Integer.parseInt(id), state);
        List<RequestOpen> requestOpenList = managerService.getOpenRequests();
        List<RequestOpen> list = new ArrayList<RequestOpen>();
        for (int i = 0; i < requestOpenList.size(); i++) {
            RequestOpen open = requestOpenList.get(i);
            if (open.getState().equals("unchecked")) {
                list.add(open);
            }
        }
        List<RequestOpenVO> VO = RequestOpenVO.entityToVO(list);
        model.addAttribute("Openlist", VO);
        model.addAttribute("message", rmsg.toShow());
        Hostel hostel = hostelService.getHostel(Integer.parseInt(id));
        HostelVO vo = new HostelVO(hostel);
        model.addAttribute("hostel", vo);
        return new ModelAndView("HostelManagerOpen");
    }

    @RequestMapping(value = "requestOpenDetail/{id}")
    public ModelAndView requestOpenDetail(@PathVariable("id") String id, Model model, HttpServletRequest request, String state) {
        Hostel hostel = hostelService.getHostel(Integer.parseInt(id));
        HostelVO vo = new HostelVO(hostel);
        model.addAttribute("hostel", vo);
        return new ModelAndView("HostelManagerOpenDetail");
    }
}
