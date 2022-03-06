package com.qin.returnJson;

import java.util.List;

/**
 * @author 秦家乐
 * @date 2022/3/6 22:03
 */
public class Permissions {
    
    private List<Permission> permissions;
    
    public List<Permission> getPermissions() {
        return permissions;
    }
    
    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
    
    @Override
    public String toString() {
        return "Permissions{" +
                "permissions=" + permissions +
                '}';
    }
}
