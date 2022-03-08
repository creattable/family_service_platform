package com.qin.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qin.bean.FcBuilding;
import com.qin.bean.FcEstate;
import com.qin.bean.TblCompany;
import com.qin.bean.TblUserRecord;
import com.qin.mapper.FcBuildingMapper;
import com.qin.mapper.FcEstateMapper;
import com.qin.mapper.TblCompanyMapper;
import com.qin.mapper.TblUserRecordMapper;
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

    @Autowired
    private TblCompanyMapper tblCompanyMapper;
    @Autowired
    private FcEstateMapper fcEstateMapper;
    @Autowired
    private FcBuildingMapper fcBuildingMapper;

    public List<TblCompany> selectCompany(){
        List<TblCompany> companys = tblCompanyMapper.selectCompany();
        return companys;
    }
    
    /*
    * 在插入数据之前，最好对当前信息做判断，判断住宅编码是否已存在
    * 如果存在啧不允许插入，如果不存在，才允许插入
    *
    * */
    
    public Integer insertEstate(FcEstate fcEstate){
        
        //定义查询包装类
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("estate_code", fcEstate.getEstateCode());
    
        FcEstate findResult = fcEstateMapper.selectOne(queryWrapper);
        //定义返回的结果
        int result=0;
        if(findResult!=null){
            return result;
        }else {
            //这里用的是mybatis-plus,简单的就可以直接插入了
            result = fcEstateMapper.insert(fcEstate);
            return result;
        }
    }
    
    
    /*
    * 业务逻辑上因为在两张表，所以需要先插入数据，然后再查询数据
    * */
    
    public List<FcBuilding> selectBuilding(Integer buildingNumber,String estateCode){
        List<FcBuilding> fcBuildings=new ArrayList<>();
        for (int i=0;i<buildingNumber;i++){
            FcBuilding fcBuilding=new FcBuilding();
            fcBuilding.setBuildingCode("B"+(i+1));
            fcBuilding.setBuildingName("第"+(i+1)+"号楼");
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
    
    public Integer updateBuilding(FcBuilding fcBuilding){
        int result = fcBuildingMapper.updateById(fcBuilding);
        return result;
    
    }
    
    
    
}
