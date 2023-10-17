package learn.learn.TaskManager.data;

import learn.learn.TaskManager.models.Status;
import learn.learn.TaskManager.models.Task;

import java.io.PrintWriter;
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
    //deserialize data
    private Task lineToTask(String line){
        String[] fields = line.split(DELIMITER);
        if(fields.length != 6){
            return null;
        }
        Task task = new Task(
                Integer.parseInt(fields[0]),
                restore(fields[1]),
                restore(fields[2]),
                restore(fields[3]),
                restore(fields[4]),
                Status.valueOf(fields[5])
        );
        return task;
    }
    //serialize
    private String taskToLine(Task task){
        StringBuilder buffer = new StringBuilder(100);
        buffer.append(task.getId()).append(DELIMITER);
        buffer.append(clean(task.getCreatedOn())).append(DELIMITER);
        buffer.append(clean(task.getTitle())).append(DELIMITER);
        buffer.append(clean(task.getDescription())).append(DELIMITER);
        buffer.append(clean(task.getDueDate())).append(DELIMITER);
        buffer.append(task.getStatus());
        return buffer.toString();
    }
    private void WriteToFile(List<Task> task){
        try(PrintWriter writer = new PrintWriter(filePath)){

        }
    }
}
