package com.qin.mapper;

import com.qin.bean.TblCompany;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 企业档案 Mapper 接口
 * </p>
 *
 * @author lian
 * @since 2022-03-06
 */
@Component
public interface TblCompanyMapper extends BaseMapper<TblCompany> {
    
    public List<TblCompany> selectCompany();

}
