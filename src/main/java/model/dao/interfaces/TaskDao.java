package model.dao.interfaces;

import java.util.List;
import model.domain.Task;
import model.exceptions.FieldsNotCompletedException;
import model.exceptions.TaskNotFoundException;

public interface TaskDao {
    
    void addTask(Task task) throws FieldsNotCompletedException;
    
    void deleteTask(Task task) throws TaskNotFoundException;
    
    void updateTask(Task task) throws TaskNotFoundException;
    
    boolean searchTask(Task task) throws TaskNotFoundException;
    
    List<Task> getAllTasks();
}
