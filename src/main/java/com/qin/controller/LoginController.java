package com.qin.controller;

import com.alibaba.fastjson.JSONObject;
import com.qin.bean.TblUserRecord;
import com.qin.returnJson.Permission;
import com.qin.returnJson.Permissions;
import com.qin.returnJson.ReturnObject;
import com.qin.returnJson.UserInfo;
import com.qin.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


/**
 * @author 秦家乐
 * @date 2022/3/6 15:20
 */



//跨域的问题，还是用注解方便的多,但以后每个跨域都要写，写配置类就是全局了，以后都不用写了
@RestController
@CrossOrigin(originPatterns = "*",allowedHeaders = "*",methods = {},allowCredentials = "true")

public class LoginController {
    
    //有可能会提示报错，但实际没问题，如果觉得难受，就在上面加个@Component
    @Autowired
    private LoginService loginService;
    
    
    @RequestMapping("/auth/2step-code")
    public Boolean test(){
        System.out.println("前端框架自带的一个验证规则，写不写无所谓");
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
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session){
        System.out.println("login");
        TblUserRecord tblUserRecord = loginService.login(username,password);
        tblUserRecord.setToken(tblUserRecord.getUserName());
        //将用户数据写入到session中
        session.setAttribute("userRecord",tblUserRecord);
        ReturnObject returnObject = new ReturnObject(tblUserRecord);
        return JSONObject.toJSONString(returnObject);
    }
    
    
    //session的跨域问题
    //如果想从session中拿到数据，不仅后端要把@CrossOrigin中的allowCredentials设置为true
    //前端也要把axios.defaults.withCredentials=true，这样才是同一个session对象
    @RequestMapping("/user/info")
    public String getInfo(HttpSession session) {
        TblUserRecord tblUserRecord = (TblUserRecord) session.getAttribute("userRecord");
        //因为前端的ant design pro of vue权限颗粒度太细，不同的权限能看到的模块页面也不一样,正常不会那么细
        //mresult->role->permission->[permissionID,actionEntiySet],甚至还可以再细化，
        //所有才要这样一层层设置和获取
        
        //获取模块信息，一层一层的权限
        //数据库的格式是，rolePrivileges=221-223-226-901
        //权限也是一层一层的比较麻烦
        String[] split = tblUserRecord.getTblRole().getRolePrivileges().split("-");
        //创建权限集合对象
        Permissions  permissions = new Permissions();
        //向权限集合对象中添加具体的权限
        List<Permission> permissionList = new ArrayList<>();
        for (String s : split) {
            permissionList.add(new Permission(s));
        }
        permissions.setPermissions(permissionList);
        //设置返回指定result
        UserInfo userInfo = new UserInfo(tblUserRecord.getUserName(),permissions);
        return JSONObject.toJSONString(new ReturnObject(userInfo));
    }
    
    
    @RequestMapping("/auth/logout")
    public String logOut(HttpSession session){
        System.out.println("logout");
        //清空session
        session.invalidate();
        
        return "";
        
    }
    
    
}
