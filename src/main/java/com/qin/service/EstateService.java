package com.qin.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qin.bean.*;
import com.qin.mapper.*;
import com.qin.vo.CellMessage;
import com.qin.vo.UnitMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 秦家乐
 * @date 2022/3/7 19:52
 */

@Service
public class EstateService {
    
    //有时候接口类会报错，如果报错就在里面加一个@Component就行了
    
    @Autowired
    private TblCompanyMapper tblCompanyMapper;
    @Autowired
    private FcEstateMapper fcEstateMapper;
    @Autowired
    private FcBuildingMapper fcBuildingMapper;
    @Autowired
    private FcUnitMapper fcUnitMapper;
    @Autowired
    private FcCellMapper fcCellMapper;
    
    public List<TblCompany> selectCompany() {
        List<TblCompany> companys = tblCompanyMapper.selectCompany();
        return companys;
    }
    
    /*
     * 在插入数据之前，最好对当前信息做判断，判断住宅编码是否已存在
     * 如果存在啧不允许插入，如果不存在，才允许插入
     *
     * */
    
    public Integer insertEstate(FcEstate fcEstate) {
        
        //定义查询包装类
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("estate_code", fcEstate.getEstateCode());
        
        FcEstate findResult = fcEstateMapper.selectOne(queryWrapper);
        //定义返回的结果
        int result = 0;
        if (findResult != null) {
            return result;
        } else {
            //这里用的是mybatis-plus,简单的就可以直接插入了
            result = fcEstateMapper.insert(fcEstate);
            return result;
        }
    }
    
    
    /*
     * 业务逻辑上因为在两张表，所以需要先插入数据，然后再查询数据
     * */
    
    public List<FcBuilding> selectBuilding(Integer buildingNumber, String estateCode) {
        List<FcBuilding> fcBuildings = new ArrayList<>();
        for (int i = 0; i < buildingNumber; i++) {
            FcBuilding fcBuilding = new FcBuilding();
            fcBuilding.setBuildingCode(estateCode + "B" + (i + 1));
            fcBuilding.setBuildingName("第" + (i + 1) + "号楼");
            fcBuilding.setEstateCode(estateCode);
            fcBuildingMapper.insert(fcBuilding);
            fcBuildings.add(fcBuilding);
        }
        return fcBuildings;
        
    }
    
    
    /*
     * 完成楼宇的更新功能
     * 因为如果全在下一步，会导致事务执行了一半然后之前执行的就没了
     * 因此，在每一行都有一个编辑，这样让用户一条一条自己选择保存
     *
     * */
    
    public Integer updateBuilding(FcBuilding fcBuilding) {
        int result = fcBuildingMapper.updateById(fcBuilding);
        return result;
        
    }
    
    
    public List<FcUnit> selectUnit(UnitMessage unitMessage) {
        //定义返回值集合
        List<FcUnit> fcUnits = new ArrayList<>();
        for (int i = 0; i < unitMessage.getUnitCount(); i++) {
            FcUnit fcUnit = new FcUnit();
            fcUnit.setBuildingCode(unitMessage.getBuildingCode());
            fcUnit.setUnitCode(unitMessage.getBuildingCode() + "U" + (i + 1));
            fcUnit.setUnitName("第" + (i + 1) + "单元");
            fcUnitMapper.insert(fcUnit);
            fcUnits.add(fcUnit);
        }
        return fcUnits;
        
    }
    
    
    public Integer updateUnit(FcUnit fcUnit) {
        int i = fcUnitMapper.updateById(fcUnit);
        return i;
    }
    
    
    public List<FcCell> insertCell(CellMessage[] cellMessages) {

        List<FcCell> lists = new ArrayList<>();
        //这个数组的循环写在外面也行，但写在controller层不美观
        for (CellMessage cellMessage : cellMessages) {
            //注意是从1开始，先写楼层，因为嵌套的逻辑
            //楼层
            for (int i = 1; i <= cellMessage.getStopFloor(); i++) {
                //房间号
                for (int j = cellMessage.getStartCellId(); j <= cellMessage.getStopCellId(); j++) {
                    FcCell fcCell = new FcCell();
                    fcCell.setUnitCode(cellMessage.getUnitCode());
                    //这里的编码自己随便写
                    fcCell.setCellName(i + "0" + j);
                    fcCell.setCellCode(cellMessage.getUnitCode() + "C" + i + "0" + j);
                    fcCell.setFloorNumber(i);
                    fcCellMapper.insert(fcCell);
                    lists.add(fcCell);
                }
            }
        }

        return lists;

    }


    public List<FcBuilding> selectBuildingByEstate(String estateCode) {
        QueryWrapper<FcBuilding> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("estate_code", estateCode);
        //可以只查询这两个字段
        queryWrapper.select("building_name", "building_code");
        List<FcBuilding> fcBuildings = fcBuildingMapper.selectList(queryWrapper);
        return fcBuildings;

    }

    public List<FcUnit> selectUnitByBuildingCode(String buildingCode) {
        QueryWrapper<FcUnit> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("building_code", buildingCode);
        queryWrapper.select("unit_name", "unit_code");
        List<FcUnit> fcUnits = fcUnitMapper.selectList(queryWrapper);
        return fcUnits;

    }

    public List<FcCell> selectCell(String unitCode) {
        QueryWrapper<FcCell> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("unit_code", unitCode);
        List<FcCell> fcCell = fcCellMapper.selectList(queryWrapper);
        return fcCell;

    }


    public List<FcEstate> selectEstate(String company){
        QueryWrapper<FcEstate> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("company", company);
        List<FcEstate> fcEstates = fcEstateMapper.selectList(queryWrapper);
        System.out.println(fcEstates);
        return fcEstates;
    }
    
    public Integer updateCell(FcCell fcCell){
        int update = fcCellMapper.updateById(fcCell);
        return update;
        
    }
    
    
    
}
