package com.qin.vo;

/**
 * @author 秦家乐
 * @date 2022/3/9 18:03
 */

/*
* 当前端传来的集合的时候，就可以用新建一个类，然后用类这种方式来接收
*
* */

public class UnitMessage {
    
    private String buildingCode;
    private Integer unitCount;
    
    public UnitMessage() {
    }
    
    public UnitMessage(String buildingCode, Integer unitCount) {
        this.buildingCode = buildingCode;
        this.unitCount = unitCount;
    }
    
    public String getBuildingCode() {
        return buildingCode;
    }
    
    public void setBuildingCode(String buildingCode) {
        this.buildingCode = buildingCode;
    }
    
    public Integer getUnitCount() {
        return unitCount;
    }
    
    public void setUnitCount(Integer unitCount) {
        this.unitCount = unitCount;
    }
    
    @Override
    public String toString() {
        return "UnitMessage{" +
                "buildingCode='" + buildingCode + '\'' +
                ", unitCount=" + unitCount +
                '}';
    }
}
