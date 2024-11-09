import java.util.*;

public class TaskManager {
    private Map<String, Group> groups;

    public TaskManager(Map<String, Group> groups) {
        this.groups = groups;
    }

    public Map<String, Group> getGroups() {
        return groups;
    }

    public void createGroup(String name) {
        groups.put(name, new Group(name));
        System.out.println("Group created: " + name);
    }

    public void deleteGroup(String name) {
        groups.remove(name);
        System.out.println("Group deleted: " + name);
    }

    public void addTask(String groupName, String taskName) {
        if (groups.containsKey(groupName)) {
            groups.get(groupName).addTask(new Task(taskName));
            System.out.println("Task added to " + groupName);
        } else {
            System.out.println("Group not found.");
        }
    }

    public void updateTask(String groupName, int taskId, String status) {
        if (groups.containsKey(groupName)) {
            groups.get(groupName).updateTaskStatus(taskId, status);
        }
    }

    public void deleteTask(String groupName, int taskId) {
        if (groups.containsKey(groupName)) {
            groups.get(groupName).deleteTask(taskId);
        }
    }

    public void listTasks(String groupName) {
        if (groups.containsKey(groupName)) {
            groups.get(groupName).listTasks();
        } else {
            System.out.println("Group not found.");
        }
    }
}
