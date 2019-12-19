package com.iflysse.viewmodel.GoodsViewModel;

import com.iflysse.pojo.Category;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品列表，用于在首页进行商品展示使用
 */
public class Goods4List {
    private int id;
    private String name;
    private double price;
    private String url;
    private String description;

    @Override
    public String toString() {
        return "Goods4List{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", categoryId=" + categoryId +
                ", slide_1='" + slide_1 + '\'' +
                ", slide_2='" + slide_2 + '\'' +
                ", slide_3='" + slide_3 + '\'' +
                ", slide_4='" + slide_4 + '\'' +
                ", category=" + category +
                ", detailPicture=" + detailPicture +
                '}';
    }

    private int categoryId;
    private String slide_1;
    private String slide_2;
    private String slide_3;
    private String slide_4;
    private Category category;
    private List<String> detailPicture;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategoryId() { return categoryId;}

    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }

    public String getSlide_1() {
        return slide_1;
    }

    public void setSlide_1(String slide_1) {
        this.slide_1 = slide_1;
    }

    public String getSlide_2() {
        return slide_2;
    }

    public void setSlide_2(String slide_2) {
        this.slide_2 = slide_2;
    }

    public String getSlide_3() {
        return slide_3;
    }

    public void setSlide_3(String slide_3) {
        this.slide_3 = slide_3;
    }

    public String getSlide_4() {
        return slide_4;
    }

    public void setSlide_4(String slide_4) {
        this.slide_4 = slide_4;
    };

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<String> getDetailPicture() {
        return detailPicture;
    }

    public void setDetailPicture(List<String> detailPicture) {
        this.detailPicture = detailPicture;
    }
}
