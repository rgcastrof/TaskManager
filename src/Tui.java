import controllers.TaskController;
import models.Task;
import exceptions.TaskException;
import models.TaskStatus;

import java.util.ArrayList;
import java.util.Scanner;

public class Tui {
    private final Scanner scanner;

    public Tui() {
        this.scanner = new Scanner(System.in);
    }

    private void drawMenu() {
        System.out.print(
                """
                === Task Manager ===
                1. Adicionar tarefa.
                2. Listar tarefas.
                3. Atualizar tarefa.
                4. Remover tarefa.
                5. Sair.
               \s
                >\s"""
        );
    }

    public void handleKeyPress(TaskController taskController) {
        drawMenu();
        String option = scanner.next();

        switch (option) {
            case "1":
                addTaskMenu(taskController);
                break;
            case "2":
                listTasks(taskController);
                break;
            case "3":
                updateTasks(taskController);
                break;
            case "4":
                removeTask(taskController);
                break;
            case "5":
                System.exit(0);
                break;
            default:
                System.err.println("Erro: Opcao invalida.");
                break;
        }
    }

    private void addTaskMenu(TaskController taskController) {
        System.out.print("Digite o nome da tarefa: ");
        String name = scanner.next();
        System.out.print("Digite a descricao da tarefa: ");
        String description = scanner.next();

        try {
            taskController.addTask(new Task(name, description));
            System.out.println("Tarefa adicionada com sucesso.\n");
        } catch (TaskException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listTasks(TaskController taskController) {
        ArrayList<Task> tasks = taskController.getTasks();
        if (tasks.isEmpty()) {
            System.out.println("Nenhuma tarefa adicionada.");
        } else {
            System.out.println("=== Tarefas ===");
            for (Task t : tasks) {
                System.out.printf("%d: %s ", t.getId(), t.getName());
                String status = switch (t.getStatus()) {
                    case PENDING -> "[ ]";
                    case FINISHED -> "[x]";
                    case CANCELED -> "[C]";
                };
                System.out.println(status);
                System.out.println("Descricao: " + t.getDescription() + "\n");
            }
        }

        System.out.println("Pressione Enter para voltar...");
        new Scanner(System.in).nextLine();
    }

    private void updateTasks(TaskController taskController) {
        boolean running = true;
        TaskStatus taskStatus = TaskStatus.PENDING;
        int taskId, option;

        while (running) {
            System.out.print("Digite o id da tarefa que deseja atualizar: ");
            taskId = scanner.nextInt();

            System.out.print(
                    """
                    O que deseja fazer?
                    1. Finalizar tarefa.
                    2. Cancelar tarefa.
                    \s
                    >\s"""
            );
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    taskStatus = TaskStatus.FINISHED;
                    running = false;
                    break;
                case 2:
                    taskStatus = TaskStatus.CANCELED;
                    running = false;
                    break;
                default:
                    System.out.println("Opcao invalida, tente novamente.");
            }
            try {
                taskController.updateTask(taskId, taskStatus);
            } catch (TaskException e) {
                System.out.println("Erro" + e.getMessage());
            }
        }
    }

    private void removeTask(TaskController taskController) {
        System.out.print("Digite o ID da tarefa que deseja remover: ");
        try {
            int taskId = scanner.nextInt();
            taskController.deleteTask(taskId);
        } catch (TaskException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}