package com.css.demo;

import com.css.demo.bean.UserBean;
import com.css.demo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {

    @Autowired
    UserService userService;

    @Test
    public void contextLoads() {
        UserBean userBean = userService.loginIn("123","123");
        if(userBean!=null)
            System.out.println("登陆成功！");
        else
            System.out.println("登录失败！");
    }

}
