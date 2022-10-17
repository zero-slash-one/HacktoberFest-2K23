package com.example.authapp;

public class User {
    private String name,username,email,password;
    private Integer age;

    public User(){

    }

    public User(String name,String username,String email,String password,String age){
        this.name=name;
        this.username=username;
        this.email=email;
        this.password=password;
        this.age=Integer.valueOf(age);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
