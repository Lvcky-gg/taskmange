package learn.learn.TaskManager.data;

import learn.learn.TaskManager.models.Task;

import java.util.List;

public class TaskRepositoryDouble implements TaskRepository{
    @Override
    public List<Task> findAll() throws DataAccessException {
        return null;
    }

    @Override
    public Task findById(int taskId) throws DataAccessException {
        return null;
    }

    @Override
    public Task create(Task task) throws DataAccessException {
        return null;
    }

    @Override
    public boolean update(Task task) throws DataAccessException {
        return false;
    }

    @Override
    public boolean delete(int taskId) throws DataAccessException {
        return false;
    }
}
