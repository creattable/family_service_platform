package com.qin.returnJson;

/**
 * @author 秦家乐
 * @date 2022/3/6 22:02
 */
public class Permission {
    private String permissionId;
    
    public Permission() {
    }
    
    public Permission(String permissionId) {
        this.permissionId = permissionId;
    }
    
    public String getPermissionId() {
        return permissionId;
    }
    
    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }
    
    @Override
    public String toString() {
        return "Permission{" +
                "permissionId='" + permissionId + '\'' +
                '}';
    }
    
}
