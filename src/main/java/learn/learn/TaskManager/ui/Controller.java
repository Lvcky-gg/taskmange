package learn.learn.TaskManager.ui;
//dont use console here

import learn.learn.TaskManager.domain.TaskService;

public class Controller {
    private final View view;
    private final TaskService taskService;

    public Controller(View view, TaskService taskService) {
        this.view = view;
        this.taskService = taskService;
    }

    public void run(){

    }

    private void runMenu(){
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


    private void addTask(){

    }
    private void viewTasks(){

    }
    private void updateTask(){

    }
    private void deleteTask(){

    }
}
