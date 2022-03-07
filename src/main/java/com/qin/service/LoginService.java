package com.qin.service;

import com.qin.bean.TblUserRecord;
import com.qin.mapper.TblUserRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author 秦家乐
 * @date 2022/3/6 16:47
 */
@Service
public class LoginService {
    
    //有可能会提示报错，但实际没问题，如果觉得难受，就在TblUserRecord上面加个@Component
    @Autowired
    private TblUserRecordMapper tblUserRecordMapper;
    
    public TblUserRecord login(String username, String password){
        return tblUserRecordMapper.login(username,password);
    }
}
