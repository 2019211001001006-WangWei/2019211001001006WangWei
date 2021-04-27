package com.WangWei.dao;

import com.WangWei.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDao implements IUserDao{
    @Override
    public boolean saveUser(Connection con, User user) throws SQLException {
        String sql = "insert into Usertable values(?,?,?,?,?)";
        try {
            //insert sqlserver；
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, user.getUsername());
            st.setString(2, user.getPassword());
            st.setString(3, user.getEmail());
            st.setString(4, user.getGender());
            st.setDate(5, new java.sql.Date(user.getBirthDate().getTime()));
            if(st.executeUpdate() > 0){
                return true;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int deleteUser(Connection con, User user) throws SQLException {
        String sql = "delete from usertable where id = ?";
        try {
            PreparedStatement p = con.prepareStatement(sql);
            p.setInt(1, user.getId());
            if(p.executeUpdate() > 0){
                return 1;
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateUser(Connection con, User user) throws SQLException {
        String sql;
        sql = "update usertable set username=?, password=?,gender=?,email=?,birthdate=? where id=?";
        try {

            PreparedStatement p = con.prepareStatement(sql);
            p.setString(1, user.getUsername());
            p.setString(2, user.getPassword());
            p.setString(3, user.getGender());
            p.setString(4, user.getEmail());
            p.setDate(5, new java.sql.Date(user.getBirthDate().getTime()));
            p.setInt(6, user.getId());

                return p.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public User findById(Connection con, Integer id) throws SQLException {
        String sql = "select * from Usertable where id=?";
        User s = new User();
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1,id);
            ResultSet sea = st.executeQuery();

            if(sea.next()){

                s.setId(sea.getInt("id"));
                s.setUsername(sea.getString("username"));
                s.setPassword(sea.getString("password"));
                s.setEmail(sea.getString("email"));
                s.setGender(sea.getString("gender"));
                s.setBirthDate(sea.getDate("birthdate"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return s;
    }

    @Override
    public User findByUsernamePassword(Connection con, String username, String password) throws SQLException {
        String sql = "select * from Usertable where username=? and password=?";
        User s = null;
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1,username);
            st.setString(2,password);
            ResultSet sea = st.executeQuery();
            if(sea.next()){
                s = new User();
                s.setId(sea.getInt("id"));
                s.setUsername(sea.getString("username"));
                s.setPassword(sea.getString("password"));
                s.setEmail(sea.getString("email"));
                s.setGender(sea.getString("gender"));
                s.setBirthDate(sea.getDate("birthdate"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return s;
    }

    @Override
    public List<User> findByUsername(Connection con, String username) throws SQLException {
        List<User> retList = new ArrayList<User>();
        String sql = "select * from Usertable where username = ?";
        try {

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1,username);
            ResultSet sea = st.executeQuery();
            while(sea.next()){
                User s = new User();
                s.setId(sea.getInt("id"));
                s.setUsername(sea.getString("username"));
                s.setPassword(sea.getString("password"));
                s.setEmail(sea.getString("email"));
                s.setGender(sea.getString("gender"));
                s.setBirthDate(sea.getDate("birthdate"));

                retList.add(s);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return retList;
    }

    @Override
    public List<User> findByPassword(Connection con, String password) throws SQLException {
        List<User> retList = new ArrayList<User>();
        String sql = "select * from Usertable where password = ?";
        try {

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1,password);
            ResultSet sea = st.executeQuery();
            while(sea.next()){
                User s = new User();
                s.setId(sea.getInt("id"));
                s.setUsername(sea.getString("username"));
                s.setPassword(sea.getString("password"));
                s.setEmail(sea.getString("email"));
                s.setGender(sea.getString("gender"));
                s.setBirthDate(sea.getDate("birthdate"));

                retList.add(s);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return retList;
    }

    @Override
    public List<User> findByEmail(Connection con, String email) throws SQLException {
        List<User> retList = new ArrayList<User>();
        String sql = "select * from Usertable where email = ?";
        try {

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1,email);
            ResultSet sea = st.executeQuery();
            while(sea.next()){
                User s = new User();
                s.setId(sea.getInt("id"));
                s.setUsername(sea.getString("username"));
                s.setPassword(sea.getString("password"));
                s.setEmail(sea.getString("email"));
                s.setGender(sea.getString("gender"));
                s.setBirthDate(sea.getDate("birthdate"));

                retList.add(s);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return retList;
    }

    @Override
    public List<User> findByGender(Connection con, String gender) throws SQLException {
        List<User> retList = new ArrayList<User>();
        String sql = "select * from Usertable where gender = ?";
        try {

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1,gender);
            ResultSet sea = st.executeQuery();
            while(sea.next()){
                User s = new User();
                s.setId(sea.getInt("id"));
                s.setUsername(sea.getString("username"));
                s.setPassword(sea.getString("password"));
                s.setEmail(sea.getString("email"));
                s.setGender(sea.getString("gender"));
                s.setBirthDate(sea.getDate("birthdate"));

                retList.add(s);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return retList;
    }

    @Override
    public List<User> findByBirthdate(Connection con, Date birthDate) throws SQLException {
        List<User> retList = new ArrayList<User>();
        String sql = "select * from Usertable where birthdate = ?";

        try {

            PreparedStatement st = con.prepareStatement(sql);
            st.setDate(1,new java.sql.Date(birthDate.getTime()));
            ResultSet sea = st.executeQuery();
            while(sea.next()){
                User s = new User();
                s.setId(sea.getInt("id"));
                s.setUsername(sea.getString("username"));
                s.setPassword(sea.getString("password"));
                s.setEmail(sea.getString("email"));
                s.setGender(sea.getString("gender"));
                s.setBirthDate(sea.getDate("birthdate"));

                retList.add(s);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return retList;
    }

    @Override
    public List<User> findAllUser(Connection con) throws SQLException {
        List<User> retList = new ArrayList<User>();
        String sqlSearch = "select * from Usertable";
        try {
            PreparedStatement stml = con.prepareStatement(sqlSearch);
            ResultSet sea = stml.executeQuery();
            while(sea.next()){
                User s = new User();
                s.setId(sea.getInt("id"));
                s.setUsername(sea.getString("username"));
                s.setPassword(sea.getString("password"));
                s.setEmail(sea.getString("email"));
                s.setGender(sea.getString("gender"));
                s.setBirthDate(sea.getDate("birthdate"));

                retList.add(s);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return retList;
    }
}
