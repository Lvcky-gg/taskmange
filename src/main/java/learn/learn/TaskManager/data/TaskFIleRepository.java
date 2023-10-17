package learn.learn.TaskManager.data;

import learn.learn.TaskManager.models.Status;
import learn.learn.TaskManager.models.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaskFIleRepository implements TaskRepository {


    private String filePath;
    private static final String DELILIMITER_REPLACEMENT = "@@@";
    private static String DELIMITER = ",";
    public TaskFIleRepository(String filePath) {
        this.filePath = filePath;
    }
    @Override
    public List<Task> findAll() throws DataAccessException {
        //create list of tasks
        List<Task> result = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            reader.readLine();
            for(String line = reader.readLine(); line != null; line = reader.readLine()){
                Task task = lineToTask(line);
                result.add(task);
            }

        }catch(FileNotFoundException ex){
            //blank
        }catch (IOException ex){
            throw new DataAccessException("Could not find path"+filePath);
        }

        return result;
    }

    @Override
    public Task findById(int taskId) throws DataAccessException {
        List<Task> all = findAll();
        for(Task task: all){
            if(task.getId() == taskId){
                return task;
            }
        }
        return null;
    }

    @Override
    public Task create(Task task) throws DataAccessException {
        List<Task>all = findAll();

        int nextId = getNextId(all);
        task.setId(nextId);

        all.add(task);
        writeToFile(all);
        return task;
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
    private void writeToFile(List<Task> tasks) throws DataAccessException {
        try(PrintWriter writer = new PrintWriter(filePath)){
            writer.println("id,createdOn,title,description,dueDate,status");
            for(Task task : tasks){
                String line = taskToLine(task);
                writer.println(line);
            }

        }catch(IOException ex){
            throw new DataAccessException("Could not write to filepath" + filePath);
        }
    }

    private int getNextId(List<Task> tasks){
        int maxId = 0;
        for(Task task:tasks){
            if(maxId < task.getId()){
                maxId = task.getId();
            }
        }
        return maxId +1;
    }
}
