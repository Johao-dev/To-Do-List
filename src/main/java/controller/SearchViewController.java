package controller;

import java.util.List;
import java.util.ListIterator;
import model.dao.implement.TaskDaoImpl;
import model.dao.interfaces.TaskDao;
import model.domain.*;
import view.*;

public class SearchViewController {
    
    MainView main;
    SearchTaskView searchView;
    Task task;
    Category category;
    TaskDao taskDao;

    public SearchViewController(MainView main, SearchTaskView searchView, Task task, Category category, TaskDaoImpl taskDao) {
        this.main = main;
        this.searchView = searchView;
        this.task = task;
        this.category = category;
        this.taskDao = taskDao;
        configListeners();
    }

    private void configListeners() {
        this.searchView.btnExit.addActionListener(e -> btnExitActionPerformed());
        this.searchView.selectTaskCategory.addItemListener(e -> categoryItemStateChanged());
        this.searchView.selectTaskName.addItemListener(e -> taskNameItemStateChanged());
    }

    private void btnExitActionPerformed() {
        this.searchView.dispose();
        this.searchView.selectTaskName.removeAllItems();
        this.searchView.taskDescriptionFound.setText("");
        this.searchView.selectTaskCategory.setSelectedIndex(0);
        this.main.setVisible(true);
    }

    private void categoryItemStateChanged() {
        this.searchView.selectTaskName.removeAllItems();
        List<Task> tasks = taskDao.getAllTasks();
        ListIterator itrTasks = tasks.listIterator();
        
        while(itrTasks.hasNext()) {
            task = (Task)itrTasks.next();
            int indexSelected = this.searchView.selectTaskCategory.getSelectedIndex();
            
            switch(indexSelected) {
                case 1 -> { //NORMAL
                    if(task.getCategory().getCategoryId() == indexSelected) 
                        this.searchView.selectTaskName.addItem(task.getTaskName());
                }
                case 2 -> { //IMPORTANTE
                    if(task.getCategory().getCategoryId() == indexSelected) 
                        this.searchView.selectTaskName.addItem(task.getTaskName());
                }
                case 3 -> { //URGENTE
                    if(task.getCategory().getCategoryId() == indexSelected) 
                        this.searchView.selectTaskName.addItem(task.getTaskName());
                }
            }
        }
    }

    private void taskNameItemStateChanged() {
        this.searchView.taskDescriptionFound.setText("");
        String taskSelected = (String)this.searchView.selectTaskName.getSelectedItem();
        List<Task> tasks = taskDao.getAllTasks();
        ListIterator itrTasks = tasks.listIterator();
        
        while(itrTasks.hasNext()) {
            task = (Task)itrTasks.next();
            if (task.getTaskName().equals(taskSelected)) {
                this.searchView.taskDescriptionFound.setText(task.getTaskDescription());
                break;
            }
        }
    }
}
