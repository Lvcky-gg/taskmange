package learn.learn.TaskManager.ui;
//dont use console here

import learn.learn.TaskManager.data.DataAccessException;
import learn.learn.TaskManager.domain.TaskResults;
import learn.learn.TaskManager.domain.TaskService;
import learn.learn.TaskManager.models.Task;

import java.util.List;

public class Controller {
    private final View view;
    private final TaskService service;

    public Controller(View view, TaskService service) {
        this.view = view;
        this.service = service;
    }

    public void run(){
        view.displayHeader("Welcome to task manager");
        try{
            runMenu();
        }catch (DataAccessException e){
            view.displayText("Fatal Error");
            view.displayText(e.getMessage());
        }
        view.displayText("Goodbye");

    }

    private void runMenu() throws DataAccessException {
        boolean exit = false;
        while(!exit){
            int selection = view.getMenuOption();
            switch(selection){
                case 1:
                    addTask();
                    break;
                case 2:
                    viewTasks();
                    break;
                case 3:
                    updateTask();
                    break;
                case 4:
                    deleteTask();
                    break;
                case 5:
                    exit = true;
                    break;
            }
        }
    }


    private void addTask() throws DataAccessException {
        Task task = view.makeTask();
        TaskResults res = service.create(task);
        if(res.isSuccess()){
            view.displayText("Task was successfully created");
        }else{
            view.displayErrors(res.getMessages());
        }

    }
    private void viewTasks() throws DataAccessException {
        List<Task> task = service.findALl();
        view.displayTasks(task);

    }
    private void updateTask() throws DataAccessException {
        view.displayHeader("Update a task.");
        int id = view.updateById();
        Task task = service.findById(id);
        if(task != null){
            Task updatedTask = view.makeTask();
            updatedTask.setId(task.getId());
            TaskResults res = service.update(updatedTask);
            if(res.isSuccess()){
                view.displayText("Success!");
            }
        }else{
            view.displayErrors(List.of(String.format("There is no task with id %s", id)));
        }

    }
    private void deleteTask() throws DataAccessException {
        view.displayHeader("Delete a task");
        Task task = service.findById(view.updateById());
        if(task != null){
            TaskResults res = service.deleteById(task.getId());
            if(res.isSuccess()){
                view.displayText("Your task was successfully deleted");
            }
        }else{
            view.displayErrors(List.of("There are no tasks with this id"));
        }

    }
}
