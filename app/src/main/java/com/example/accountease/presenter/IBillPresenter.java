package com.example.accountease.presenter;

public interface IBillPresenter {
    void addNewBill(double amount, String remark);
    void autoCategory(String remark);
}