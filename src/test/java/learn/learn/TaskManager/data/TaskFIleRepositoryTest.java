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
}