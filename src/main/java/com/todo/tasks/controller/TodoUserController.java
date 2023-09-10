package com.todo.tasks.controller;

import com.todo.tasks.model.RequestLogin;
import com.todo.tasks.model.RequestUser;
import com.todo.tasks.model.Response;
import com.todo.tasks.model.ResponseLogin;
import com.todo.tasks.service.TodoService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping ("/user")
public class TodoUserController {
    @Autowired
    TodoService todoService;
    @PostMapping("/sign-up")
    public ResponseEntity<Response> insertUser(@RequestBody RequestUser request){
        Response response = new Response();
        System.out.println(request);
        try {
            List<String> errorList = todoService.insertUserCredentials(request);
            response.setErrorList(errorList);
            response.setUserName(request.getUserName());
            if (errorList.isEmpty()) {
                response.setStatus("SUCCESS");
                return new ResponseEntity<Response>(response, HttpStatus.OK);
            } else {
                response.setStatus("FAILURE");
                return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e){
            response.setUserName(request.getUserName());
            response.setStatus("FAILURE");
            List<String> errorList= new ArrayList<>();
            errorList.add("Exception occured"+ ExceptionUtils.getStackTrace(e));
            response.setErrorList(errorList);
            return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/login")
    public ResponseEntity<ResponseLogin> userLogin(@RequestBody RequestLogin request){
        List<String> errorList=todoService.checkUserLogin(request);
        ResponseLogin response=new ResponseLogin();
        if(errorList.isEmpty()) {
            response.setStatus("Valid");
            return new ResponseEntity<ResponseLogin>(response,HttpStatus.OK);
        }
        else {
            response.setStatus("Not Valid");
            return new ResponseEntity<ResponseLogin>(response,HttpStatus.FORBIDDEN);
        }
    }
}
