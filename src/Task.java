import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private static int idCounter = 1;
    private int id;
    private String name;
    private String status;
    private String createdDate;

    // ANSI color codes for console output
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_GREEN = "\u001B[32m";

    public Task(String name) {
        this.id = idCounter++;
        this.name = name;
        this.status = "pending";
        this.createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    // Constructor for loading tasks from file with specific data
    public Task(int id, String name, String status, String createdDate) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.createdDate = createdDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String getStatusColor() {
        switch (status.toLowerCase()) {
            case "pending":
                return ANSI_RED + "Pending" + ANSI_RESET;
            case "in-progress":
                return ANSI_YELLOW + "In-Progress" + ANSI_RESET;
            case "completed":
                return ANSI_GREEN + "Completed" + ANSI_RESET;
            default:
                return status;
        }
    }

    @Override
    public String toString() {
        return String.format("%-5d %-20s %-15s %-20s", id, name, getStatusColor(), createdDate);
    }
}
