package com.gumpread.controller.user;


import com.gumpread.domain.entity.User;
import com.gumpread.utils.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping(value = {"/getUserInfo"})
    public AjaxResult getUserInfo(@RequestParam("name") String name){
        System.out.println("getUserInfo后端收到的参数： "+name);
        User user = new User();
        user.setId(22);
        user.setUserName("测试获取user");
        user.setPhoneNumber("15922465346");
        user.setUserNickName("Charles玉");
        return AjaxResult.success(user);
    }
}
