package repository;

import com.rgcastrof.taskmanager.data.local.database.TaskDatabase;
import com.rgcastrof.taskmanager.data.repository.TaskRepositoryImpl;
import com.rgcastrof.taskmanager.domain.models.Task;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class RepositoryTest {

    private Connection connection;
    private final TaskRepositoryImpl taskRepository = new TaskRepositoryImpl();

    @Before
    public void prepareDb() throws SQLException {
        connection = TaskDatabase.getInstance().getConnection();

        if (connection.isClosed())
            connection = TaskDatabase.getInstance().getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(
        "CREATE TABLE IF NOT EXISTS Task (" +
                "id SERIAL NOT NULL PRIMARY KEY," +
                "name VARCHAR(50) NOT NULL," +
                "description VARCHAR(255)," +
                "is_completed BOOLEAN NOT NULL" +
                ");"
        );
        PreparedStatement cleanTable = connection.prepareStatement("DELETE FROM task")) {
            preparedStatement.executeUpdate();
            cleanTable.executeUpdate();
        }
    }

    @After
    public void tearDown() throws SQLException {
        // 1. Limpa os dados inseridos durante o teste atual
        if (connection != null && !connection.isClosed()) {
            try (PreparedStatement statement = connection.prepareStatement("TRUNCATE TABLE task RESTART IDENTITY;")) {
                statement.executeUpdate();
            }
        }
    }

    @Test
    public void shouldSaveTask() throws IOException {
        Task task = new Task(
            1,
            "Estudar JUnit",
            "Criar testes automatizados",
            false
        );

        taskRepository.create(task);

        List<Task> tasks = taskRepository.getAll();

        assertEquals(
            1,
            tasks.size()
        );

        assertEquals(
                "Estudar JUnit",
                tasks.getFirst().getName()
        );
    }

    @Test
    public void shouldGetTasks() throws IOException {
        taskRepository.create(
            new Task(
                1,
                "Task 1",
                "Primeira task",
                false
            )
        );

        taskRepository.create(
            new Task(
                2,
                "Task 2",
                "Segunda task",
                false
            )
        );

        List<Task> tasks = taskRepository.getAll();

        assertEquals(
                2,
                tasks.size()
        );

        assertEquals(
                "Task 1",
                tasks.get(0).getName()
        );

        assertEquals(
                "Task 2",
                tasks.get(1).getName()
        );
    }

    @Test
    public void shouldUpdateTasks() throws IOException {

        Task task = new Task(
            1,
            "Estudar Java",
            "Estudar Java básico",
            false
        );

        taskRepository.create(task);

        task.setName("Estudar JavaFX");

        task.setDescription("Estudar interface gráfica");

        task.setCompleted(true);

        taskRepository.update(task);
        taskRepository.complete(task);

        List<Task> tasks = taskRepository.getAll();

        assertEquals(1, tasks.size());

        assertEquals(
            "Estudar JavaFX",
            tasks.getFirst().getName()
        );

        assertEquals(
            "Estudar interface gráfica",
            tasks.getFirst().getDescription()
        );

        assertTrue(
                tasks.getFirst().getCompleted()
        );
    }

    @Test
    public void shouldDeleteTask() throws IOException {

        Task task = new Task(
            1,
            "Task para excluir",
            "Teste de exclusão",
            false
        );

        taskRepository.create(task);

        taskRepository.delete(task);

        List<Task> tasks = taskRepository.getAll();

        assertTrue(tasks.isEmpty());
    }
}