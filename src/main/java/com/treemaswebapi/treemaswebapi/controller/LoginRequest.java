package com.treemaswebapi.treemaswebapi.controller;

public class LoginRequest {
    private String nik;
    private String password;
    
    public String getNik() {
        return nik;
    }
    public void setNik(String nik) {
        this.nik = nik;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
