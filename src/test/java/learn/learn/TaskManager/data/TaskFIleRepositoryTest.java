package learn.learn.TaskManager.data;

import learn.learn.TaskManager.models.Status;
import learn.learn.TaskManager.models.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class TaskFIleRepositoryTest {
    private static final String SEED_FILE = "./data/tasks-seed.csv";
    private static final String TEST_FILE_PATH = "./data/tasks-test.csv";
    private final TaskFIleRepository repository = new TaskFIleRepository(TEST_FILE_PATH);
    @BeforeEach
    public void setup() throws IOException {
        Path seedPath = Paths.get(SEED_FILE);
        Path testPath = Paths.get(TEST_FILE_PATH);

        Files.copy(seedPath,testPath, StandardCopyOption.REPLACE_EXISTING);
    }
  @Test
    public void shouldFindAll() throws DataAccessException {
      List<Task> actual = repository.findAll();
      assertEquals(3, actual.size());
      
      Task task = actual.get(0);
      assertEquals(1, task.getId());
      assertEquals("2023-10-08", task.getCreatedOn());
      assertEquals("Review Curriculum", task.getTitle());
      assertEquals("check content for spelling and grammar", task.getDescription());
      assertEquals("2023-10-10", task.getDueDate());
      assertEquals(Status.TODO, task.getStatus());
  }
    @Test
    public void shouldFindById() throws DataAccessException {
        Task taskOne = repository.findById(1);
        assertNotNull(taskOne);
        assertEquals(1, taskOne.getId());
        assertEquals("2023-10-08", taskOne.getCreatedOn());
        assertEquals("Review Curriculum", taskOne.getTitle());
        assertEquals("check content for spelling and grammar", taskOne.getDescription());
        assertEquals("2023-10-10", taskOne.getDueDate());
        assertEquals(Status.TODO, taskOne.getStatus());
    }
    @Test
    public void shouldNotFindNonExistingId() throws DataAccessException {
        Task task = repository.findById(100000000);
        assertNull(task);
    }

    @Test
    public void shouldCreateTask() throws DataAccessException {
        Task task = new Task(
                0,
                "2024-02-01",
                "TEST",
                "TEST",
                "2024-02-05",
                Status.TODO
        );
        Task actual = repository.create(task);
        assertEquals(4, actual.getId());

        List<Task> all = repository.findAll();
        assertEquals(4, all.size());

        assertEquals("2024-02-01", actual.getCreatedOn());
        assertEquals("TEST", actual.getTitle());
        assertEquals("TEST", actual.getDescription());
        assertEquals("2024-02-05", actual.getDueDate());
        assertEquals(Status.TODO, actual.getStatus());
    }
    @Test
    public void shouldCreateWithCommas() throws DataAccessException {
        Task task = new Task(
                0,
                "2024-02-01",
                "TEST",
                "TEST",
                "2024-02-05",
                Status.TODO
        );
        Task actual = repository.create(task);
        assertEquals(4, actual.getId());

        List<Task> all = repository.findAll();
        assertEquals(4, all.size());

        assertEquals("2024-02-01", actual.getCreatedOn());
        assertEquals("TEST", actual.getTitle());
        assertEquals("TEST", actual.getDescription());
        assertEquals("2024-02-05", actual.getDueDate());
        assertEquals(Status.TODO, actual.getStatus());

    }

    @Test
    public void shouldUpdate() throws DataAccessException {
        Task task = repository.findById(1);
        task.setStatus(Status.IN_PROGRESS);
        task.setDescription("CHECK");
        boolean res = repository.update(task);
        assertTrue(res);
        assertNotNull(task);
        assertEquals(1, task.getId());
        assertEquals("2023-10-08", task.getCreatedOn());
        assertEquals("Review Curriculum", task.getTitle());
        assertEquals("CHECK", task.getDescription());
        assertEquals("2023-10-10", task.getDueDate());
        assertEquals(Status.IN_PROGRESS, task.getStatus());
    }
    @Test
    public void shouldNotUpdateUnknown() throws DataAccessException {
        Task task = new Task(99999, "","","","",Status.TODO);
        boolean res = repository.update(task);
        assertFalse(res);
    }
    @Test
    public void shouldDelete() throws DataAccessException {
        boolean res = repository.delete(1);
        assertTrue(res);
        List<Task> all = repository.findAll();
        assertEquals(2, all.size());
        Task task = repository.findById(1);
        assertNull(task);
    }
}