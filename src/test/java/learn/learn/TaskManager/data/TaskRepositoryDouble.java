package learn.learn.TaskManager.data;

import learn.learn.TaskManager.models.Status;
import learn.learn.TaskManager.models.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskRepositoryDouble implements TaskRepository{
    @Override
    public List<Task> findAll() throws DataAccessException {
        List<Task> all = new ArrayList<>();
        all.add(new Task(1,"2024-08-01", "Bake Cake", "bake cake for rover's birthday", "2024-08-22", Status.TODO));
        all.add(new Task(2,"2024-08-01", "Bake Cake", "bake cake for rover's birthday", "2024-08-22", Status.TODO));
        all.add(new Task(3,"2024-08-01", "Bake Cake", "bake cake for rover's birthday", "2024-08-22", Status.TODO));
        return all;
    }

    @Override
    public Task findById(int taskId) throws DataAccessException {
        for (Task task : findAll()){
            if(task.getId() == taskId){
                return task;
            }
        }
        return null;
    }

    @Override
    public Task create(Task task) throws DataAccessException {
        task.setId(99);
        return null;
    }

    @Override
    public boolean update(Task task) throws DataAccessException {

        return task.getId() >0;
    }

    @Override
    public boolean delete(int taskId) throws DataAccessException {
        return taskId != 999;
    }
}
