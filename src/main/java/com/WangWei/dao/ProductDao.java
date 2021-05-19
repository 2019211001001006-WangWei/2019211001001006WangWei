package com.WangWei.dao;

import com.WangWei.model.Product;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductDao implements  IProductDao{
    @Override
    public int save(Product product, Connection con) throws SQLException {
        int n = 0;
        String sql = "insert into product(ProductName,ProductDescription,Picture,price,CategoryID) values(?,?,?,?,?)";
        PreparedStatement pt = con.prepareStatement(sql);
        pt.setString(1, product.getProductName());
        pt.setString(2, product.getProductDescription());
        if(product.getPicture()!=null) {
            //for sql server
            pt.setBinaryStream(3, product.getPicture());
            //for mysql
            //   pt.setBlob(3, product.getPicture());
        }
        pt.setDouble(4, product.getPrice());
        pt.setInt(5, product.getCategoryId());
        n = pt.executeUpdate();
        if (n > 0) {
            return n;
        }
        return 0;
    }//end save

    @Override
    public int delete(Integer productId, Connection con) {
        String sql = "delete from product where productid = ?";
        try {
            PreparedStatement p = con.prepareStatement(sql);
            p.setInt(1, productId);
            if(p.executeUpdate() > 0){
                return 1;
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(Product instance, Connection con) {
        String sql = "update product set productname=?, productPicture=?,productDescription=?,categoryId=?,price=? where id=?";
        try {

            PreparedStatement p = con.prepareStatement(sql);
            p.setString(1, instance.getProductName());
            if(instance.getPicture() != null){
                p.setBinaryStream(2, instance.getPicture());
            }

            p.setString(3, instance.getProductDescription());
            p.setInt(4, instance.getCategoryId());
            p.setDouble(5, instance.getPrice());
            p.setInt(6, instance.getProductId());

            return p.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Product findById(Integer productId, Connection con) {
        Product product = null;
        String queryString = "select * form product where productId=?";
        try {
            PreparedStatement st = con.prepareStatement(queryString);
            st.setInt(1,productId);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                product.setProductId(rs.getInt("productId"));
                product.setProductName(rs.getString("ProductName"));
                product.setProductDescription(rs.getString("ProductDescription"));
                product.setPrice(rs.getDouble("price"));
                product.setCategoryId(rs.getInt("CategoryId"));
                product.setPicture(rs.getBinaryStream("picture"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> findByCategoryId(int categoryId, Connection con) {
        List list = null;
        String queryString = "select * form product where categoryId=?";
        try {
            PreparedStatement st = con.prepareStatement(queryString);
            st.setInt(1,categoryId);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Product product = new Product();
                product.setProductId(rs.getInt("productId"));
                product.setProductName(rs.getString("ProductName"));
                product.setProductDescription(rs.getString("ProductDescription"));
                product.setPrice(rs.getDouble("price"));
                product.setCategoryId(rs.getInt("CategoryId"));
                product.setPicture(rs.getBinaryStream("picture"));
                list.add(product);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Product> findByPrice(double minPrice, double maxPrice, Connection con) throws SQLException {
        List list = null;
        String queryString = "select * form product where ?< price and price <? ";
        try {
            PreparedStatement st = con.prepareStatement(queryString);
            st.setDouble(1,minPrice);
            st.setDouble(2,maxPrice);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Product product = new Product();
                product.setProductId(rs.getInt("productId"));
                product.setProductName(rs.getString("ProductName"));
                product.setProductDescription(rs.getString("ProductDescription"));
                product.setPrice(rs.getDouble("price"));
                product.setCategoryId(rs.getInt("CategoryId"));
                product.setPicture(rs.getBinaryStream("picture"));
                list.add(product);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Product> findAll(Connection con) throws SQLException {
        List list = null;
        String queryString = "select * form product";
        try {
            PreparedStatement st = con.prepareStatement(queryString);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Product product = new Product();
                product.setProductId(rs.getInt("productId"));
                product.setProductName(rs.getString("ProductName"));
                product.setProductDescription(rs.getString("ProductDescription"));
                product.setPrice(rs.getDouble("price"));
                product.setCategoryId(rs.getInt("CategoryId"));
                product.setPicture(rs.getBinaryStream("picture"));
                list.add(product);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Product> findByProductName(String productName, Connection con) throws SQLException {
        List list = null;
        String queryString = "select * form product where productName=?";
        try {
            PreparedStatement st = con.prepareStatement(queryString);
            st.setString(1,productName);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Product product = new Product();
                product.setProductId(rs.getInt("productId"));
                product.setProductName(rs.getString("ProductName"));
                product.setProductDescription(rs.getString("ProductDescription"));
                product.setPrice(rs.getDouble("price"));
                product.setCategoryId(rs.getInt("CategoryId"));
                product.setPicture(rs.getBinaryStream("picture"));
                list.add(product);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Product> getPicture(Integer productId, Connection con) throws SQLException {
        return null;
    }
}
