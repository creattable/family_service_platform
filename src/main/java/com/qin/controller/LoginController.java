package com.qin.controller;

import com.alibaba.fastjson.JSONObject;
import com.qin.bean.TblUserRecord;
import com.qin.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 秦家乐
 * @date 2022/3/6 15:20
 */


@RestController
//跨域的问题，还是用注解方便的多,但以后每个跨域都要写，写配置类就是全局了，以后都不用写了
@CrossOrigin(origins = "*",allowedHeaders = "*",methods = {},allowCredentials = "true")
public class LoginController {
    
    //有可能会提示报错，但实际没问题，如果觉得难受，就在上面加个@Component
    @Autowired
    private LoginService loginService;
    
    
    

    @RequestMapping("/auth/2step-code")
    public Boolean test(){
        System.out.println("前端框架自带的一个验证规则，对于后端没有意义，写不写无所谓");
        return true;
        
    }
    
    
    
    /*
    * 如果后端因为前端传来的类型导致接收不到参数，
    * 要么是加@RequestBody
    * 要么，写@RequestParam()
    * 要么，让前端的submit改成字符串格式
    * 要么，让前端安装npm install qs,
    *   然后导入const QS = require('qs')
    *   设置参数类型const params = QS.stringify(loginParams)
    *   qs.stringify 是把一个参数对象格式化为一个字符串
    *   把params传回来
    * 这样后端就可以正确的接收到前端的params
    *
    *
    * */
    
    @RequestMapping("/auth/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password){
        System.out.println("login");
        TblUserRecord tblUserRecord=loginService.login(username, password);
        System.out.println(tblUserRecord);
        return JSONObject.toJSONString(tblUserRecord);
        
    }
    

}
