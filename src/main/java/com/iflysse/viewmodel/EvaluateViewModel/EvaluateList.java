package com.iflysse.viewmodel.EvaluateViewModel;

import java.util.Date;

/**
 * 商品列表，用于在首页进行商品展示使用
 */
public class EvaluateList {
    private int id;
    private int userId;
    private String userName;
    private int goodsId;
    private int cartId;
    private double grade;
    private String comment;
    private Date createTime;

    public int getId() {return id;}

    public void setId(int id) { this.id = id; }

    public int getUserId() {return userId;}

    public void setUserId(int userId) { this.userId = userId;}

    public int getGoodsId() {return goodsId; }

    public void setGoodsId(int goodsId) {this.goodsId = goodsId;}

    public String getComment() {return comment;}

    public void setComment(String comment) {this.comment = comment;}
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }
}
