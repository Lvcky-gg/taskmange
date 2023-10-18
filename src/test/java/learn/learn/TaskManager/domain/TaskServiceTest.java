package learn.learn.TaskManager.domain;

import learn.learn.TaskManager.data.DataAccessException;
import learn.learn.TaskManager.data.TaskRepository;
import learn.learn.TaskManager.data.TaskRepositoryDouble;
import learn.learn.TaskManager.models.Status;
import learn.learn.TaskManager.models.Task;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {
    TaskRepository repo = new TaskRepositoryDouble();
    TaskService service = new TaskService(repo);

    @Test
    public void shouldCreateTask(){

    }
    @Test
    public void shouldFindAll() throws DataAccessException {
        List<Task> actual = service.findALl();

        assertEquals(actual.size(),3);
        Task task = actual.get(0);

        assertEquals(1, task.getId());
        assertEquals("2024-08-01", task.getCreatedOn());
        assertEquals("Bake Cake", task.getTitle());
        assertEquals("bake cake for rover's birthday", task.getDescription());
        assertEquals("2024-08-22", task.getDueDate());
        assertEquals(Status.TODO, task.getStatus());

    }
    @Test
    public void shouldFindById() throws DataAccessException {
        Task task = service.findById(1);
        assertNotNull(task);
        assertEquals(1, task.getId());
        assertEquals("2024-08-01", task.getCreatedOn());
        assertEquals("Bake Cake", task.getTitle());
        assertEquals("bake cake for rover's birthday", task.getDescription());
        assertEquals("2024-08-22", task.getDueDate());
        assertEquals(Status.TODO, task.getStatus());


    }
    @Test
    public void shouldNotFindNonExistingId() throws DataAccessException {
        Task task  = service.findById(999);
        assertNull(task);

    }



}