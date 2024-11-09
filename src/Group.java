import java.util.ArrayList;
import java.util.List;

public class Group {
    private String name;
    private List<Task> tasks;

    public Group(String name) {
        this.name = name;
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public String getName() {
        return name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void updateTaskStatus(int id, String status) {
        tasks.stream().filter(t -> t.getId() == id).findFirst().ifPresent(task -> task.setStatus(status));
    }

    public void deleteTask(int id) {
        tasks.removeIf(task -> task.getId() == id);
    }

    public void listTasks() {
        System.out.println(String.format("%-5s %-20s %-10s %-20s", "ID", "Name", "Status", "Created Date"));
        for (Task task : tasks) {
            System.out.println(task);
        }
    }
}
