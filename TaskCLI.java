import java.io.*;
import java.nio.file.*;
import java.util.*;

public class TaskCLI {

    private static final String FILE_NAME = "tasks.json";
    private static List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        loadTasks();
        if (args.length == 0) {
            System.out.println("No command provided.");
            return;
        }

        String command = args[0];

        switch (command) {
            case "add":
                if (args.length < 2) {
                    System.out.println("Please provide a task description.");
                    return;
                }
                addTask(args[1]);
                break;
            case "update":
                if (args.length < 3) {
                    System.out.println("Please provide a task ID and new description.");
                    return;
                }
                updateTask(Integer.parseInt(args[1]), args[2]);
                break;
            case "delete":
                if (args.length < 2) {
                    System.out.println("Please provide a task ID.");
                    return;
                }
                deleteTask(Integer.parseInt(args[1]));
                break;
            case "mark-in-progress":
                if (args.length < 2) {
                    System.out.println("Please provide a task ID.");
                    return;
                }
                markTaskStatus(Integer.parseInt(args[1]), "in-progress");
                break;
            case "mark-done":
                if (args.length < 2) {
                    System.out.println("Please provide a task ID.");
                    return;
                }
                markTaskStatus(Integer.parseInt(args[1]), "done");
                break;
            case "list":
                if (args.length == 1) {
                    listTasks(null);  // List all tasks
                } else {
                    listTasks(args[1]);  // List tasks by status
                }
                break;
            default:
                System.out.println("Unknown command: " + command);
        }
        saveTasks();
    }

    private static void loadTasks() {
        Path path = Paths.get(FILE_NAME);
        if (Files.exists(path)) {
            try (BufferedReader reader = Files.newBufferedReader(path)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",", 3);  // Ensuring we split into ID, Description, and Status
                    if (parts.length == 3) {
                        tasks.add(new Task(Integer.parseInt(parts[0]), parts[1], parts[2]));
                    }
                }
            } catch (IOException e) {
                System.out.println("Error loading tasks: " + e.getMessage());
            }
        }
    }

    private static void saveTasks() {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(FILE_NAME))) {
            for (Task task : tasks) {
                writer.write(task.getId() + "," + task.getDescription() + "," + task.getStatus());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    private static void addTask(String description) {
        int id = tasks.isEmpty() ? 1 : tasks.get(tasks.size() - 1).getId() + 1;
        tasks.add(new Task(id, description, "todo"));
        System.out.println("Task added successfully (ID: " + id + ")");
    }

    private static void updateTask(int id, String description) {
        Task task = findTaskById(id);
        if (task != null) {
            task.setDescription(description);
            System.out.println("Task updated successfully.");
        } else {
            System.out.println("Task not found.");
        }
    }

    private static void deleteTask(int id) {
        Task task = findTaskById(id);
        if (task != null) {
            tasks.remove(task);
            System.out.println("Task deleted successfully.");
        } else {
            System.out.println("Task not found.");
        }
    }

    private static void markTaskStatus(int id, String status) {
        Task task = findTaskById(id);
        if (task != null) {
            task.setStatus(status);
            System.out.println("Task marked as " + status + ".");
        } else {
            System.out.println("Task not found.");
        }
    }

    private static Task findTaskById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    private static void listTasks(String status) {
        boolean found = false;
        for (Task task : tasks) {
            if (status == null || task.getStatus().equalsIgnoreCase(status)) {
                System.out.println(task);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No tasks found for status: " + status);
        }
    }

    static class Task {
        private int id;
        private String description;
        private String status;

        public Task(int id, String description, String status) {
            this.id = id;
            this.description = description;
            this.status = status;
        }

        public int getId() {
            return id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return "ID: " + id + " | Description: " + description + " | Status: " + status;
        }
    }
}
