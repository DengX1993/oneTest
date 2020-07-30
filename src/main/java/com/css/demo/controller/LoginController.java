package com.css.demo.controller;

import com.css.demo.common.UUidUtil;
import com.css.demo.service.AccountService;
import com.css.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/oneTest")
public class LoginController {

    @Autowired
    UserService userService ;

    @Autowired
    private AccountService accountService;

    //首页
    @RequestMapping("/index")
    public String show(){
        return "console";
    }

    //填写用户code弹窗
    //初始化测评页面

    //需求社区场景示例
    //需求社区主页

    //帖子内容页面
    //问卷页
    //结束页



    @RequestMapping(value = "/begin",method = RequestMethod.POST)
    public int begin(@RequestParam("userNumber") String userNumber){
        String uuid = UUidUtil.getUUid();
        Date lastLoginTime = new Date();
        return accountService.insert(uuid,userNumber,lastLoginTime);
    }

}
