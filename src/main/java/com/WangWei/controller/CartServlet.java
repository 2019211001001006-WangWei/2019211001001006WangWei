package com.WangWei.controller;

import com.WangWei.dao.ProductDao;
import com.WangWei.model.Item;
import com.WangWei.model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CartServlet", value = "/cart")
public class CartServlet extends HttpServlet {
    Connection con = null;
    public void init(){
        con = (Connection) getServletContext().getAttribute("con");
    }
    protected  void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if(session != null && session.getAttribute("user")!=null){
            if(request.getParameter("action")==null){
                displayCart(request,response);
            }else if(request.getParameter("action").equals("add")){
                try {
                    buy(request,response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }else if(request.getParameter("action").equals("remove")){
                remove(request,response);
            }
        }else{
            response.sendRedirect("login");
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    protected void remove (HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        int id = 0;
        if(request.getParameter("productId")!=null){
            id = Integer.parseInt(request.getParameter("productId"));
        }
        int index = isExisting(id,cart);
        cart.remove(index);
        request.setAttribute("cart",cart);
        response.sendRedirect(request.getContextPath() + "/cart");
    }
    protected void buy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession();
        int id = request.getParameter("productId")!=null? Integer.parseInt(request.getParameter("productId")):0;
        int quantityParam = request.getParameter("quantity")!=null? Integer.parseInt(request.getParameter("quantity")):1;
        ProductDao dao = new ProductDao();
        if(session.getAttribute("cart")==null){
            List<Item> cart = new ArrayList<Item>();
            Product p = dao.findById(id,con);
            cart.add(new Item(p,quantityParam));
            session.setAttribute("cart", cart);
        }else{
            List<Item> cart = (List<Item>) session.getAttribute("cart");
            int index = isExisting(id,cart);
            if(index == -1){
                cart.add(new Item(dao.findById(id,con),1));
            }else{
                int quantity = cart.get(index).getQuantity() + 1;
                cart.get(index).setQuantity(quantity);
            }
            session.setAttribute("cart",cart);
        }
        response.sendRedirect(request.getContextPath() + "/cart");
    }
    protected int isExisting(int id, List<Item> item){
        for(int i = 0; i<item.size(); i++){
            if(item.get(i).getProduct().getProductId() ==  id){
                return i;
            }
        }
        return -1;
    }
    protected void displayCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("message","Your Cart");
        request.getRequestDispatcher("WEB-INF/views/cart.jsp").forward(request,response);
    }
}
