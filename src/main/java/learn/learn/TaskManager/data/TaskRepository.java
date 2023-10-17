package learn.learn.TaskManager.data;

import learn.learn.TaskManager.models.Task;
import java.util.List;

public interface TaskRepository {
    //crud
    List<Task> findAll() throws DataAccessException;
    Task findById(int taskId) throws DataAccessException;
    Task create(Task task) throws DataAccessException;
    boolean update(Task task) throws DataAccessException;
    boolean delete(int taskId) throws DataAccessException;
}
