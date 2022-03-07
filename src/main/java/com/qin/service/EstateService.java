package com.qin.service;

import com.qin.bean.TblCompany;
import com.qin.bean.TblUserRecord;
import com.qin.mapper.TblCompanyMapper;
import com.qin.mapper.TblUserRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 秦家乐
 * @date 2022/3/7 19:52
 */

@Service
public class EstateService {

    @Autowired
    private TblCompanyMapper tblCompanyMapper;

    public List<TblCompany> selectCompany(){
        List<TblCompany> companys = tblCompanyMapper.selectCompany();
        return companys;
    }
}
