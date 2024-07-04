package controller;

import java.util.List;
import java.util.ListIterator;
import javax.swing.JOptionPane;
import model.dao.implement.TaskDaoImpl;
import model.domain.*;
import view.*;

public class UpdateViewController {
    
    MainView main;
    UpdateTaskView updateView;
    Task task;
    Category category;
    TaskDaoImpl taskDao;

    public UpdateViewController(MainView main, UpdateTaskView updateView, Task task, Category category, TaskDaoImpl taskDao) {
        this.main = main;
        this.updateView = updateView;
        this.task = task;
        this.category = category;
        this.taskDao = taskDao;
        configListeners();
    }

    private void configListeners() {
        this.updateView.btnModify.addActionListener(e -> btnModifyTaskActionPerformed());
        this.updateView.btnCancel.addActionListener(e -> btnCancelActionPerformed());
        //event when item has changed
        this.updateView.selectTaskName.addItemListener(w -> nameItemStateChanged());
        //when category is changing
        this.updateView.changeTaskCategory.addItemListener(e -> categoryItemStateChanged());
    }

    private void btnModifyTaskActionPerformed() {
        String taskSelected = (String)this.updateView.selectTaskName.getSelectedItem();
        List<Task> tasks = taskDao.getAllTasks();
        
        ListIterator itrTasks = tasks.listIterator();
        while(itrTasks.hasNext()) {
            task = (Task)itrTasks.next();
            
            if(task.getTaskName().equals(taskSelected)) {
                 category = categoryItemStateChanged();
                 String newTaskDescription = this.updateView.changeTaskDescription.getText();
                 int taskId = task.getTaskId();
                 
                 Task taskToUpdate = new Task(taskId, task.getTaskName(), newTaskDescription, category);                 
                 
                 taskDao.updateTask(taskToUpdate);
                 
                 JOptionPane.showMessageDialog(null, "Tarea actualizada con exito.", "INFO", JOptionPane.INFORMATION_MESSAGE);
                 this.updateView.dispose();
                 this.updateView.selectTaskName.removeAllItems();
                 this.updateView.changeTaskDescription.setText("");
                 this.main.setVisible(true);
                 break;
            }
        }
    }

    private void btnCancelActionPerformed() {
        this.updateView.dispose();
        //cleaning
        this.updateView.selectTaskName.removeAllItems();
        this.updateView.changeTaskDescription.setText("");
        this.main.setVisible(true);
    }

    private void nameItemStateChanged() {
        String taskSelected = (String)this.updateView.selectTaskName.getSelectedItem();
        List<Task> tasks = taskDao.getAllTasks();
        
        ListIterator itrTasks = tasks.listIterator();
        while(itrTasks.hasNext()) {
            task = (Task)itrTasks.next();
            if (task.getTaskName().equals(taskSelected)) {
                this.updateView.changeTaskCategory.setSelectedIndex(task.getCategory().getCategoryId());
                this.updateView.changeTaskDescription.setText(task.getTaskDescription());
                break;
            }
        }
    }

    private Category categoryItemStateChanged() {
        int taskCategory = this.updateView.changeTaskCategory.getSelectedIndex();
        
        switch(taskCategory) {
            case 1 -> { category = Category.NORMAL;}
            case 2 -> { category = Category.IMPORTANTE;}
            case 3 -> { category = Category.URGENTE;}
        
        }
        return category;
    }
}
