package controller;

import java.util.List;
import java.util.ListIterator;
import model.dao.implement.TaskDaoImpl;
import model.domain.*;
import utils.MainViewUtils;
import view.*;

public class MainViewController {
    
    MainView main;
    AddTaskView addView;
    DeleteTaskView deleteView;
    UpdateTaskView updateView;
    SearchTaskView searchView;
    Task task;
    Category category;
    TaskDaoImpl taskDao;

    public MainViewController(MainView main, AddTaskView addView, DeleteTaskView deleteView, UpdateTaskView updateView, SearchTaskView searchView, Task task, Category category) {
        this.main = main;
        this.addView = addView;
        this.deleteView = deleteView;
        this.updateView = updateView;
        this.searchView = searchView;
        this.task = task;
        this.category = category;
        this.taskDao = new TaskDaoImpl();
        configListeners();
    }

    private void configListeners() {
        this.main.btnAddTask.addActionListener(e -> btnAddTaskActionPerformed());
        this.main.btnListTasks.addActionListener(e -> btnListTasksActionPerfomed());
        this.main.btnDeleteTask.addActionListener(e -> btnDeleteTaskActionPerformed());
        this.main.btnUpdateTask.addActionListener(e -> btnUpdateTaskActionPerformed());
        this.main.btnSearchTask.addActionListener(e -> btnSearchTaskActionPerformed());
    }

    private void btnAddTaskActionPerformed() {
        this.addView.setVisible(true);
    }

    private void btnListTasksActionPerfomed() {
        this.main.txtTasks.setText("");
        List<Task> tasks = taskDao.getAllTasks();
        ListIterator itrTasks = tasks.listIterator();
        while (itrTasks.hasNext()) {
            Task unitTask = (Task) itrTasks.next();
            if (this.main.txtTasks.getText().isEmpty())
                MainViewUtils.printToTask(main, unitTask, unitTask.getCategory());
            else
                MainViewUtils.appendToTask(main, unitTask, unitTask.getCategory());
        }
    }

    private void btnDeleteTaskActionPerformed() {
        this.deleteView.setVisible(true);
        List<Task> tasks = taskDao.getAllTasks();
        ListIterator itrTasks = tasks.listIterator();
        while (itrTasks.hasNext()) {
            String taskName = ((Task)itrTasks.next()).getTaskName();
            this.deleteView.selectTaskName.addItem(taskName);
        }
    }

    private void btnUpdateTaskActionPerformed() {
        this.updateView.setVisible(true);
        List<Task> tasks = taskDao.getAllTasks();
        ListIterator itrTasks = tasks.listIterator();
        while (itrTasks.hasNext()) {
            String taskName = ((Task)itrTasks.next()).getTaskName();
            this.updateView.selectTaskName.addItem(taskName);
        }
    }

    private void btnSearchTaskActionPerformed() {
        this.searchView.setVisible(true);
    }
}
