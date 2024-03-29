package com.qin.controller;

import com.alibaba.fastjson.JSONObject;
import com.qin.bean.*;
import com.qin.returnJson.ReturnObject;
import com.qin.service.EstateService;
import com.qin.vo.CellMessage;
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
@CrossOrigin(originPatterns = "*", allowedHeaders = "*", methods = {}, allowCredentials = "true")

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
    public String selectUnit(@RequestBody UnitMessage[] unitMessages) {
        System.out.println("estate selectUnit");
        List<FcUnit> allUnit = new ArrayList<>();
        //这里因为返回的数组，如果在server层想要遍历
        //就只能把这个集合往一个数组里面插入
        for (UnitMessage unitMessage : unitMessages) {
            allUnit.addAll(estateService.selectUnit(unitMessage));
        }
        
        return JSONObject.toJSONString(new ReturnObject(allUnit));
        
    }
    
    
    @RequestMapping("/estate/updateUnit")
    public String updateUnit(FcUnit fcUnit) {
        Integer result = estateService.updateUnit(fcUnit);
        if (result == 1) {
            return JSONObject.toJSONString(new ReturnObject("更新单元成功"));
        } else {
            return JSONObject.toJSONString(new ReturnObject("更新单元失败"));
        }
        
    }
    
    
    
    /*
     * 维护房间信息，
     * 因为楼宇，单元，房间都是一对多的关系
     * 如果全都一次性显示出来，那每次都是100+条数据
     * 因此，前端设置楼宇，单元，房间等字段，让用户选择那些维护
     *
     * */
    
    @RequestMapping("/estate/insertCell")
    public String insertCell(@RequestBody CellMessage[] cellMessages) {
        System.out.println("insert cell");
        List<FcCell> fcCells = estateService.insertCell(cellMessages);
        return JSONObject.toJSONString(new ReturnObject(fcCells));
    }
    
    
    //在step4中通过estate_code来查询楼宇，和selectBuilding类似，但不需要插入数据
    @RequestMapping("/estate/selectBuildingByEstate")
    public String selectBuildingByEstate(String estateCode) {
        System.out.println("estate:" + estateCode);
        List<FcBuilding> fcBuildings = estateService.selectBuildingByEstate(estateCode);
        System.out.println("----------------");
        System.out.println(fcBuildings);
        return JSONObject.toJSONString(new ReturnObject(fcBuildings));

    }
    
    
    //根据楼宇信息查询单元，还是一样的问题selectUnit里面有插入
    @RequestMapping("/estate/selectUnitByBuildingCode")
    public String selectUnitByBuildingCode(String buildingCode) {
        System.out.println("select unit");
        List<FcUnit> fcUnits = estateService.selectUnitByBuildingCode(buildingCode);
        System.out.println(fcUnits.size());
        return JSONObject.toJSONString(new ReturnObject(fcUnits));
    }
    
    //根据楼宇查出来的单元，再根据单元查出来具体的房间
    @RequestMapping("/estate/selectCell")
    public String selectCell(String unitCode) {
        System.out.println("select cell");
        List<FcCell> fcCells = estateService.selectCell(unitCode);
        System.out.println("----------------");
        System.out.println(fcCells.size());
        return JSONObject.toJSONString(new ReturnObject(fcCells));
    }
    
    
    //根据前端的company序号来查询公司和住宅,一个表查询
    @RequestMapping("/estate/selectEstate")
    public String selectEstate(String company){
        System.out.println("estate company");
        System.out.println(company);
        List<FcEstate> fcEstates = estateService.selectEstate(company);
        return JSONObject.toJSONString(new ReturnObject(fcEstates));
    }
    
    //维护房间信息
    @RequestMapping("/estate/updateCell")
    public String updateCell(FcCell fcCell){
        System.out.println("updateCell");
        System.out.println(fcCell);
        Integer result = estateService.updateCell(fcCell);
        if(result==1){
            return JSONObject.toJSONString(new ReturnObject("更新单元成功"));
        }else {
            return JSONObject.toJSONString(new ReturnObject("更新单元失败"));
        }
    
    }
    
    
    @RequestMapping("/estate/selectFcEstate")
    public String selectFcEstate(){
        System.out.println("selectFcEstate");
        List<FcEstate> fcEstates = estateService.selectFcEstate();
        return JSONObject.toJSONString(new ReturnObject(fcEstates));
    }
    
    
}
