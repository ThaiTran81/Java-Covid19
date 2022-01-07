package com.Model;

public class LoginModel {
    private String username;
    private String password;
    private int type;

    public LoginModel(){}
    LoginModel(String username, String password, int type){
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public String getUsername(){return username;}
    public String getPassword(){return password;}
    public int getType(){return type;}
    public void setUsername(String username){this.username = username;}
    public void setPassword(String password){this.password=password;}
    public void setType(int type) {this.type = type;}
}
