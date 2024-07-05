
import controller.*;
import model.dao.implement.TaskDaoImpl;
import model.dao.interfaces.TaskDao;
import model.domain.*;
import view.*;

public class ToDoList {

    public static void main(String[] args) {
        //models
        Task task = new Task();
        TaskDao taskDao = new TaskDaoImpl();
        //default category
        Category category = Category.NORMAL;

        //views
        MainView main = new MainView();
        AddTaskView addTask = new AddTaskView();
        DeleteTaskView deleteView = new DeleteTaskView();
        UpdateTaskView updateView = new UpdateTaskView();
        SearchTaskView searchView = new SearchTaskView();

        //controllers
        MainViewController mainCtrl = new MainViewController(main, addTask, deleteView, updateView, searchView, task, category);
        AddTaskController addTaskCtrl = new AddTaskController(main, addTask, task, category, (TaskDaoImpl) taskDao);
        DeleteViewController deleteTaskCtrl = new DeleteViewController(main, deleteView, task, category, (TaskDaoImpl) taskDao);
        UpdateViewController updateTaskCtrl = new UpdateViewController(main, updateView, task, category, (TaskDaoImpl) taskDao);
        SearchViewController searchTaskCtrl = new SearchViewController(main, searchView, task, category, (TaskDaoImpl) taskDao);

        //views with their controllers
        main.setController(mainCtrl);
        addTask.setController(addTaskCtrl);
        deleteView.setController(deleteTaskCtrl);
        updateView.setController(updateTaskCtrl);
        searchView.setController(searchTaskCtrl);

        //init
        main.setVisible(true);
    }
}
