package controller;

import model.domain.*;
import view.AddTaskView;
import view.DeleteTaskView;
import view.MainView;
import view.SearchTaskView;
import view.UpdateTaskView;

public class MainViewController {
    
    MainView main;
    AddTaskView addView;
    DeleteTaskView deleteView;
    UpdateTaskView updateView;
    SearchTaskView searchView;
    Task task;
    Category category;

    public MainViewController(MainView main, AddTaskView addView, DeleteTaskView deleteView, UpdateTaskView updateView, SearchTaskView searchView, Task task, Category category) {
        this.main = main;
        this.addView = addView;
        this.deleteView = deleteView;
        this.updateView = updateView;
        this.searchView = searchView;
        this.task = task;
        this.category = category;
        configListeners();
    }

    private void configListeners() {
        this.main.btnAddTask.addActionListener(e -> btnAddTaskActionPerformed());
        this.main.btnListTasks.addActionListener(e -> btnListTasksActionPerfomed());
    }

    private void btnAddTaskActionPerformed() {
        this.addView.setVisible(true);
    }

    private void btnListTasksActionPerfomed() {
        
    }
}
