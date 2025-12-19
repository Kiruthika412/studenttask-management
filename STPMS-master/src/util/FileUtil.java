package util;

import service.TaskManager;
import java.io.*;

public class FileUtil {
    public static void saveTaskManager(TaskManager manager, String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(manager);
        }
    }

    public static TaskManager loadTaskManager(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (TaskManager) ois.readObject();
        }
    }
}
