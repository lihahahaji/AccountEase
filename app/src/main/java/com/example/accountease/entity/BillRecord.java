package com.example.accountease.entity;

public class BillRecord {

    private int id;
    private double amount; // 金额
    private String category; // 类别 (如:餐饮、交通)
    private String remark; // 备注 (如:午饭、打车)
    private long time; // 时间戳

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
