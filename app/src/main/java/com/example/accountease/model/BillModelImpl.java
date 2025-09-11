package com.example.accountease.model;

import com.example.accountease.entity.BillRecord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BillModelImpl implements IBillModel {
    // 这里为了简单，先用内存列表存储。
    // 后续可以改为数据库(SQLite)存储。
    private List<BillRecord> recordList = new ArrayList<>();

    // 定义一个简单的自动分类规则映射表
    private Map<String, String> categoryRules = new HashMap<String, String>() {{
        put("饭", "餐饮");
        put("餐", "餐饮");
        put("面", "餐饮");
        put("火锅", "餐饮");
        put("奶茶", "餐饮");
        put("咖啡", "餐饮");
        put("公交", "交通");
        put("地铁", "交通");
        put("打车", "交通");
        put("汽油", "交通");
        put("电影", "娱乐");
        put("游戏", "娱乐");
        put("买书", "学习");
        put("课程", "学习");
        // ...可以不断扩展更多规则
    }};

    @Override
    public void addRecord(BillRecord record, OnOperationListener listener) {
        // 模拟一个简单的保存操作
        recordList.add(record);
        // 保存成功，回调通知Presenter
        if (listener != null) {
            listener.onSuccess();
        }
        // 实际项目中这里可能会有数据库操作，失败时调用 listener.onFailure();
    }

    @Override
    public List<BillRecord> getAllRecords() {
        return recordList;
    }

    @Override
    public String getCategoryByRule(String remark) {
        if (remark == null || remark.isEmpty()) {
            return "其他"; // 默认类别
        }
        // 遍历规则映射，如果备注中包含关键字，则返回对应的类别
        for (Map.Entry<String, String> entry : categoryRules.entrySet()) {
            if (remark.contains(entry.getKey())) {
                return entry.getValue();
            }
        }
        return "其他"; // 没有匹配规则时返回默认类别
    }
}