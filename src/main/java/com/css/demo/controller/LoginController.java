package com.css.demo.controller;

import com.css.demo.bean.UserBean;
import com.css.demo.common.UUidUtil;
import com.css.demo.service.AccountService;
import com.css.demo.service.UserService;
import com.css.demo.serviceImpl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
@RequestMapping("/oneTest")
public class LoginController {


    @Autowired
    UserService userService ;

    @Autowired
    AccountService accountService;
    //首页
    @RequestMapping("/index")
    public String show(){
        return "console";
    }

    //填写用户code弹窗
    //初始化测评页面

    //需求社区场景示例
    @RequestMapping("/xuQiuShequDemo")
    public ModelAndView xuQiuShequDemo(){
        String a = "1";
        ModelAndView view = new ModelAndView("xuqiushequdemo");
        view.getModel().put("aaaaaa","2131231");
        return view;
    }
    //记录一下访问时间
    @RequestMapping(value = "/recordingStartTime" ,method = RequestMethod.POST )
    public String recordingStartTime(String uuid){
        return "";
    }

    //需求社区主页
    @RequestMapping("/xuqiushequmain")
    public String xuqiushequmain(){
        return "xuqiushequmain";
    }

    //帖子内容页面
    //问卷页
    //结束页



    @RequestMapping(value = "/begin",method = RequestMethod.POST)
    public String begin(@RequestParam("userNumber") String userNumber){
        String uuid = UUidUtil.getUUid();
        Date lastLoginTime = new Date();
        return accountService.insert(uuid,userNumber,lastLoginTime);
    }

}
