import controllers.TaskController;

public class Manager {
    private final Tui tui;
    private final TaskController taskController;

    public Manager() {
        this.tui = new Tui();
        this.taskController = new TaskController();
    }

    public void run() {
        for (;;) {
            tui.handleKeyPress(taskController);
        }
    }
}
