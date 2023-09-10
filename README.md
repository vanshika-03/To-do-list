# **To-do-List**

It is a task management tool designed to help individuals and teams to organize their daily tasks and increase productivity. It is suitable for any individual looking to stay organized. It is a Springboot application which leverages REST APIs which are used to perform various backend operations like validate the user and login, signup, insert, update and delete tasks.

## **APIs used:**
**/user/sign-up:** This is used when a new user sign up into the application.

Request Body:

{
    "userName": "<userName>",
    "name": "<name>",
    "email": "<email id>",
    "password": "<password>" 
}

**/user/login:** Through this an existing user can login into the application using their credentials.

Request Body:

{
    "userName": "<user name>",
    "password": "<password>"
}

**/tasks/insert-task:** This API is used when a user wants to insert a new task in his To do list.

Request Body:

{
    "userName": "<user name>",
    "taskId": "<task id>",
    "taskName": "<task name>",
    "taskDesc": "<task desc>"
}

**/tasks/delete-task:** This API is used when a user wants to delete an existing task.

Request Body:

{
    "userName": "<user name>",
    "taskId": "<task id>",
    "taskName": "<task name>",
    "taskDesc": "<task desc>"
}

**/tasks/update-task:** This API is used when a user wants to update about description or name of a task.

Request Body:

{
    "userName": "<user name>",
    "taskId": "<task id>",
    "taskName": "<task name>",
    "taskDesc": "<task desc>"
}

## **Tools used:**
* Springboot v3.1.3
* Jdk v17
* MySQL v8.0.29

## **Author:**

Vanshika Mahana



