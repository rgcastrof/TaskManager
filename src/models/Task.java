package models;

public class Task {
    private final int id;
    private final String name;
    private final String description;
    private TaskStatus status;

    static int idCounter = 0;

    public Task(String name, String description) {
        this.id = idCounter++;
        this.name = name;
        this.description = description;
        this.status = TaskStatus.PENDING;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}
