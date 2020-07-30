package com.css.demo.controller;

import com.css.demo.bean.UserBean;
import com.css.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/oneTest")
public class LoginController {

    @Autowired
    UserService userService ;

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



    @RequestMapping(value = "/loginIn",method = RequestMethod.POST)
    public String login(String name , String password){
        UserBean userBean = userService.loginIn(name,password);
        if(userBean!=null)
            return "success";
        else
            return "error";
    }

}
