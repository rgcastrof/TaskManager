import controllers.TaskController;
import models.Task;
import exceptions.TaskException;

import java.util.ArrayList;
import java.util.Scanner;

public class Tui {
    private final Scanner scanner;

    public Tui() {
        this.scanner = new Scanner(System.in);
    }

    private void drawMenu() {
        System.out.println(
                """
                === Task Manager ===
                1. Adicionar tarefa.
                2. Listar tarefas.
                3. Remover tarefas.
                4. Sair.
                """
        );
    }

    public void handleKeyPress(TaskController taskController) {
        drawMenu();
        String arg = scanner.next();

        switch (arg) {
            case "1":
                addTaskMenu(taskController);
                break;
            case "2":
                drawTasks(taskController);
                break;
            case "3":
                removeTask(taskController);
                break;
            case "4":
                System.exit(0);
                break;
            default:
                System.err.println("Error: Invalid key\n");
                break;
        }
    }

    private void addTaskMenu(TaskController taskController) {
        System.out.print("Type task name: ");
        String name = scanner.next();
        System.out.print("Type task description: ");
        String description = scanner.next();

        try {
            taskController.addTask(new Task(name, description));
        } catch (TaskException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void drawTasks(TaskController taskController) {
        ArrayList<Task> tasks = taskController.getTasks();
        if (tasks.isEmpty()) {
            System.out.println("There are no tasks yet.\n");
        } else {
            System.out.println("=== Task List ===");
            for (Task t : tasks) {
                System.out.printf("%d: %s ", t.getId(), t.getDescription());
                String status = switch (t.getStatus()) {
                    case PENDING -> "[]";
                    case FINISHED -> "[x]";
                    case CANCELED -> "C";
                };
                System.out.println(status);
            }
        }
    }

    private void removeTask(TaskController taskController) {
        System.out.print("Type id of the task which will be removed: ");
        try {
            int taskId = scanner.nextInt();
            taskController.deleteTask(taskId);
        } catch (TaskException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}