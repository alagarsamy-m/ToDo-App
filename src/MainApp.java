import java.util.Scanner;

public class MainApp {
    private static FileManager fileManager;
    private static TaskManager taskManager;

    public static void main(String[] args) {
        displayTitle();  // Display title at the start

        Scanner scanner = new Scanner(System.in);
        fileManager = new FileManager("todo_data.txt");
        taskManager = new TaskManager(fileManager.load());

        System.out.println("Welcome to the To-Do App (Type 'exit' to quit, 'commit' to save)");

        while (true) {
            System.out.print("> ");
            String command = scanner.nextLine().trim();

            if (command.equals("exit")) break;
            if (command.equals("commit")) {
                fileManager.save(taskManager.getGroups());
                System.out.println("Changes committed.");
                continue;
            }

            handleCommand(command);
        }

        scanner.close();
    }

    private static void handleCommand(String command) {
        String[] parts = command.split(" ");
        switch (parts[0]) {
            case "help":
                printHelp();
                break;
            case "create-group":
                if (parts.length > 1) {
                    taskManager.createGroup(parts[1]);
                } else {
                    System.out.println("Usage: create-group <group_name>");
                }
                break;
            case "delete-group":
                if (parts.length > 1) {
                    taskManager.deleteGroup(parts[1]);
                } else {
                    System.out.println("Usage: delete-group <group_name>");
                }
                break;
            case "add-task":
                if (parts.length > 2) {
                    taskManager.addTask(parts[1], parts[2]);
                } else {
                    System.out.println("Usage: add-task <group_name> <task_name>");
                }
                break;
            case "update-task":
                if (parts.length > 3) {
                    try {
                        int taskIndex = Integer.parseInt(parts[2]);
                        taskManager.updateTask(parts[1], taskIndex, parts[3]);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid task index. Usage: update-task <group_name> <task_index> <new_status>");
                    }
                } else {
                    System.out.println("Usage: update-task <group_name> <task_index> <new_status>");
                }
                break;
            case "delete-task":
                if (parts.length > 2) {
                    try {
                        int taskIndex = Integer.parseInt(parts[2]);
                        taskManager.deleteTask(parts[1], taskIndex);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid task index. Usage: delete-task <group_name> <task_index>");
                    }
                } else {
                    System.out.println("Usage: delete-task <group_name> <task_index>");
                }
                break;
            case "list-tasks":
                if (parts.length > 1) {
                    taskManager.listTasks(parts[1]);
                } else {
                    System.out.println("Usage: list-tasks <group_name>");
                }
                break;
            default:
                System.out.println("Unknown command. Type 'help' to see available commands.");
        }
    }

    // ASCII title display method
    private static void displayTitle() {
        String red = "\u001B[31m";
        String title = extracted(red);

        System.out.println(title);
        System.out.println("Version: 1.0.0");
        System.out.println("Type 'help' to see a list of available commands.");
        System.out.println();
    }

    private static String extracted(String red) {
        return red +
                "\r\n" + //
                "  _______    _____                               \r\n" + //
                " |__   __|  |  __ \\            /\\                \r\n" + //
                "    | | ___ | |  | | ___      /  \\   _ __  _ __  \r\n" + //
                "    | |/ _ \\| |  | |/ _ \\    / /\\ \\ | '_ \\| '_ \\ \r\n" + //
                "    | | (_) | |__| | (_) |  / ____ \\| |_) | |_) |\r\n" + //
                "    |_|\\___/|_____/ \\___/  /_/    \\_\\ .__/| .__/ \r\n" + //
                "                                    | |   | |    \r\n" + //
                "                                    |_|   |_|    \r\n" + //
                "";
    }

    // Print available commands and descriptions
    private static void printHelp() {
        System.out.println("\nAvailable Commands:");
        System.out.println("help                                 - Shows this help message.");
        System.out.println("create-group <name>                  - Creates a new group with the specified name.");
        System.out.println("delete-group <name>                  - Deletes the specified group.");
        System.out.println("add-task <group> <task>              - Adds a new task to the specified group.");
        System.out.println("update-task <group> <index> <status> - Updates the status of a task.");
        System.out.println("delete-task <group> <index>          - Deletes a task by index in the specified group.");
        System.out.println("list-tasks <group>                   - Lists all tasks in the specified group.");
        System.out.println("commit                               - Saves changes to the file.");
        System.out.println("exit                                 - Exits the application.\n");
    }
}
