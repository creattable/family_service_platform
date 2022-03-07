package com.qin.controller;

import com.alibaba.fastjson.JSONObject;
import com.qin.bean.TblCompany;
import com.qin.returnJson.ReturnObject;
import com.qin.service.EstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 秦家乐
 * @date 2022/3/7 19:49
 */

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {}, allowCredentials = "true")

public class EstateController {

    @Autowired
    private EstateService estateService;



    @RequestMapping("/estate/selectCompany")
    public String selectCompany(){
        System.out.println("selectCompany");
        List<TblCompany> companies = estateService.selectCompany();
        return JSONObject.toJSONString(new ReturnObject(companies));
    }


}
