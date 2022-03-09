package com.qin.vo;

/**
 * @author 秦家乐
 * @date 2022/3/9 22:04
 */
public class CellMessage {
    
    private String unitCode;
    private Integer startFloor;
    private Integer stopFloor;
    private Integer startCellId;
    private Integer StopCellId;
    
    public CellMessage(String unitCode, Integer startFloor, Integer stopFloor, Integer startCellId, Integer stopCellId) {
        this.unitCode = unitCode;
        this.startFloor = startFloor;
        this.stopFloor = stopFloor;
        this.startCellId = startCellId;
        StopCellId = stopCellId;
    }
    
    public CellMessage() {
    }
    
    public String getUnitCode() {
        return unitCode;
    }
    
    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }
    
    public Integer getStartFloor() {
        return startFloor;
    }
    
    public void setStartFloor(Integer startFloor) {
        this.startFloor = startFloor;
    }
    
    public Integer getStopFloor() {
        return stopFloor;
    }
    
    public void setStopFloor(Integer stopFloor) {
        this.stopFloor = stopFloor;
    }
    
    public Integer getStartCellId() {
        return startCellId;
    }
    
    public void setStartCellId(Integer startCellId) {
        this.startCellId = startCellId;
    }
    
    public Integer getStopCellId() {
        return StopCellId;
    }
    
    public void setStopCellId(Integer stopCellId) {
        StopCellId = stopCellId;
    }
    
    @Override
    public String toString() {
        return "CellMessage{" +
                "unitCode='" + unitCode + '\'' +
                ", startFloor=" + startFloor +
                ", stopFloor=" + stopFloor +
                ", startCellId=" + startCellId +
                ", StopCellId=" + StopCellId +
                '}';
    }
    
}
