package learn.learn.TaskManager;

import learn.learn.TaskManager.data.DataAccessException;
import learn.learn.TaskManager.data.TaskFIleRepository;
import learn.learn.TaskManager.models.Task;

import java.util.List;

public class App {
    public static void main(String[] args) throws DataAccessException {
        TaskFIleRepository repository = new TaskFIleRepository("./data/tasks.csv");
        List<Task> tasks = repository.findAll();

        for(Task task : tasks){
            System.out.println(task);
        }
    }
}
