package com.todo.tasks.controller;

import com.todo.tasks.model.Request;
import com.todo.tasks.model.Response;
import com.todo.tasks.service.TodoService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/tasks")
@RestController
public class TodoController {
    @Autowired
    TodoService todoService;

    Response response = new Response();
    @PostMapping("/insert-item")
    public ResponseEntity<Response> insert(@RequestBody Request request){
        try {
            System.out.println(request);
            List<String> errorList = todoService.insertTask(request);
            response.setErrorList(errorList);
            response.setUserName(request.getUserName());
            if (errorList.isEmpty()) {
                response.setStatus("SUCCESS");
                return new ResponseEntity<Response>(response, HttpStatus.OK);
            } else {
                response.setStatus("FAILURE");
                return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            response.setUserName(request.getUserName());
            response.setStatus("FAILURE");
            List<String> errorList= new ArrayList<>();
            errorList.add("Exception occured"+ ExceptionUtils.getStackTrace(e));
            response.setErrorList(errorList);
            return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update-item")
    public ResponseEntity<Response> update(@RequestBody Request request){
        try {
            List<String> errorList = todoService.updateTask(request);
            Response response = new Response();
            response.setErrorList(errorList);
            response.setUserName(request.getUserName());
            if (errorList.isEmpty()) {
                response.setStatus("SUCCESS");
                return new ResponseEntity<Response>(response, HttpStatus.OK);
            } else {
                response.setStatus("FAILURE");
                return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
            }
        }catch(Exception e){
            response.setUserName(request.getUserName());
            response.setStatus("FAILURE");
            List<String> errorList= new ArrayList<>();
            errorList.add("Exception occured"+ ExceptionUtils.getStackTrace(e));
            response.setErrorList(errorList);
            return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/delete-item")
    public ResponseEntity<Response> delete(@RequestBody Request request){
            try {
                List<String> errorList = todoService.deleteTask(request);
                Response response = new Response();
                response.setErrorList(errorList);
                response.setUserName(request.getUserName());
                if (errorList.isEmpty()) {
                    response.setStatus("SUCCESS");
                    return new ResponseEntity<Response>(response, HttpStatus.OK);
                } else {
                    response.setStatus("FAILURE");
                    return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
                }
            }catch(Exception e){
                response.setUserName(request.getUserName());
                response.setStatus("FAILURE");
                List<String> errorList= new ArrayList<>();
                errorList.add("Exception occured"+ ExceptionUtils.getStackTrace(e));
                response.setErrorList(errorList);
                return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }
}
