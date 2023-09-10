package com.todo.tasks.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Timestamp;

@Entity
@Table(name = "tasks")
public class Tasks {
    @Id
    TasksPK taskPk;
    @Column(name = "taskname")
    private String taskName;
    @Column(name = "taskdesc")
    private String taskDesc;
    @Column(name = "created_Ts")
    private Timestamp createdTs;
    @Column(name = "last_Uptd_Ts")
    private Timestamp lastUptdTs;


    public TasksPK getTaskPk() {
        return taskPk;
    }

    public void setTaskPk(TasksPK taskPk) {
        this.taskPk = taskPk;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public Timestamp getCreatedTs() {
        return createdTs;
    }

    public void setCreatedTs(Timestamp createdTs) {
        this.createdTs = createdTs;
    }

    public Timestamp getLastUptdTs() {
        return lastUptdTs;
    }

    public void setLastUptdTs(Timestamp lastUptdTs) {
        this.lastUptdTs = lastUptdTs;
    }
}
