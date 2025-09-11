package com.example.accountease.model;

import com.example.accountease.entity.BillRecord;

import java.util.List;

public interface IBillModel {
    interface OnOperationListener {
        void onSuccess();
        void onFailure(String errorMsg);
    }

    // 添加账单记录
    void addRecord(BillRecord record, OnOperationListener listener);
    // 获取所有记录 (为后续功能预留)
    List<BillRecord> getAllRecords();
    // 根据备注关键字获取自动分类的类别
    String getCategoryByRule(String remark);
}