package com.todo.tasks.entity;

import jakarta.persistence.Column;

import java.io.Serializable;

public class TasksPK implements Serializable {
    @Column(name = "taskid")
    private int taskId;
    @Column(name = "userName")
    private String userName;

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
