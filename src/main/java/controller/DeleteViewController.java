package controller;

import java.util.*;
import model.dao.implement.TaskDaoImpl;
import model.dao.interfaces.TaskDao;
import model.domain.*;
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
    }

    private void btnDeleteTaskActionPerformed() {
        String taskSelected = (String)this.deleteView.selectTaskName.getSelectedItem();
        List<Task> tasks = taskDao.getAllTasks();
        
        ListIterator itrTasks = tasks.listIterator();
        while(itrTasks.hasNext()) {
            String taskName = ((Task)itrTasks.next()).getTaskName();
            
            if (taskName.equals(taskSelected)) {
                
                String categoryName = ((Task)itrTasks.next()).getCategory().getCategoryName();
                //error que solucionar aqui xd
                String taskDescription = ((Task)itrTasks.next()).getTaskDescription();
                
                this.deleteView.TaskCategory.setText(categoryName);
                this.deleteView.TaskDescription.setText(taskDescription);
                break;
            }
        }
    }

    private void btnCancelActionPerformed() {
        this.deleteView.selectTaskName.removeAllItems();
        this.deleteView.dispose();
        this.main.setVisible(true);
    }
}
