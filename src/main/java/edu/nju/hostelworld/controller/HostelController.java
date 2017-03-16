package edu.nju.hostelworld.controller;

import edu.nju.hostelworld.entity.BookBill;
import edu.nju.hostelworld.entity.Hostel;
import edu.nju.hostelworld.entity.HostelRoom;
import edu.nju.hostelworld.entity.User;
import edu.nju.hostelworld.service.HostelService;
import edu.nju.hostelworld.service.UserService;
import edu.nju.hostelworld.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import vo.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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
        boolean permitted = hostel.getPermitted();
        String state = "客栈未营业";
        if (permitted) {
            state = "客栈正常营业中";
        }
        model.addAttribute("hostelstate", state);
        return new ModelAndView("hostelHome");
    }

    @RequestMapping(value = "/home/{id}")
    public ModelAndView memberHostelRoom(ModelMap model, @PathVariable("id") String hostelid, HttpServletRequest request) {
        OnLineUserVO userVO = (OnLineUserVO) request.getSession().getAttribute("userVO");
        User user = userService.getById(userVO.getId());
        Hostel hostel = hostelService.getById(Integer.parseInt(hostelid));
        boolean permitted = hostel.getPermitted();
        String state = "客栈未营业";
        if (permitted) {
            state = "客栈正常营业中";
        }
        HostelVO hostelVO = new HostelVO(hostel);

        List<HostelRoomVO> roomlist = HostelRoomVO.entityToVO(hostelService.getAllValidRooms(hostel.getId()));
        //System.out.println();
        //System.out.println();
        //System.out.println("we have rooms" +roomlist.size());
        model.addAttribute("roomList", roomlist);
        model.addAttribute("hostelInfo", hostelVO);
        model.addAttribute("hostelstate", state);

        return new ModelAndView("hostelHomeForMember");
    }

    @RequestMapping(value = "/rooms")
    public ModelAndView roomsPage(Model model, HttpServletRequest request) {
        OnLineUserVO userVO = (OnLineUserVO) request.getSession().getAttribute("userVO");
        User user = userService.getById(userVO.getId());
        Hostel hostel = hostelService.getById(user.getUserid());
        List<HostelRoom> rooms = hostelService.getAllValidRooms(hostel.getId());
        List<HostelRoom> allrooms = hostelService.getAllRooms(hostel.getId());
        List<HostelRoom> allInvalidrooms = new ArrayList<HostelRoom>();
        for (int i = 0; i < allrooms.size(); i++) {
            HostelRoom room = allrooms.get(i);
            if (!room.getValid()) {
                allInvalidrooms.add(room);
            }
        }
        List<HostelRoomVO> roomlist = HostelRoomVO.entityToVO(rooms);
        List<HostelRoomVO> Invalidroomlist = HostelRoomVO.entityToVO(allInvalidrooms);
        model.addAttribute("roomList", roomlist);
        model.addAttribute("InvalidroomList", Invalidroomlist);
        return new ModelAndView("hostelRooms");
    }

    @RequestMapping(value = "/addroom", method = RequestMethod.GET)
    public ModelAndView addRoom(Model model, HttpServletRequest request) {

        return new ModelAndView("hosteladdRoom");
    }

    @RequestMapping(value = "/addroom", method = RequestMethod.POST)
    public ModelAndView hosteladdRoom(ModelMap model, String roomName, String roomPrice, String img, HttpServletRequest request) {
        OnLineUserVO userVO = (OnLineUserVO) request.getSession().getAttribute("userVO");
        User user = userService.getById(userVO.getId());
        Hostel hostel = hostelService.getById(user.getUserid());
        HostelRoomVO vo = new HostelRoomVO(Double.parseDouble(roomPrice), img, roomName, Integer.parseInt(roomPrice));
        ResultMessage rmsg = hostelService.addRoom(hostel.getId(), vo);
        model.addAttribute("message", rmsg.toShow());
        return new ModelAndView("hosteladdRoom");
    }

    @RequestMapping(value = "/room/{id}", method = RequestMethod.GET)
    public ModelAndView room(Model model, @PathVariable("id") String id, HttpServletRequest request) {
        OnLineUserVO userVO = (OnLineUserVO) request.getSession().getAttribute("userVO");
        User user = userService.getById(userVO.getId());
        HostelRoom room = hostelService.getRoomById(Integer.parseInt(id));
        Hostel hostel = room.getHostel();
        model.addAttribute("room", room);
        model.addAttribute("hostel", hostel);
        return new ModelAndView("hostelroomHome");
    }

    @RequestMapping(value = "/room/{id}", method = RequestMethod.POST)
    public ModelAndView roomEdit(@PathVariable("id") String id, Model model, String name, String price, HttpServletRequest request) {
        HostelRoom room = hostelService.getRoomById(Integer.parseInt(id));
        Hostel hostel = room.getHostel();
        RoomVO_input vp = new RoomVO_input();
        room.setPrice(Double.parseDouble(price));
        room.setName(name);
        vp.setName(name);
        vp.setPrice(Double.parseDouble(price));
        ResultMessage rmsg = hostelService.updateRoom(Integer.parseInt(id), vp);
        model.addAttribute("message", rmsg.toShow());
        model.addAttribute("room", room);
        model.addAttribute("hostel", hostel);
        return new ModelAndView("hostelroomHome");
    }

    @RequestMapping(value = "/roomInvalid/{id}", method = RequestMethod.GET)
    public ModelAndView roomInvalid(Model model, @PathVariable("id") String id, HttpServletRequest request) {
        OnLineUserVO userVO = (OnLineUserVO) request.getSession().getAttribute("userVO");
        User user = userService.getById(userVO.getId());
        HostelRoom room = hostelService.getRoomById(Integer.parseInt(id));
        Hostel hostel = room.getHostel();
        model.addAttribute("room", room);
        model.addAttribute("hostel", hostel);
        return new ModelAndView("hostelroomInvalidHome");
    }

    @RequestMapping(value = "/roomInvalid/{id}", method = RequestMethod.POST)
    public ModelAndView roomInvalidEdit(@PathVariable("id") String id, Model model, String name, String price, HttpServletRequest request) {
        HostelRoom room = hostelService.getRoomById(Integer.parseInt(id));
        Hostel hostel = room.getHostel();
        RoomVO_input vp = new RoomVO_input();
        room.setPrice(Double.parseDouble(price));
        room.setName(name);
        vp.setName(name);
        vp.setPrice(Double.parseDouble(price));
        ResultMessage rmsg = hostelService.updateRoom(Integer.parseInt(id), vp);
        model.addAttribute("message", rmsg.toShow());
        model.addAttribute("room", room);
        model.addAttribute("hostel", hostel);
        return new ModelAndView("hostelroomInvalidHome");
    }

    @RequestMapping(value = "/roomPaused/{id}")
    public ModelAndView roomPaused(@PathVariable("id") String id, Model model, HttpServletRequest request) {
        ResultMessage rmsg = hostelService.invalidateRoom(Integer.parseInt(id));
        HostelRoom room = hostelService.getRoomById(Integer.parseInt(id));
        Hostel hostel = room.getHostel();
        model.addAttribute("message", rmsg.toShow());
        model.addAttribute("room", room);
        model.addAttribute("hostel", hostel);
        return new ModelAndView("hostelroomHome");
    }

    @RequestMapping(value = "/roomActive/{id}")
    public ModelAndView roomActive(@PathVariable("id") String id, Model model, HttpServletRequest request) {
        ResultMessage rmsg = hostelService.activeRoom(Integer.parseInt(id));
        HostelRoom room = hostelService.getRoomById(Integer.parseInt(id));
        Hostel hostel = room.getHostel();
        model.addAttribute("message", rmsg.toShow());
        model.addAttribute("room", room);
        model.addAttribute("hostel", hostel);
        return new ModelAndView("hostelroomInvalidHome");
    }


    @RequestMapping(value = "/request", method = RequestMethod.GET)
    public ModelAndView requestPage(Model model, HttpServletRequest request) {
        OnLineUserVO userVO = (OnLineUserVO) request.getSession().getAttribute("userVO");
        User user = userService.getById(userVO.getId());
        Hostel hostel = hostelService.getById(user.getUserid());
        model.addAttribute("hostel", hostel);
        model.addAttribute("user", user);
        model.addAttribute("roomNum", hostelService.getAllRooms(hostel.getId()).size());
        model.addAttribute("validroomNum", hostelService.getAllValidRooms(hostel.getId()).size());
        return new ModelAndView("hostelOpenRequest");
    }

    @RequestMapping(value = "/request", method = RequestMethod.POST)
    public ModelAndView requestOpen(ModelMap model, HttpServletRequest request) {
        OnLineUserVO userVO = (OnLineUserVO) request.getSession().getAttribute("userVO");
        User user = userService.getById(userVO.getId());
        Hostel hostel = hostelService.getById(user.getUserid());

        ResultMessage rmsg = hostelService.requestManager(hostel.getId());
        model.addAttribute("message", rmsg.toShow());
        model.addAttribute("hostel", hostel);
        model.addAttribute("user", user);
        return new ModelAndView("hostelOpenRequest");
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView hostelEditPage(Model model, HttpServletRequest request) {
        OnLineUserVO userVO = (OnLineUserVO) request.getSession().getAttribute("userVO");
        User user = userService.getById(userVO.getId());
        Hostel hostel = hostelService.getById(user.getUserid());
        model.addAttribute("hostel", hostel);
        model.addAttribute("user", user);
        return new ModelAndView("hostelEdit");
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView hostelEdit(Model model, HttpServletRequest request, String name, String address, String phone) {
        OnLineUserVO userVO = (OnLineUserVO) request.getSession().getAttribute("userVO");
        User user = userService.getById(userVO.getId());
        Hostel hostel = hostelService.getById(user.getUserid());
        ResultMessage rmsg = hostelService.update(hostel.getId(), name, address, phone);
        model.addAttribute("message", rmsg.toShow());
        model.addAttribute("hostel", hostel);
        model.addAttribute("user", user);
        return new ModelAndView("hostelEdit");
    }

    @RequestMapping(value = "/analysis")
    public ModelAndView analysisPage(Model model, HttpServletRequest request) {
        OnLineUserVO userVO = (OnLineUserVO) request.getSession().getAttribute("userVO");
        User user = userService.getById(userVO.getId());
        Hostel hostel = hostelService.getById(user.getUserid());
        List<BookBill> bookbilllist = hostelService.getAllBookBills(hostel.getId());
        //model.addAttribute("booklistState", bookbillstatelist);
        model.addAttribute("bookbillVO", BookBillVO.entityToVO(bookbilllist));
        model.addAttribute("paybillVO", PayBillVO.entityToVO(hostelService.getAllPayBills(hostel.getId())));
        List<LiveBillVO> liveBillVOList = LiveBillVO.entityToVO(hostelService.getAllLiveBills(hostel.getId()));
        List<LiveBillVO> checkInbillVO = new ArrayList<LiveBillVO>(), checkOutbillVO = new ArrayList<LiveBillVO>();
        for (int i = 0; i < liveBillVOList.size(); i++) {
            LiveBillVO vo = liveBillVOList.get(i);
            if (vo.isType()) checkInbillVO.add(vo);
            else checkOutbillVO.add(vo);
        }
        model.addAttribute("checkInbillVO", checkInbillVO);
        model.addAttribute("checkOutbillVO", checkOutbillVO);
        return new ModelAndView("hostelAnalysis");
    }
}
