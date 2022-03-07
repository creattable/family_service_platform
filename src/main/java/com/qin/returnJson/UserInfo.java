package com.qin.returnJson;

/**
 * @author 秦家乐
 * @date 2022/3/6 22:04
 */
public class UserInfo {
    
    private String name;
    private String avatar = "/avatar2.jpg";
    private Permissions role;
    
    public UserInfo(String name, Permissions role) {
        this.name = name;
        this.role = role;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getAvatar() {
        return avatar;
    }
    
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    
    public Permissions getRole() {
        return role;
    }
    
    public void setRole(Permissions role) {
        this.role = role;
    }
    
    @Override
    public String toString() {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", role=" + role +
                '}';
    }
}
