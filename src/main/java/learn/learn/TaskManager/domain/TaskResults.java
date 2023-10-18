package learn.learn.TaskManager.domain;

import learn.learn.TaskManager.models.Task;

import java.util.ArrayList;

public class TaskResults {
    private final ArrayList<String> messages = new ArrayList<>();
    private Task task;

    public ArrayList<String> getMessages() {
        return messages;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public void addMessage(String message){
        messages.add(message);
    }

    public boolean isSuccess(){
        return messages.size() == 0;
    }
}
