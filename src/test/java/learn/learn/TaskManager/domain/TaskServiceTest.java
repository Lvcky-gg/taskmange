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
    public void shouldCreateTask() throws DataAccessException {
        TaskResults act = service.create(new Task(0, "2023-05-09","Prepare Snacks",
                "prepare apple slices and snack for soccer game", "20223-05-11", Status.COMPLETE));
        assertNotNull(act.getTask());
        assertTrue(act.isSuccess());
        assertEquals(99, act.getTask().getId());


    }
    @Test
    public void shouldNotCreateNullTask() throws DataAccessException {
        TaskResults act = service.create(null);
        assertFalse(act.isSuccess());
        assertNull(act.getTask());
        assertEquals("Task cannot be null", act.getMessages().get(0));

    }
    @Test
    public void shouldNotCreateTaskWithoutStartDate() throws DataAccessException {
        TaskResults act = service.create( new Task(0, "","qweqwe",
                "prepare apple slices and snack for soccer game", "20223-05-11", Status.COMPLETE));

        assertFalse(act.isSuccess());
        assertNull(act.getTask());
        assertEquals("Created on date is required", act.getMessages().get(0));

    }

    @Test
    public void shouldNotCreateTaskWithoutTitle() throws DataAccessException {
        TaskResults act = service.create( new Task(0, "2023-05-09","",
                "prepare apple slices and snack for soccer game", "20223-05-11", Status.COMPLETE));

        assertFalse(act.isSuccess());
        assertNull(act.getTask());
        assertEquals("Title must exist and cannot be longer than 50 characters.", act.getMessages().get(0));

    }
    @Test
    public void shouldNotCreateTaskTitleLongerThan50() throws DataAccessException {
        TaskResults act = service.create( new Task(0, "2023-05-09","asdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasd",
                "prepare apple slices and snack for soccer game", "20223-05-11", Status.COMPLETE));

        assertFalse(act.isSuccess());
        assertNull(act.getTask());
        assertEquals("Title must exist and cannot be longer than 50 characters.", act.getMessages().get(0));

    }
    @Test
    public void shouldNotCreateTaskWithoutDescription() throws DataAccessException {
        TaskResults act = service.create( new Task(0, "2023-05-09","asd",
                "", "20223-05-11", Status.COMPLETE));

        assertFalse(act.isSuccess());
        assertNull(act.getTask());
        assertEquals("Description is required and must be more than 20 characters.", act.getMessages().get(0));

    }
    @Test
    public void shouldNotCreateTaskWithDescOver20Chars() throws DataAccessException {
        TaskResults act = service.create( new Task(0, "2023-05-09","asd",
                "asd", "20223-05-11", Status.COMPLETE));

        assertFalse(act.isSuccess());
        assertNull(act.getTask());
        assertEquals("Description is required and must be more than 20 characters.", act.getMessages().get(0));

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
    @Test
    public void shouldUpdateExistingTask() throws DataAccessException {
        List<Task> all = service.findALl();
        Task toUpdate = all.get(0);
        toUpdate.setDescription("this is an updated task description, testing, testing, testing");

        TaskResults act = service.update(toUpdate);
        assertTrue(act.isSuccess());
        assertEquals(0, act.getMessages().size());
        assertEquals("this is an updated task description, testing, testing, testing", all.get(0).getDescription());

    }
    @Test
    public void shouldNotUpdateNonExistingId() throws DataAccessException {
        Task task = new Task(0, "2023-08-01","Fake","This is a fake description whith at least 20 characters","2024-01-22",Status.TODO);
        TaskResults act = service.update(task);

        assertFalse(act.isSuccess());
        assertEquals(1, act.getMessages().size());
        assertTrue(act.getMessages().get(0).contains("does not exist"));

    }
//    @Test
//    public void shouldNotUpdateNullTask() throws DataAccessException {
//        TaskResults act = service.update(null);
//
//        assertFalse(act.isSuccess());
//        assertEquals(1, act.getMessages().size());
//        assertTrue(act.getMessages().get(0).contains("cannot be null"));
//
//    }
    @Test
    public void shouldDeleteExisting() throws DataAccessException {
        TaskResults act = service.deleteById(1);
        assertTrue(act.isSuccess());


    }
    @Test
    public void shouldNotDeleteNonExisting() throws DataAccessException {
        TaskResults act = service.deleteById(9999999);
        assertFalse(act.isSuccess());
        assertEquals(1, act.getMessages().size());
        assertTrue(act.getMessages().get(0).contains("does not"));
    }




}