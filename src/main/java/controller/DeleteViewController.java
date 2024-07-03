package controller;

import java.util.*;
import javax.swing.JOptionPane;
import model.dao.implement.TaskDaoImpl;
import model.dao.interfaces.TaskDao;
import model.domain.*;
import model.exceptions.TaskNotFoundException;
import view.*;

public class DeleteViewController {
    
    MainView main;
    DeleteTaskView deleteView;
    Task task;
    Category category;
    TaskDao taskDao;
    
    public DeleteViewController(MainView main, DeleteTaskView deleteView, Task task, Category category, TaskDaoImpl taskDao) {
        this.main = main;
        this.deleteView = deleteView;
        this.task = task;
        this.category = category;
        this.taskDao = taskDao;
        configListeners();
    }
    
    private void configListeners() {
        this.deleteView.btnDelete.addActionListener(e -> btnDeleteTaskActionPerformed());
        this.deleteView.btnCancel.addActionListener(e -> btnCancelActionPerformed());
        //event when item has changed
        this.deleteView.selectTaskName.addItemListener(e -> itemStateChanged());
    }

    private void btnDeleteTaskActionPerformed() {
        String taskSelected = (String)this.deleteView.selectTaskName.getSelectedItem();
        List<Task> tasks = taskDao.getAllTasks();
        
        ListIterator itrTasks = tasks.listIterator();
        while(itrTasks.hasNext()) {
            task = (Task)itrTasks.next();
            if (task.getTaskName().equals(taskSelected)) {
                try {
                    taskDao.deleteTask(task);
                } catch (TaskNotFoundException ex) {
                    System.out.println(ex.getMessage());
                    ex.printStackTrace(System.out);
                }
                JOptionPane.showMessageDialog(null, "Tarea eliminada con exito.", "INFO", JOptionPane.INFORMATION_MESSAGE);
                this.deleteView.dispose();
                //cleaning
                this.deleteView.selectTaskName.removeAllItems();
                this.main.setVisible(true);
                break;
            }
        }
    }

    private void btnCancelActionPerformed() {
        this.deleteView.selectTaskName.removeAllItems();
        this.deleteView.dispose();
        this.main.setVisible(true);
    }

    private void itemStateChanged() {
        String taskSelected = (String)this.deleteView.selectTaskName.getSelectedItem();
        List<Task> tasks = taskDao.getAllTasks();
        
        ListIterator itrTasks = tasks.listIterator();
        while(itrTasks.hasNext()) {
            task = (Task)itrTasks.next();
            if (task.getTaskName().equals(taskSelected)) {
                this.deleteView.TaskCategory.setText(task.getCategory().getCategoryName());
                this.deleteView.TaskDescription.setText(task.getTaskDescription());
                break;
            }
        }
    }
}
