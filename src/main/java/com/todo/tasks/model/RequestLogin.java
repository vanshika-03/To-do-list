package com.todo.tasks.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RequestLogin {
    @NotEmpty(message="You need to enter your user name")
    @Size(min = 1, max = 35,message = "The length of task name should be between 1 and 35")
    @Pattern(regexp = "[A-Za-z][A-Za-z0-9]*",message = "Username can only contain a-z, A-Z or 0-9")
    private String userName;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
