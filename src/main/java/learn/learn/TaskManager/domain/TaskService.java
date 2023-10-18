package learn.learn.TaskManager.domain;

import learn.learn.TaskManager.data.DataAccessException;
import learn.learn.TaskManager.data.TaskRepository;
import learn.learn.TaskManager.models.Task;

import java.util.List;

public class TaskService {
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> findALl() throws DataAccessException {
        return repository.findAll();
    }
    public Task findById(int taskId) throws DataAccessException {
        return repository.findById(taskId);
    }

    public TaskResults create(Task task) throws DataAccessException {
        TaskResults res = validate(task);
        if(!res.isSuccess()){
            return res;
        }
        if(task == null){
            res.addMessage("Task cannot be null.");
            return res;
        }
        if(task.getId() > 0){
            res.addMessage("Cannot create existing task");
            return res;
        }

        task = repository.create(task);
        res.setTask(task);
        return res;
    }




    /*
    cannot add a task with id > 0
    we need to check
    we need to check that a title exists and it cannot be greater than 50 char
    we need to be sure there is a description and it cannot e less than 20 chars
    wee need to be sure there is a due date
    ensure there is a task status

    let the user know upon failures
    * */

    public TaskResults validate(Task task){
        TaskResults results = new TaskResults();
        if(task == null){
            results.addMessage("Task cannot be null");
            return results;
        }
        if(task.getCreatedOn() == null || task.getCreatedOn().isBlank()){
            results.addMessage("Created on date is required");
            return results;
        }
        if (task.getTitle() == null || task.getTitle().isBlank() || task.getTitle().length() > 50){
            results.addMessage("Title must exist and cannot be longer than 50 characters.");
            return results;
        }
        if(task.getDescription().isBlank() || task.getDescription().length() < 20 || task.getDescription() == null){
            results.addMessage("Description is required and must be more than 20 characters.");
            return results;
        }
        if(task.getDueDate() == null || task.getDueDate().isBlank()){
            results.addMessage(" Due date is required.");
            return results;
        }
        if(task.getStatus() == null){
            results.addMessage("Status is required.");
            return results;
        }

        return results;
    }

}
