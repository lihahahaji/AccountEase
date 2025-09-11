package com.example.accountease.presenter;

import com.example.accountease.entity.BillRecord;
import com.example.accountease.model.IBillModel;
import com.example.accountease.view.IBillView;

public class BillPresenterImpl implements IBillPresenter {
    private IBillView mBillView;
    private IBillModel mBillModel;

    public BillPresenterImpl(IBillView view, IBillModel model) {
        this.mBillView = view;
        this.mBillModel = model;
    }

    @Override
    public void addNewBill(double amount, String remark) {
        // 1. 调用Model的方法进行自动分类
        String category = mBillModel.getCategoryByRule(remark);

        // 2. 创建BillRecord对象
        BillRecord record = new BillRecord();
        record.setAmount(amount);
        record.setCategory(category);
        record.setRemark(remark);
        record.setTime(System.currentTimeMillis()); // 当前时间

        // 3. 调用Model保存数据
        mBillModel.addRecord(record, new IBillModel.OnOperationListener() {
            @Override
            public void onSuccess() {
                // 通知View保存成功
                if (mBillView != null) {
                    mBillView.showMessage("添加成功");
                    mBillView.clearInput();
                }
            }
            @Override
            public void onFailure(String errorMsg) {
                // 通知View保存失败
                if (mBillView != null) {
                    mBillView.showMessage("添加失败: " + errorMsg);
                }
            }
        });
    }

    @Override
    public void autoCategory(String remark) {
        // 调用Model的规则匹配方法，并将结果返回给View
        String category = mBillModel.getCategoryByRule(remark);
        if (mBillView != null) {
            mBillView.showAutoCategory(category);
        }
    }
}