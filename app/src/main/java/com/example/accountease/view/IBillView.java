package com.example.accountease.view;

public interface IBillView {
    // 显示添加账单成功或失败的消息
    void showMessage(String msg);
    // 清除输入框
    void clearInput();
    // 显示自动分类后的类别
    void showAutoCategory(String category);
}