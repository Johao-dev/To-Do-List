package controller;

import javax.swing.JOptionPane;
import model.dao.implement.TaskDaoImpl;
import model.dao.interfaces.TaskDao;
import model.domain.Category;
import model.domain.Task;
import model.exceptions.FieldsNotCompletedException;
import static utils.AddTaskViewUtils.*;
import view.AddTaskView;
import view.MainView;

public class AddTaskController {
    
    private final AddTaskView addTaskView;
    private final MainView mainView;
    private final Task task;
    private final Category category;
    private final TaskDao taskDao;

    public AddTaskController(MainView mainView, AddTaskView view, Task task, Category category, TaskDaoImpl taskDaoImpl) {
        this.addTaskView = view;
        this.mainView = mainView;
        this.task = task;
        this.category = category;
        this.taskDao = taskDaoImpl;
        configListeners();
    }
    
    private void configListeners() {
        this.addTaskView.btnSave.addActionListener(e -> btnSaveActionPerfomed());
        this.addTaskView.btnCancel.addActionListener(e -> btnCancelActionPerfomed());
    }

    private void btnSaveActionPerfomed() {
        if(validateFields(addTaskView, task, category)){
            getFields(addTaskView, task, category);
            try {
                taskDao.addTask(task);
            } catch (FieldsNotCompletedException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ALERT", JOptionPane.ERROR_MESSAGE);
            }
            this.addTaskView.dispose();
            //cleaning
            this.addTaskView.TaskName.setText("");
            this.addTaskView.TaskDescription.setText("");
            this.addTaskView.TaskCategory.setSelectedIndex(0);
            this.mainView.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Debe llenar los campos", "ALERT", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnCancelActionPerfomed() {
        //cleaning
        this.addTaskView.TaskName.setText("");
        this.addTaskView.TaskDescription.setText("");
        this.addTaskView.TaskCategory.setSelectedIndex(0);
        
        this.addTaskView.dispose();
        this.mainView.setVisible(true);
    }
}
