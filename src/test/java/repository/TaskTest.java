package repository;

import com.rgcastrof.taskmanager.domain.models.Task;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class TaskTest {
    @Test
    public void shouldCreateTask() {

        Task tarefa = new Task(
            1,
            "Estudar Java",
            "Estudar JUnit",
            false
        );

        assertEquals(
            1,
            tarefa.getId()
        );

        assertEquals(
            "Estudar Java",
            tarefa.getName()
        );

        assertEquals(
            "Estudar JUnit",
            tarefa.getDescription()
        );

        assertFalse(tarefa.getCompleted());
    }

    @Test
    public void shouldUpdateTask() {

        Task tarefa = new Task(
            1,
            "Estudar Java",
            "Estudar JUnit",
            false
        );

        tarefa.setName("Estudar JavaFX");

        tarefa.setDescription("Estudar interface gráfica");

        tarefa.setCompleted(true);

        assertEquals(
            "Estudar JavaFX",
            tarefa.getName()
        );

        assertEquals(
            "Estudar interface gráfica",
            tarefa.getDescription()
        );

        assertTrue(tarefa.getCompleted());
    }

    @Test
    public void representTaskAsString() {

        Task tarefa = new Task(
            1,
            "Estudar Java",
            "Estudar JUnit",
            false
        );

        String result = tarefa.toString();

        assertTrue(result.contains("Estudar Java"));

        assertTrue(result.contains("Estudar JUnit"));

        assertTrue(result.contains("Pendente"));
    }

    @Test
    public void shouldShowTaskCompleted() {

        Task tarefa = new Task(
            1,
            "Estudar Java",
            "Estudar JUnit",
            false
        );

        tarefa.setCompleted(true);

        String result = tarefa.toString();

        assertTrue(result.contains("Concluída"));
    }
}