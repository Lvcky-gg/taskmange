package learn.learn.TaskManager.data;

import learn.learn.TaskManager.models.Task;
import java.util.List;

public interface TaskRepository {
    //crud
    List<Task> findAll();
    Task findById(int taskId);
    Task create(Task task);
    boolean update(Task task);
    boolean delete(int taskId);
}
