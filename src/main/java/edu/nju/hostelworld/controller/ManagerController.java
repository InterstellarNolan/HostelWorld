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
        return new ModelAndView("HostelManagerHome");
    }

    @RequestMapping(value = "requestOpen", method = RequestMethod.GET)
    public ModelAndView requestOpenPage(Model model, HttpServletRequest request) {
        List<RequestOpen> list = managerService.getOpenRequests();


        List<RequestOpenVO> VO = RequestOpenVO.entityToVO(list);
        //System.out.println();
        //System.out.println();
        //System.out.println();
        //System.out.println("hostelid"+VO.get(0).getHostel_id()+"   hostel  look  id"+VO.get(0).getHostel_lookid());
        model.addAttribute("Openlist", VO);
        return new ModelAndView("HostelManagerOpen");
    }

    @RequestMapping(value = "requestOpen/{id}", method = RequestMethod.POST)
    public ModelAndView requestOpen(@PathVariable("id") String id, Model model, HttpServletRequest request, String state) {
        //System.out.println();
        //System.out.println();
        //System.out.println();
        //System.out.println(state+"               "+id);

        ResultMessage rmsg = managerService.updateOpenRequest(Integer.parseInt(id), state);
        List<RequestOpen> list = managerService.getOpenRequests();


        //List<RequestOpenVO> VO = RequestOpenVO.entityToVO(list);
        //System.out.println("hostelid"+VO.get(0).getHostel_id()+"   hostel  look  id"+VO.get(0).getHostel_lookid());
        //model.addAttribute("Openlist", VO);
        model.addAttribute("message", rmsg.toShow());
        RequestOpen open = managerService.getOpen(Integer.parseInt(id));
        //System.out.println();
        //System.out.println();
        //System.out.println();
        //System.out.println("hostelid"+VO.get(0).getHostel_id()+"   hostel  look  id"+VO.get(0).getHostel_lookid());
        Hostel hostelid = open.getHostel();
        HostelVO vo = new HostelVO(hostelid);
        model.addAttribute("hostel", vo);
        return new ModelAndView("HostelManagerOpen");
    }

    @RequestMapping(value = "requestOpenDetail/{id}")
    public ModelAndView requestOpenDetail(@PathVariable("id") String id, Model model, HttpServletRequest request) {
        //Hostel hostel = hostelService.getById(Integer.parseInt(id));
        //System.out.println();
        //System.out.println();
        //System.out.println();
        //System.out.println(hostel.getUserid());
        RequestOpen open = managerService.getOpen(Integer.parseInt(id));
        //System.out.println();
        //System.out.println();
        //System.out.println();
        //System.out.println("hostelid"+VO.get(0).getHostel_id()+"   hostel  look  id"+VO.get(0).getHostel_lookid());
        Hostel hostelid = open.getHostel();
        model.addAttribute("Openlist", open);
        //HostelVO vo = new HostelVO(hostel);
        model.addAttribute("hostel", hostelid);
        return new ModelAndView("HostelManagerOpenDetail");
    }

    @RequestMapping(value = "requestModify", method = RequestMethod.GET)
    public ModelAndView requestModifyPage(Model model, HttpServletRequest request) {
        List<RequestModify> list = managerService.getModifyRequests();


        //System.out.println();
        //System.out.println();
        //System.out.println();
        //System.out.println(list.size());
        List<RequestModifyVO> VO = RequestModifyVO.entityToVO(list);
        model.addAttribute("Modify", VO);
        return new ModelAndView("HostelManagerMo");
    }

    @RequestMapping(value = "requestModify/{id}", method = RequestMethod.POST)
    public ModelAndView requestModify(@PathVariable("id") String id, Model model, HttpServletRequest request, String state) {
        ResultMessage rmsg = managerService.updateModifyRequest(Integer.parseInt(id), state);
        List<RequestModify> list = managerService.getModifyRequests();


        List<RequestModifyVO> VO = RequestModifyVO.entityToVO(list);
        model.addAttribute("Modify", VO);
        model.addAttribute("message", rmsg.toShow());
        Hostel hostel = hostelService.getHostel(Integer.parseInt(id));
        HostelVO vo = new HostelVO(hostel);
        model.addAttribute("hostel", vo);
        return new ModelAndView("HostelManagerModify");
    }

    @RequestMapping(value = "requestModifyDetail/{id}")
    public ModelAndView requestModifyDetail(@PathVariable("id") String id, Model model, HttpServletRequest request, String state) {
        Hostel hostel = hostelService.getById(Integer.parseInt(id));
        HostelVO vo1 = new HostelVO(hostel);
        model.addAttribute("hostel", vo1);
        RequestModify rm = managerService.getModify(Integer.parseInt(id));
        RequestModifyVO vo = new RequestModifyVO(rm);
        model.addAttribute("request", vo);
        return new ModelAndView("HostelManagerModifyDetail");
    }
}
