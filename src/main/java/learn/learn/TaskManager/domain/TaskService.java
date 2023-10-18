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


    /*
    cannot add a task with id > 0
    we need to check
    we need to check that a title exists and it cannot be greater than 50 char
    we need to be sure there is a description and it cannot e less than 20 chars
    wee need to be sure there is a due date
    ensure there is a task status

    let the user know upon failures
    * */
}
