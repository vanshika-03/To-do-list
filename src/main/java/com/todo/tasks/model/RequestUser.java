package com.todo.tasks.model;

import jakarta.validation.constraints.*;

public class RequestUser {
    @NotEmpty(message="You need to enter your user name")
    @Size(min = 1, max = 35,message = "The length of task name should be between 1 and 35")
//    @Min(value = 1, message = "No of characters in user name can't be less than 1")
//    @Max(value = 35, message = "No of characters in user name can't be more than 35")
    @Pattern(regexp = "[A-Za-z][A-Za-z0-9]*",message = "Username can only contain a-z, A-Z or 0-9")
    private String userName;
    @NotEmpty(message="Email id cannot be empty")
    @Pattern(regexp = "[a-zA-z0-9]+@[a-zA-z]+\\.com", message = "Email id is not valid")
    private String email;

    @NotEmpty(message="Name cannot be empty")
    @Size(min=1,max=35,message = "You cannot exceed the length of 35 characters and minimum character should be 1")
    @Pattern(regexp = "[a-zA-Z]+", message = "Name can only contain alphabets")
    private String name;

    @NotEmpty(message="Password cannot be empty")
    @Size(min=1,max=35,message = "Password has to be min 8, max 15")
    @Pattern(regexp="[a-zA-Z@#$&/*!0-9/-_]+",message = "Password can only contain a-z, A-Z, 0-9, @, #, $, &, *, !, -, _")
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString(){
        return "Name:"+ getName()+","+"Email: "+ getEmail()+","+"UserName: "+ getUserName()+","+"Password: "+"######";
    }
}
