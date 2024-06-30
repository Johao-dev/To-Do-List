package utils;

import model.domain.Category;
import model.domain.Task;
import view.AddTaskView;

public class AddTaskViewUtils {
    
    public static boolean validateFields(AddTaskView view, Task task, Category cat) {
        boolean fieldsValidated = true;
        
        if (view.TaskName.getText().isEmpty() || view.TaskName.getText().trim().equals("")) {
            fieldsValidated = false;
        }
        
        switch (view.TaskCategory.getSelectedIndex()) {
            case -1 -> {
                fieldsValidated = false;
            }
            case 0 -> {
                fieldsValidated = false;
            }
        }
        
        return fieldsValidated;
    }
    
    public static void getFields(AddTaskView view, Task task, Category cat) {
        task.setTaskName(view.TaskName.getText());
        task.setTaskDescription(view.TaskDescription.getText());
        
        switch (view.TaskCategory.getSelectedIndex()) {
            case 1 -> {
                task.setCategory(Category.NORMAL);
            }
            case 2 -> {
                task.setCategory(Category.IMPORTANTE);
            }
            case 3 -> {
                task.setCategory(Category.URGENTE);
            }
        }
    }
}
