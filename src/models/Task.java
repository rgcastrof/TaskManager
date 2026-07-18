package models;

public class Task {
    private int id;
    private String name;
    private String description;
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

    public void setId(int id) {
        this.id = id;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}
