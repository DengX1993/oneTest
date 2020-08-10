package com.css.demo.controller;

import com.css.demo.bean.*;
import com.css.demo.common.UUidUtil;
import com.css.demo.mapper.LogsBeanMapper;
import com.css.demo.service.*;
import javafx.scene.input.DataFormat;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.text.SimpleDateFormat;
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

    @Autowired
    RecordService recordService;

    //首页
    @RequestMapping("/index")
    public String show(HttpServletRequest request){
        String test = request.getRequestURI();
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
    @ResponseBody
    @RequestMapping(value = "/chushihuaceping",method = RequestMethod.POST)
    public Map<String, Object> chushihuaceping(
            @RequestParam("y1") String y1,@RequestParam("o1") String o1,@RequestParam("y2") String y2,@RequestParam("o2") String o2,
            @RequestParam("y3") String y3,@RequestParam("o3") String o3,@RequestParam("y4") String y4,@RequestParam("o4") String o4,
            @RequestParam("y5") String y5,@RequestParam("o5") String o5,@RequestParam("y6") String y6,@RequestParam("o6") String o6,
            @RequestParam("userNumber") String userNumber,@RequestParam("userId") String userId,HttpServletRequest request){


        cePingService.insert(userId,userNumber,y1,o1,y2,o2,y3,o3,y4,o4,y5,o5,y6,o6);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("statusCode", "200");
        result.put("message", "提交成功!");
        return  result;
    }

    @Autowired
    ContentDesignService contentDesign;
    //需求社区场景示例 http://localhost:8080/oneTest/xuQiuShequDemo?scene=1
    @RequestMapping(value = "/xuQiuShequDemo")
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
                    String[] split = contents.split("[<br/>]");
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
        if(cePingBean != null){
            cePingBean.setBeginCheckInvItationTime(new Date());
            int i = cePingService.updCepingCheckTime(cePingBean);
        }
        return view;
    }

    //需求社区主页http://localhost:8080/oneTest/xuqiushequmain?scene=1&userId=1
    @RequestMapping(value = "/xuqiushequmain",method = RequestMethod.POST)
    public ModelAndView xuqiushequmain(@RequestParam("scene") String scene,@RequestParam("userId") String userId,@RequestParam("viewFlag") String viewFlag){
        ModelAndView view = new ModelAndView("xuqiushequmain");
        List<ContentDesignBean> contentDesignList = contentDesign.selectAllInvitationByScene(scene);
        view.getModel().put("invitationList",contentDesignList);
        view.getModel().put("userId",userId);
        view.getModel().put("nextUrl","/oneTest/checkinvItation");
        view.getModel().put("commentFlag","0");
        view.getModel().put("viewFlag",viewFlag);
        view.getModel().put("scene",scene);
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
    @RequestMapping( value = "/checkinvItation",method = RequestMethod.POST)
    public ModelAndView checkinvItation( @RequestParam("invitationId") String invitationId,@RequestParam("userId") String userId,String insertType){
        //进入页面，也需要新增一条记录
        if("1".equals(insertType)){
            LogsBean saveLogsBean = new LogsBean();
            saveLogsBean.setUuid(UUidUtil.getUUid());
            saveLogsBean.setUserUuid(userId);
            saveLogsBean.setCheckContentId(invitationId);
            saveLogsBean.setCommentFlag(3);
            saveLogsBean.setCreateTime(new Date());
            int insert = logsBeanService.insert(saveLogsBean);
        }
        ModelAndView view = new ModelAndView("checkinvitation");
        ContentDesignBean contentDesignBean = contentDesign.selectObjByUUid(invitationId);
        view.getModel().put("contentDesignBean",contentDesignBean);
        //帖子内容
        List<String> contentList = new ArrayList<>();
        String contents = contentDesignBean.getContents();
        String[] split = contents.split("[<br/>]");
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
        view.getModel().put("contents",contents);
        view.getModel().put("contentList",contentList);
        view.getModel().put("commentList",commentList);
        view.getModel().put("scene",contentDesignBean.getScene());
        view.getModel().put("backUrl","/oneTest/xuqiushequmain");

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
    @ResponseBody
    @RequestMapping(value = "/questionnaire",method = RequestMethod.POST)
    public ModelAndView questionnaire(@RequestParam("userId") String userId,@RequestParam("scene") String scene){
        ModelAndView view = new ModelAndView("questionnaire");
        view.getModel().put("commentFlag","0");

        List<LogsBean> logsBeans = logsBeanService.selectLogsByUserId(userId);
        for (LogsBean logsBean : logsBeans) {
            //根据日志，查出是否评论
            if(1 == logsBean.getCommentFlag()){
                view.getModel().put("commentFlag","1");
            }
        }
        view.getModel().put("userId",userId);
        view.getModel().put("scene",scene);
        return view;
    }
    //问卷提交
    @RequestMapping(value = "/wenjuan",method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView saveRecordBean(RecordBean recordBean,String userId){
        ModelAndView view = new ModelAndView("endpage");
        CePingBean cePingBean = cePingService.selectBeanByUuid(userId);
        recordBean.setUuid(userId);
        if(cePingBean!=null) {
            recordBean.setO1(cePingBean.getO1());
            recordBean.setY1(cePingBean.getY1());
            recordBean.setY2(cePingBean.getY2());
            recordBean.setO2(cePingBean.getO2());
            recordBean.setY3(cePingBean.getY3());
            recordBean.setO3(cePingBean.getO3());
            recordBean.setY4(cePingBean.getY4());
            recordBean.setO4(cePingBean.getO4());
            recordBean.setY5(cePingBean.getY5());
            recordBean.setO5(cePingBean.getO5());
            recordBean.setY6(cePingBean.getY6());
            recordBean.setO6(cePingBean.getO6());

            //根据用户uuid查logs日志
            int views = 0;
            int Quantity = 0;
            List<LogsBean> logsBeans = logsBeanService.selectLogsByUserId(userId);
            for (LogsBean logsBean : logsBeans) {
                //查看用户点击的帖子数View
                if(3 == logsBean.getCommentFlag()){
                    views++;
                }
                if(1 == logsBean.getCommentFlag()){
                    Quantity++;
                    /**
                     * 根据浏览帖子uuid，查询帖子所属类型
                     * comment1 6,16,26,36
                     * comment2 7,17,27,37
                     * comment3 8,18,28,38
                     * comment4 9,19,29,39
                     * comment5 10,20,30,40
                     */
                    String id = logsBean.getCheckContentId();
                }
            }
            recordBean.setView(views+"");
            //浏览帖子时间Time
            recordBean.setTime(new Date().getTime()-cePingBean.getBeginCheckInvItationTime().getTime()+"");
            //评论过的帖子数Quantity
            recordBean.setQuantity(Quantity+"");
            recordBean.setUserNumber(cePingBean.getUserNumber());
            cePingBean.setEndCheckInvItationTime(new Date());
            cePingService.updCepingCheckTime(cePingBean);
        }
        recordService.insert(recordBean);
        //recordService.insertSelective(recordBean);
        view.getModel().put("userNumber",recordBean.getUserNumber());
        return view;
    }

    //结束页




    @RequestMapping(value = "/beg12in")
    public void contentDesign(){
        ContentDesignBean contentDesignzs = contentDesign.selectObjByUUid("1");
        System.out.println(contentDesignzs.getContents());
    }

    @RequestMapping("/test")
    public String test(){

        return "test";
    }

    @ResponseBody
    @RequestMapping("/parseExcel")
    public ModelAndView parseExcel(HttpServletRequest request) {
     Map<String, Object> result = new HashMap<String, Object>();
        result.put("statusCode", "200");
        result.put("message", "成功!");
        ModelAndView modelAndView = new ModelAndView("test");
        modelAndView.getModel().put("message","cg");
        return modelAndView;
    }

}
