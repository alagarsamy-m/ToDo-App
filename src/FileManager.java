import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileManager {
    private String filePath;

    public FileManager(String filePath) {
        this.filePath = filePath;
    }

    // Loads groups and tasks from the file
    public Map<String, Group> load() {
        Map<String, Group> groups = new HashMap<>();
        
        try (Scanner scanner = new Scanner(new File(filePath))) {
            Group currentGroup = null;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                
                if (line.startsWith("[Group]")) {
                    String groupName = line.substring(7).trim();
                    currentGroup = new Group(groupName);
                    groups.put(groupName, currentGroup);
                } else if (line.startsWith("[Task]") && currentGroup != null) {
                    String[] taskData = line.substring(6).split("\t");
                    int id = Integer.parseInt(taskData[0].trim());
                    String name = taskData[1].trim();
                    String status = taskData[2].trim();
                    String createdDate = taskData[3].trim();

                    Task task = new Task(id, name, status, createdDate);
                    currentGroup.addTask(task);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No existing data file found. Starting fresh.");
        }

        return groups;
    }

    // Saves groups and tasks to the file in a tabular format
    public void save(Map<String, Group> groups) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Group group : groups.values()) {
                writer.println("[Group] " + group.getName());
                
                for (Task task : group.getTasks()) {
                    writer.printf("[Task] %d\t%s\t%s\t%s%n", task.getId(), task.getName(), task.getStatus(), task.getCreatedDate());
                }
            }
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
}
