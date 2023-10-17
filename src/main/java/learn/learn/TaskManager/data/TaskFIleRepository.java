package learn.learn.TaskManager.data;

import learn.learn.TaskManager.models.Task;

import java.util.List;

public class TaskFIleRepository implements TaskRepository {


    private String filePath;
    private static final String DELILIMITER_REPLACEMENT = "@@@";
    private static String DELIMITER = ",";
    public TaskFIleRepository(String filePath) {
        this.filePath = filePath;
    }
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

    //helpers
    private String restore(String value){
        return value.replace(DELILIMITER_REPLACEMENT, DELIMITER);
    }
    private String clean(String value){
        return  value.replace(DELIMITER,DELILIMITER_REPLACEMENT);
    }
}
