package learn.learn.TaskManager.data;

import learn.learn.TaskManager.models.Task;

import java.util.List;

public class TaskFIleRepository implements TaskRepository {
    @Override
    public List<Task> findAll() {
        return null;
    }

    @Override
    public Task findById(int taskId) {
        return null;
    }

    @Override
    public Task create(Task task) {
        return null;
    }

    @Override
    public boolean update(Task task) {
        return false;
    }

    @Override
    public boolean delete(int taskId) {
        return false;
    }
}
