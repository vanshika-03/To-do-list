package com.todo.tasks.service;

import com.todo.tasks.model.Request;
import com.todo.tasks.model.RequestLogin;
import com.todo.tasks.model.RequestUser;

import java.util.List;

public interface TodoService {
    public List<String> insertTask(Request request);

    public List<String> updateTask(Request request);
    public List<String> deleteTask(Request request);

    List<String> insertUserCredentials(RequestUser request);

    List<String> checkUserLogin(RequestLogin request);
}
