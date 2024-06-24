package models.dao.implement;

import java.sql.*;
import models.dao.interfaces.TaskDao;
import models.database.Conexion;
import models.domain.Task;

public class TaskDaoImpl implements TaskDao {

    Connection conn;
    PreparedStatement st;
    ResultSet rs;
    
    @Override
    public void addTask(Task task) {
        
    }

    @Override
    public void deleteTask(Task task) {

    }

    @Override
    public void updateTask(Task task) {

    }

    @Override
    public void searchTask(Task task) {

    }
}
