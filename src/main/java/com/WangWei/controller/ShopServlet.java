package com.WangWei.controller;

import com.WangWei.dao.ProductDao;
import com.WangWei.model.Category;
import com.WangWei.model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ShopServlet", value = "/shop")
public class ShopServlet extends HttpServlet {
    Connection con = null;
    public void init(){
        con = (Connection) getServletContext().getAttribute("con");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categoryList = Category.findAllCategory(con);
        request.setAttribute("categoryList", categoryList);
        ProductDao productDao = new ProductDao();
        List<Product> productList= new ArrayList<Product>();
        try {
            if(request.getParameter("categoryId")==null){
                productList = productDao.findAll(con);
            }else{
                int categoryId = Integer.parseInt(request.getParameter("categoryId"));
                productList = productDao.findByCategoryId(categoryId,con);
            }
        } catch (SQLException throwables) {
        throwables.printStackTrace(); }
        request.setAttribute("productList",productList);
        String path = "WEB-INF/views/shop.jsp";
        request.getRequestDispatcher(path).forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
