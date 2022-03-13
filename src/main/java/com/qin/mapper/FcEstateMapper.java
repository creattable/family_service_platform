package com.qin.mapper;

import com.qin.bean.FcEstate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 楼盘信息 Mapper 接口
 * </p>
 *
 * @author lian
 * @since 2022-03-06
 */
@Component
public interface FcEstateMapper extends BaseMapper<FcEstate> {
    
    public List<FcEstate> selectEstate();

}
