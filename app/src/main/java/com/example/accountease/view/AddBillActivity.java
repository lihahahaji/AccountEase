package com.example.accountease.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.accountease.R;
import com.example.accountease.model.BillModelImpl;
import com.example.accountease.presenter.BillPresenterImpl;

public class AddBillActivity extends AppCompatActivity implements IBillView, View.OnClickListener {
    private EditText mEtAmount, mEtRemark;
    private Button mBtnSubmit;
    private BillPresenterImpl mPresenter;
    private TextView tv_category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bill);

        initViews();
        // 初始化Presenter，将View和Model传递进去
        mPresenter = new BillPresenterImpl(this, new BillModelImpl());
    }

    private void initViews() {
        mEtAmount = findViewById(R.id.et_amount);
        mEtRemark = findViewById(R.id.et_remark);
        mBtnSubmit = findViewById(R.id.btn_submit);
        tv_category = findViewById(R.id.tv_category);
        mBtnSubmit.setOnClickListener(this);

        // 可以添加对备注的监听，实现实时自动分类
        mEtRemark.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // 文本改变之前调用，如果你不需要处理具体逻辑，可以留空
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 文本正在改变时调用，如果你不需要处理具体逻辑，可以留空
            }
            @Override
            public void afterTextChanged(Editable s) {
                // 当备注变化时，请求Presenter进行自动分类
                mPresenter.autoCategory(s.toString());
            }
            // ...其他方法省略
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_submit) {
            // 点击提交按钮，获取输入
            String amountStr = mEtAmount.getText().toString().trim();
            String remark = mEtRemark.getText().toString().trim();

            if (!amountStr.isEmpty()) {
                double amount = Double.parseDouble(amountStr);
                // 通知Presenter处理添加账单逻辑
                mPresenter.addNewBill(amount, remark);
            } else {
                showMessage("金额不能为空");
            }
        }
    }

    // 实现IBillView接口的方法

    // 显示 Toast 信息
    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    // 清除输入信息
    @Override
    public void clearInput() {
        mEtAmount.setText("");
        mEtRemark.setText("");
    }


    // 自动分类
    @Override
    public void showAutoCategory(String category) {
        // 你可以用一个TextView来显示自动分类的结果
        tv_category.setText(category);
        // 或者在输入备注时直接给用户提示
        Toast.makeText(this, "自动分类: " + category, Toast.LENGTH_SHORT).show();
    }
}