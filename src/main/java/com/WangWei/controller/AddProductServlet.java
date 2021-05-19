package com.WangWei.controller;

import com.WangWei.dao.ProductDao;
import com.WangWei.model.Category;
import com.WangWei.model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "AddProductServlet", value = "/admin/addProduct")
@MultipartConfig(maxFileSize = 16177215)
public class AddProductServlet extends HttpServlet {
    Connection con = null;
    public void init(){
        con = (Connection) getServletContext().getAttribute("con");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> productList = Category.findAllCategory(con);
        request.setAttribute("productList",productList);
        String path = "/WEB-INF/views/admin/addProduct.jsp";
        request.getRequestDispatcher(path).forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productName = request.getParameter("productName");
        Double price = request.getParameter("price")!=null? Double.parseDouble(request.getParameter("price")) :0.0;
        int categoryId = request.getParameter("categoryId")!=null? Integer.parseInt(request.getParameter("categoryId")) :0;
        String productDescription = request.getParameter("productDescription");
        Part filePart = request.getPart("picture");
        InputStream inputStream = null;
        if(filePart != null){
            System.out.println("fill name:" + filePart.getName() + "size:" + filePart.getSize() + "fill type:" + filePart.getContentType());
            inputStream = filePart.getInputStream();
        }
        Product product = new Product();
        product.setProductName(productName);
        product.setProductDescription(productDescription);
        product.setPicture(inputStream);
        product.setPrice(price);
        product.setCategoryId(categoryId);

        ProductDao productDao = new ProductDao();
        int n = 0;
        try {
            n = productDao.save(product,con);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(n>0){
            response.sendRedirect("productList");
        }
    }
}
