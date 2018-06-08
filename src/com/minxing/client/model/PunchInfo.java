package com.minxing.client.model;

/**
 * @author SuZZ on 2018/6/8.
 */
public class PunchInfo {

    /**
     * 打卡日期,格式为yyyy-MM-dd
     */
    private String punchDate;
    /**
     * 打卡时间,格式为HH:mm:ss
     */
    private String punchTime;
    /**
     * 时间段顺序号
     */
    private Integer itemSort;
    /**
     * 状态 1:正常 2:迟到 3:早退 4:旷工 5:缺卡
     */
    private Integer status;
    /**
     * 签到签退状态 0:签到 1:签退
     */
    private Integer punchType;
    /**
     * 是否可用进行考勤审批
     */
    private boolean canApproval;

    public String getPunchDate() {
        return punchDate;
    }

    public void setPunchDate(String punchDate) {
        this.punchDate = punchDate;
    }

    public String getPunchTime() {
        return punchTime;
    }

    public void setPunchTime(String punchTime) {
        this.punchTime = punchTime;
    }

    public Integer getItemSort() {
        return itemSort;
    }

    public void setItemSort(Integer itemSort) {
        this.itemSort = itemSort;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPunchType() {
        return punchType;
    }

    public void setPunchType(Integer punchType) {
        this.punchType = punchType;
    }

    public boolean isCanApproval() {
        return canApproval;
    }

    public void setCanApproval(boolean canApproval) {
        this.canApproval = canApproval;
    }
}
