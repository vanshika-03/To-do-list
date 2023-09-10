package com.todo.tasks.service;

import com.todo.tasks.entity.Tasks;
import com.todo.tasks.entity.TasksPK;
import com.todo.tasks.entity.User;
import com.todo.tasks.model.Request;
import com.todo.tasks.model.RequestLogin;
import com.todo.tasks.model.RequestUser;
import com.todo.tasks.repository.TasksRepository;
import com.todo.tasks.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class TodoServiceImpl implements TodoService{
    @Autowired
    TasksRepository tasksRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    Validator validator;
    @Transactional
    @Override
    public List<String> insertTask(Request request) {
        List<String> errorList = validateTask(request);
        if(errorList.isEmpty()) {
            TasksPK tasksPKForId = new TasksPK();
            tasksPKForId.setUserName(request.getUserName());
            tasksPKForId.setTaskId(request.getTaskId());
            Optional<Tasks> tasksForId = tasksRepository.findById(tasksPKForId);
            if (tasksForId.isPresent()) {
                Tasks tasks = tasksForId.get();
                System.out.println(tasks.getTaskPk());
                System.out.print(tasks.getTaskName());
            } else {
                System.out.println("id not present");
                Date date = new Date();
                Timestamp timestamp = new Timestamp(date.getTime());
                Tasks tasks = new Tasks();
                TasksPK tasksPK = new TasksPK();
                tasksPK.setTaskId(request.getTaskId());
                tasksPK.setUserName(request.getUserName());
                tasks.setTaskPk(tasksPK);
                tasks.setTaskName(request.getTaskName());
                tasks.setTaskDesc(request.getTaskDesc());
                tasks.setCreatedTs(timestamp);
                tasks.setLastUptdTs(timestamp);
                Tasks tasksSaved = tasksRepository.save(tasks);
                System.out.println(tasksSaved.getTaskPk().getUserName()
                        + "," + tasksSaved.getTaskPk().getTaskId() + ":" + tasksSaved.getTaskName());
            }
            Tasks tasksForName = tasksRepository.findByTaskName(request.getTaskName());
        }
        return errorList;
    }

    @Override
    public List<String> updateTask(Request request) {
//        Date date = new Date();
//        TimeSta
        List<String> errorList = validateTask(request);
        if(errorList.isEmpty()) {
            TasksPK tasksPK = new TasksPK();
            tasksPK.setUserName(request.getUserName());
            tasksPK.setTaskId(request.getTaskId());
            Optional<Tasks> tasksForId = tasksRepository.findById(tasksPK);
            if (tasksForId.isPresent()) {
                Tasks tasks = tasksForId.get();
                if (request.getTaskName().isEmpty() == false)
                    tasks.setTaskName(request.getTaskName());
                if (request.getTaskDesc().isEmpty() == false)
                    tasks.setTaskDesc(request.getTaskDesc());
                Date date = new Date();
                Timestamp timestamp = new Timestamp(date.getTime());
                tasks.setLastUptdTs(timestamp);
                Tasks tasksSaved = tasksRepository.save(tasks);
                System.out.println("Successfully updated!");
            } else
                System.out.println("ID not present");
        }
        return errorList;
    }

    @Override
    public List<String> deleteTask(Request request) {
        List<String> errorList=validateTask(request);
        if(errorList.isEmpty()) {
            TasksPK tasksPKForId = new TasksPK();
            tasksPKForId.setUserName(request.getUserName());
            tasksPKForId.setTaskId(request.getTaskId());
            Optional<Tasks> tasksForId = tasksRepository.findById(tasksPKForId);
            if (tasksForId.isPresent()) {
                tasksRepository.deleteById(tasksPKForId);
                System.out.println("Deleted successfully");
            }
            else{
                System.out.println("Id not found");
            }
        }
        return errorList;
    }

//    @Override
//    public boolean validateRequest(Request request) {
//        if(!StringUtils.isAlphanumericSpace(request.getTaskName())){
//            return false;
//        }
////        else if(!)
//
//        return true;
//    }

    @Override
    public List<String> insertUserCredentials(RequestUser request) {
        List<String> errorList = validateUser(request);
        Optional<User> nameOfUser=userRepository.findById(request.getUserName());
        if(!nameOfUser.isPresent()) {
            if (errorList.isEmpty()) {
                User user = new User();
                user.setUserName(request.getUserName());
                user.setEmail(request.getEmail());
                user.setName(request.getName());
                try {
                    String decodedString=decode(request.getPassword());
                    user.setPassword(decodedString);
                    User userSaved = userRepository.save(user);
                }
                catch(Exception e){
                    errorList.add("Password is not in encoded form");
                }
            }
        }
        else {
            errorList.add("User name already exists");
        }
        return errorList;
    }

    private List<String> validateTask(Request request){
        List<String> errorList = new ArrayList<>();
        Set<ConstraintViolation<Request>> violations = validator.validate(request);
        if(!violations.isEmpty()){
            for(ConstraintViolation<Request> constraintViolation: violations){
                errorList.add(constraintViolation.getMessage());
            }
        }
        return errorList;
    }
    private List<String> validateUser(RequestUser user){
        List<String> errorList = new ArrayList<>();
        Set<ConstraintViolation<RequestUser>> violations = validator.validate(user);
        if(!violations.isEmpty()){
            for(ConstraintViolation<RequestUser> constraintViolation: violations){
                errorList.add(constraintViolation.getMessage());
            }
        }
        return errorList;
    }

    @Override
    public List<String> checkUserLogin(RequestLogin request) {
        List<String> errorList= new ArrayList<>();
        errorList = validateUserLogin(request);
        if(!errorList.isEmpty()) {
            Optional<User> user = userRepository.findById(request.getUserName());
            if (user.isPresent()) {
                User userById = user.get();
                String decodedPassword = decode(request.getPassword());
                if (!userById.getPassword().equals(decodedPassword)) {
                    errorList.add("Wrong Password");
                }
            } else
                errorList.add("User name does not exist.");
        }
        return errorList;

    }

    private List<String> validateUserLogin(RequestLogin request) {
        List<String> errorList = new ArrayList<>();
        Set<ConstraintViolation<RequestLogin>> violations = validator.validate(request);
        if(!violations.isEmpty()){
            for(ConstraintViolation<RequestLogin> constraintViolation: violations){
                errorList.add(constraintViolation.getMessage());
            }
        }
        return errorList;
    }

    private String decode(String password){
        byte[] decodedBytes = Base64.getDecoder().decode(password);
        String decodedString = new String(decodedBytes);
        return decodedString;
    }
}
