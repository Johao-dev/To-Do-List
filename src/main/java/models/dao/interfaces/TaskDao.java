package models.dao.interfaces;

import models.domain.Task;
import models.exceptions.FieldsNotCompletedException;
import models.exceptions.TaskNotFoundException;

public interface TaskDao {
    
    void addTask(Task task) throws FieldsNotCompletedException;
    
    void deleteTask(Task task) throws TaskNotFoundException;
    
    void updateTask(Task task) throws TaskNotFoundException;
    
    boolean searchTask(Task task) throws TaskNotFoundException;
}
