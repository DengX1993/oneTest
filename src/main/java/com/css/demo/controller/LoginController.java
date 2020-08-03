package com.css.demo.controller;

import com.css.demo.bean.*;
import com.css.demo.common.UUidUtil;
import com.css.demo.mapper.LogsBeanMapper;
import com.css.demo.service.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.awt.*;
import java.util.*;
import java.util.List;

@Controller
@RequestMapping("/oneTest")
public class LoginController {


    @Autowired
    UserService userService ;

    @Autowired
    private AccountService accountService;

    @Autowired
    CePingService cePingService;

    //首页
    @RequestMapping("/index")
    public String show(){
        return "console";
    }

    //填写用户code弹窗
    @RequestMapping(value = "/begin",method = RequestMethod.POST)
    public ModelAndView begin(@RequestParam("userNumber") String userNumber){
        ModelAndView view = new ModelAndView("choice");
        String uuid = UUidUtil.getUUid();
        Date lastLoginTime = new Date();
        accountService.insert(uuid,userNumber,lastLoginTime);
        view.getModel().put("userNumber",userNumber);
        view.getModel().put("userId",uuid);

        return view;
    }

    //初始化测评页面
    @RequestMapping(value = "/chushihuaceping",method = RequestMethod.POST)
    public String chushihuaceping(
            @RequestParam("y1") String y1,@RequestParam("o1") String o1,@RequestParam("y2") String y2,@RequestParam("o2") String o2,
            @RequestParam("y3") String y3,@RequestParam("o3") String o3,@RequestParam("y4") String y4,@RequestParam("o4") String o4,
            @RequestParam("y5") String y5,@RequestParam("o5") String o5,@RequestParam("y6") String y6,@RequestParam("o6") String o6,
            @RequestParam("userNumber") String userNumber,@RequestParam("userId") String userId){


        System.out.println("y1"+y1+"o1"+o1+"y2"+y2+"o2"+o2+"y3"+y3+"o3"+o3+"y4"+y4+"o4"+o4+"y5"+y5+"o5"+o5+"y6"+y6+"o6"+o6);
        cePingService.insert(userId,userNumber,y1,o1,y2,o2,y3,o3,y4,o4,y5,o5,y6,o6);
        //return  "redirect:xuqiushequmain?scene="+x+"&userId="+userNumber;
        return  "console";
    }

    @Autowired
    ContentDesignService contentDesign;
    //需求社区场景示例 http://localhost:8080/oneTest/xuQiuShequDemo?scene=1
    @RequestMapping(value = "/xuQiuShequDemo",method = RequestMethod.GET)
    public ModelAndView xuQiuShequDemo(@RequestParam("scene") String scene,@RequestParam("userId") String userId){
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
                    String[] split = contents.split("[？，。：；]");
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
            view.getModel().put("scene",scene);
            if(userId == null)
                userId = "1";
            view.getModel().put("userId",userId);
            view.getModel().put("contentList",contentList);
            view.getModel().put("commentList",commentList);
            view.getModel().put("invitationList",invitationList);
        }

        //记录一下访问时间
        CePingBean cePingBean = cePingService.selectBeanByUuid(userId);
        cePingBean.setBeginCheckInvItationTime(new Date());
        int i = cePingService.updCepingCheckTime(cePingBean);
        return view;
    }
    //记录一下访问时间
    @RequestMapping(value = "/recordingStartTime" ,method = RequestMethod.POST )
    public String recordingStartTime(String uuid){
        return "";
    }

    //需求社区主页http://localhost:8080/oneTest/xuqiushequmain?scene=1&userId=1
    @RequestMapping("/xuqiushequmain")
    public ModelAndView xuqiushequmain(@RequestParam("scene") String scene,@RequestParam("userId") String userId){
        ModelAndView view = new ModelAndView("xuqiushequmain");
        List<ContentDesignBean> contentDesignList = contentDesign.selectAllInvitationByScene(scene);
        view.getModel().put("invitationList",contentDesignList);
        view.getModel().put("userId",userId);
        view.getModel().put("nextUrl","/oneTest/checkinvItation");
        view.getModel().put("commentFlag","0");
        List<LogsBean> logsBeans = logsBeanService.selectLogsByUserId(userId);
        for (LogsBean logsBean : logsBeans) {
            //根据日志，查出是否评论
            if(1 == logsBean.getCommentFlag())
                view.getModel().put("commentFlag","1");
        }
        return view;
    }

    @Autowired
    public LogsBeanService logsBeanService;

    //帖子内容页面http://localhost:8080/oneTest/checkinvItation?invitationId=10&userId=1
    @RequestMapping("/checkinvItation")
    public ModelAndView checkinvItation(@RequestParam("invitationId") String invitationId,@RequestParam("userId") String userId){
        ModelAndView view = new ModelAndView("checkinvitation");
        ContentDesignBean contentDesignBean = contentDesign.selectObjByUUid(invitationId);
        view.getModel().put("contentDesignBean",contentDesignBean);
        //帖子内容
        List<String> contentList = new ArrayList<>();
        String contents = contentDesignBean.getContents();
        String[] split = contents.split("[？，。：；]");
        for (String s : split) {
            contentList.add(s);
        }
        //帖子评论
        List<ContentDesignBean> commentList = new ArrayList<>();
        if(userId != null){
            AccountBean accountBean = accountService.selectBeanByUuid(userId);
            List<LogsBean> logsBeans = logsBeanService.selectLogsByUserIdAndCheckContentId(userId,invitationId);
            for (LogsBean logsBean : logsBeans) {
                //根据日志，查出是否评论
                if(1 == logsBean.getCommentFlag()){
                    view.getModel().put("commentFlag","1");
                    ContentDesignBean viewBean = new ContentDesignBean();
                    //标题为用户code
                    viewBean.setTitle(accountBean.getUserNumber());
                    viewBean.setContents(logsBean.getComment());
                    commentList.add(viewBean);
                }
            }
        }
        view.getModel().put("userId",userId);
        view.getModel().put("contentList",contentList);
        view.getModel().put("commentList",commentList);
        view.getModel().put("backUrl","/oneTest/xuqiushequmain?scene="+contentDesignBean.getScene()+"&userId="+userId);

        return view;
    }
    //新增一条logs记录
    @RequestMapping(value = "/saveLogsBean",method = RequestMethod.POST)
    @ResponseBody
    public void saveLogsBean(LogsBean logsBean){
        logsBean.setUuid(UUidUtil.getUUid());
        logsBean.setCommentFlag(1);
        logsBean.setCreateTime(new Date());
        logsBeanService.insert(logsBean);
    }

    //问卷页
    @RequestMapping("/questionnaire")
    public ModelAndView questionnaire( String userNumber){
        ModelAndView view = new ModelAndView("questionnaire");
        view.getModel().put("userNumber",userNumber);
        return view;
    }
    //问卷提交
    @RequestMapping(value = "/wenjuan",method = RequestMethod.POST)
    @ResponseBody
    public void saveRecordBean(RecordBean recordBean){
        String age = recordBean.getAge();
        System.out.println(age);

        // logsBeanService.insert(logsBean);
    }

    //结束页




    @RequestMapping(value = "/beg12in")
    public void contentDesign(){
        ContentDesignBean contentDesignzs = contentDesign.selectObjByUUid("1");
        System.out.println(contentDesignzs.getContents());
    }

    @RequestMapping("/test")
    public String test(){
        return "choice";
    }

}
