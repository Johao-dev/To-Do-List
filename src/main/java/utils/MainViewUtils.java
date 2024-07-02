package utils;

import model.domain.Category;
import model.domain.Task;
import view.MainView;

public class MainViewUtils {
    
    public static void printToTask(MainView view, Task task, Category category) {
        view.txtTasks.setText(task.getTaskName() + "          " + category.name() + "\n");
    }
    
    public static void appendToTask(MainView view, Task task, Category category) {
        view.txtTasks.append(task.getTaskName() + "          " + category.name() + "\n");
    }
}
