package com.todo.tasks.model;

import jakarta.validation.constraints.*;
import org.springframework.lang.NonNull;

public class Request {
    @Min(1) @Max(35)
    @Pattern(regexp = "^[a-zA-Z0-9]{1,35}$" )
    private String taskDesc;
    @Size(min = 1, max = 35,message = "The length of task name should be between 1 and 35")
    @Pattern(regexp = "^[/s*a-zA-Z0-9]{1,35}$")
    private String taskName;
    @NotEmpty(message = "Task id cannot be empty")
    @Digits(message = "Enter a valid number", fraction=1, integer=100)
    private Integer taskId;

    @NotEmpty(message="You need to enter your user name")
    @Pattern(regexp = "[A-Za-z0-9]{1,35}")
    private String userName;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public String toString(){
        return "taskId:"+taskId + ", taskName:" +taskName + ", taskDesc:" + taskDesc;
    }
}
