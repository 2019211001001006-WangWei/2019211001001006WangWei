package com.WangWei.model;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private Integer categoryId;
    private String categoryName;
    private String description;
    private boolean active;

    public Category() {
    }

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category(Integer categoryId, String categoryName, String description, boolean active) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.description = description;
        this.active = active;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    public static List<Category> findAllCategory(Connection con){
        List<Category> list = new ArrayList<Category>();
        String queryString = "select * from Category";
        try {
            PreparedStatement statement = con.prepareStatement(queryString);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Category c = new Category();
                c.setCategoryId(rs.getInt("categoryId"));
                c.setCategoryName(rs.getString("categoryName"));
                c.setDescription(rs.getString("description"));
                list.add(c);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
    public static String findCategoryById(Connection con, int categoryId){
        String categoryName = null;
        String queryString = "select categoryName from category where categoryId=?";
        try {
            PreparedStatement st = con.prepareStatement(queryString);
            st.setInt(1,categoryId);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                categoryName = rs.getString("categoryName");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return categoryName;
    }
    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", description='" + description + '\'' +
                ", active=" + active +
                '}';
    }
}
