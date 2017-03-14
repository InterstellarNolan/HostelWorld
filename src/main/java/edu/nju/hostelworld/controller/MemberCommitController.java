package edu.nju.hostelworld.controller;

import edu.nju.hostelworld.service.MemberService;
import edu.nju.hostelworld.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vo.BookVO;
import vo.MemberVO;
import vo.OnLineUserVO;
import vo.TopUpVO;

/**
 * Created by disinuo on 17/3/12.
 */
@Controller
@SessionAttributes(types = {MemberVO.class,OnLineUserVO.class})
@RequestMapping("/member")
public class MemberCommitController {
    @Autowired
   MemberService vipService;

    @RequestMapping(value = "/book" ,method = RequestMethod.POST)
    public ModelAndView book(@ModelAttribute("bookBill")BookVO bookVO,
                             @ModelAttribute("member")MemberVO vipVO){
        bookVO.setVipId(vipVO.getId());
        ResultMessage msg=vipService.book(bookVO);
        return new ModelAndView("redirect:/member/bookList");
    }
    @RequestMapping(value = "/unbook",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String unBook(@RequestParam("bookListId")int bookListId,
                         @ModelAttribute("member")MemberVO vipVO){
         return vipService.unbook(vipVO.getId(),bookListId).toShow();
    }
    @RequestMapping(value = "/charge",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    public ModelAndView topUp(@ModelAttribute("member") MemberVO vipVO,
                              ModelMap model,
                              TopUpVO topUpVO){
        ResultMessage resMssg=vipService.charge(topUpVO.getMoney(),vipVO.getId(),topUpVO.getBankPassword());
        if(resMssg==ResultMessage.SUCCESS){
           model.addAttribute("message","充值成功");
        }else {
            model.addAttribute("message",resMssg.toShow());
        }
        return new ModelAndView("member/chargePage");
    }
    @RequestMapping(value = "/stopCard",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String stop(@ModelAttribute("member") MemberVO vipVO){
        ResultMessage msg=vipService.stop(vipVO.getId());
        return msg.toShow();
    }

    @RequestMapping(value = "/convert",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String convertScore(@ModelAttribute("member") MemberVO vipVO,
                               @RequestParam("score")double score){
        return vipService.scoreToMoney(vipVO.getId(),score).toShow();
    }
}
