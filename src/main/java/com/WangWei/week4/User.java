package com.WangWei.week4;

public class User {
    private String username;
    private String password;
    private String email;
    private String gender;
    private String birthDate;
    public void setUsername(String username){
        this.username = username;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setGender(String gender){
        this.gender = gender;
    }
    public void setBirthDate(String birthDate){
        this.birthDate = birthDate;
    }
    public String getUsername(){
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }
    public String getEmail(){
        return this.email;
    }
    public String getGender(){
        return this.gender;
    }
    public String getBirthDate(){
        return this.birthDate;
    }
}
