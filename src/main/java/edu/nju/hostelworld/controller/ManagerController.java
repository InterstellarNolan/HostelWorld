package edu.nju.hostelworld.controller;

import edu.nju.hostelworld.entity.*;
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
        model.addAttribute("uservo", user);
        return new ModelAndView("HostelManagerHome");
    }

    @RequestMapping(value = "requestOpen", method = RequestMethod.GET)
    public ModelAndView requestOpenPage(Model model, HttpServletRequest request) {
        List<RequestOpen> list = managerService.getOpenRequests();
        List<RequestOpenVO> VO = RequestOpenVO.entityToVO(list);
        model.addAttribute("Openlist", VO);
        return new ModelAndView("HostelManagerOpen");
    }

    @RequestMapping(value = "requestOpen/{id}", method = RequestMethod.POST)
    public ModelAndView requestOpen(@PathVariable("id") String id, Model model, HttpServletRequest request, String state) {
        ResultMessage rmsg = managerService.updateOpenRequest(Integer.parseInt(id), state);
        List<RequestOpen> list = managerService.getOpenRequests();
        model.addAttribute("message", rmsg.toShow());
        RequestOpen open = managerService.getOpen(Integer.parseInt(id));
        Hostel hostelid = open.getHostel();
        HostelVO vo = new HostelVO(hostelid);
        model.addAttribute("hostel", vo);
        return new ModelAndView("HostelManagerOpen");
    }

    @RequestMapping(value = "requestOpenDetail/{id}")
    public ModelAndView requestOpenDetail(@PathVariable("id") String id, Model model, HttpServletRequest request) {
        RequestOpen open = managerService.getOpen(Integer.parseInt(id));
        Hostel hostelid = open.getHostel();
        model.addAttribute("Openlist", open);
        model.addAttribute("hostel", hostelid);
        return new ModelAndView("HostelManagerOpenDetail");
    }

    @RequestMapping(value = "requestModify", method = RequestMethod.GET)
    public ModelAndView requestModifyPage(Model model, HttpServletRequest request) {
        List<RequestModify> list = managerService.getModifyRequests();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("size is " + list.size());
        List<RequestModifyVO> VO = RequestModifyVO.entityToVO(list);
        model.addAttribute("Modifylist", VO);
        return new ModelAndView("HostelManagerModify");
    }

    @RequestMapping(value = "requestModify/{id}", method = RequestMethod.POST)
    public ModelAndView requestModify(@PathVariable("id") String id, Model model, HttpServletRequest request, String state) {
        ResultMessage rmsg = managerService.updateModifyRequest(Integer.parseInt(id), state);
        List<RequestModify> list = managerService.getModifyRequests();
        model.addAttribute("message", rmsg.toShow());
        RequestModify open = managerService.getModify(Integer.parseInt(id));
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("size is " + open.getId());
        //Hostel hostelid = open.getHostelOriginal();
        //HostelVO vo = new HostelVO(hostelid);
        //model.addAttribute("hostel", vo);
        return new ModelAndView("HostelManagerModify");
    }

    @RequestMapping(value = "requestModifyDetail/{id}")
    public ModelAndView requestModifyDetail(@PathVariable("id") String id, Model model, HttpServletRequest request) {
        RequestModify open = managerService.getModify(Integer.parseInt(id));
        //Hostel hostelid = open.getHostelOriginal();
        RequestModifyVO vo = new RequestModifyVO(open);
        model.addAttribute("Modifylist", vo);
        //model.addAttribute("hostel", hostelid);
        return new ModelAndView("HostelManagerModifyDetail");
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public ModelAndView countPage(Model model, HttpServletRequest request) {
        OnLineUserVO userVO = (OnLineUserVO) request.getSession().getAttribute("userVO");
        User user = userService.getById(userVO.getId());
        Manager manager = managerService.getById(user.getUserid());
        model.addAttribute("IMmanager", manager);
        return new ModelAndView("HostelManagerCount");
    }

    @RequestMapping(value = "/count", method = RequestMethod.POST)
    public ModelAndView countMoney(Model model, HttpServletRequest request, String password) {
        OnLineUserVO userVO = (OnLineUserVO) request.getSession().getAttribute("userVO");
        User user = userService.getById(userVO.getId());
        Manager manager = managerService.getById(user.getUserid());
        model.addAttribute("IMmanager", manager);
        System.out.println(manager.getId() + "      aaaaaaaa    " + password);
        ResultMessage rmsg = managerService.count(manager.getId(), password);
        model.addAttribute("message", rmsg.toShow());
        return new ModelAndView("HostelManagerCount");
    }
}
