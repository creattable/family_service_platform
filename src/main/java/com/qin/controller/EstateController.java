package com.qin.controller;

import com.alibaba.fastjson.JSONObject;
import com.qin.bean.FcBuilding;
import com.qin.bean.FcEstate;
import com.qin.bean.FcUnit;
import com.qin.bean.TblCompany;
import com.qin.returnJson.ReturnObject;
import com.qin.service.EstateService;
import com.qin.vo.UnitMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public String selectCompany() {
        System.out.println("selectCompany");
        List<TblCompany> companies = estateService.selectCompany();
        return JSONObject.toJSONString(new ReturnObject(companies));
    }
    
    
    @RequestMapping("/estate/insertEstate")
    public String insertEstate(FcEstate fcEstate) {
        System.out.println(fcEstate);
        System.out.println("insert estate");
        Integer result = estateService.insertEstate(fcEstate);
        
        if (result == 0) {
            return JSONObject.toJSONString(new ReturnObject("0", "房产编码已经存在"));
        } else {
            return JSONObject.toJSONString(new ReturnObject("1", "插入房产成功"));
        }
    }
    
    
    /*
     * 此处应该完成的应该是楼宇的查询功能，但是，
     * 现在数据表中没有任何楼宇的数据，
     * 因此在编写的时候，需要进行插入且返回插入的数据
     * */
    
    @RequestMapping("/estate/selectBuilding")
    public String selectBuilding(Integer buildingNumber, String estateCode) {
        System.out.println("select building");
        List<FcBuilding> fcBuildings = estateService.selectBuilding(buildingNumber, estateCode);
        System.out.println(fcBuildings);
        return JSONObject.toJSONString(new ReturnObject(fcBuildings));
        
    }
    
    
    @RequestMapping("/estate/updateBuilding")
    public String updateBuilding(FcBuilding fcBuilding) {
        System.out.println("updateBuilding");
        Integer result = estateService.updateBuilding(fcBuilding);
        if (result == 1) {
            return JSONObject.toJSONString(new ReturnObject("更新楼宇成功"));
        } else {
            return JSONObject.toJSONString(new ReturnObject("更新楼宇失败"));
        }
    }
    
    
    //设置在step3里面检索和显示unit
    @RequestMapping("/estate/selectUnit")
    //接收过来的buildingCode是个数组，传来的格式大概是[{k=v},{k=v}]的格式
    //有时候需要额外创建一个类用来接收和处理
    public String selectUnit(@RequestBody UnitMessage[] unitMessages){
        System.out.println("estate selectUnit");
        List<FcUnit> allUnit=new ArrayList<>();
        //这里因为返回的数组，如果在server层想要遍历
        //就只能把这个集合往一个数组里面插入
        for (UnitMessage unitMessage : unitMessages) {
            allUnit.addAll(estateService.selectUnit(unitMessage));
        }
        
        return JSONObject.toJSONString(new ReturnObject(allUnit));
    
    }
    
    
    @RequestMapping("/estate/updateUnit")
    public String updateUnit(FcUnit fcUnit){
        Integer result = estateService.updateUnit(fcUnit);
        if(result==1){
            return JSONObject.toJSONString(new ReturnObject("更新单元成功"));
        }else {
            return JSONObject.toJSONString(new ReturnObject("更新单元失败"));
        }
        
    }
    
    
}
