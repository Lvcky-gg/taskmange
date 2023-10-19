package learn.learn.TaskManager;

import learn.learn.TaskManager.data.DataAccessException;
import learn.learn.TaskManager.data.TaskFIleRepository;
import learn.learn.TaskManager.domain.TaskService;
import learn.learn.TaskManager.models.Task;
import learn.learn.TaskManager.ui.Controller;
import learn.learn.TaskManager.ui.View;

import java.util.List;

public class App {
    public static void main(String[] args)  {
//        TaskFIleRepository repository = new TaskFIleRepository("./data/tasks.csv");
//        List<Task> tasks = repository.findAll();
//
//        for(Task task : tasks){
//            System.out.println(task);
//        }

        TaskFIleRepository repo = new TaskFIleRepository("./data/tasks.csv");
        TaskService service = new TaskService(repo);
        View view = new View();
        Controller controller = new Controller(view, service);
        controller.run();
    }
}
