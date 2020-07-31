package com.css.demo.controller;

import com.css.demo.bean.ContentDesignBean;
import com.css.demo.common.UUidUtil;
import com.css.demo.service.AccountService;
import com.css.demo.service.ContentDesignService;
import com.css.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Autowired
    ContentDesignService contentDesign;
    //需求社区场景示例 http://localhost:8080/oneTest/xuQiuShequDemo?scene=1
    @RequestMapping(value = "/xuQiuShequDemo",method = RequestMethod.GET)
    public ModelAndView xuQiuShequDemo(@RequestParam("scene") String scene){
        ModelAndView view = new ModelAndView("xuqiushequdemo");
        //内容
        List<String> contentList = new ArrayList<>();
        //评论
        List<ContentDesignBean> commentList = new ArrayList<>();
        List<ContentDesignBean> invitationList = new ArrayList<>();

        List<ContentDesignBean> ContentDesignBeanList = contentDesign.selectAllByScene(scene);
        if(ContentDesignBeanList != null && ContentDesignBeanList.size()>0){

            for (ContentDesignBean contentDesignBean : ContentDesignBeanList) {

                //根据场景，查1主要内容。
                if("1".equals(contentDesignBean.getContentType())){
                    String contents = contentDesignBean.getContents();
                    String[] split = contents.split("[？，。]");
                    for (String s : split) {
                        contentList.add(s);
                    }
                    view.getModel().put("title",contentDesignBean.getTitle());
                }
                //根据场景，查2评论。
                if("2".equals(contentDesignBean.getContentType()))
                    commentList.add(contentDesignBean);
                //根据场景，查3帖子
                if("3".equals(contentDesignBean.getContentType()))
                    invitationList.add(contentDesignBean);

            }
            view.getModel().put("contentList",contentList);
            view.getModel().put("commentList",commentList);
            view.getModel().put("invitationList",invitationList);
        }


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
    public int begin(@RequestParam("userNumber") String userNumber){
        String uuid = UUidUtil.getUUid();
        Date lastLoginTime = new Date();
        return accountService.insert(uuid,userNumber,lastLoginTime);
    }



    @RequestMapping(value = "/beg12in")
    public void contentDesign(){
        ContentDesignBean contentDesignzs = contentDesign.selectObjByUUid("1");
        System.out.println(contentDesignzs.getContents());
    }

}
