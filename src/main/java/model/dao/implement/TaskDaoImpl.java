package model.dao.implement;

import java.sql.*;
import model.dao.interfaces.TaskDao;
import model.database.Conexion;
import model.domain.Task;
import model.exceptions.*;

public class TaskDaoImpl implements TaskDao {

    private Connection conn;
    private PreparedStatement st;
    private ResultSet rs;
    
    @Override
    public void addTask(Task task) {
        try {
            if (validateFields(task)) {
                conn = getConnection();
                String query = "INSERT INTO task(TaskName,TaskDescription,CategoryId) values(?,?,?)";
                st = conn.prepareStatement(query);
                st.setString(1, task.getTaskName());
                st.setString(2, task.getTaskDescription());
                st.setInt(3, task.getCategory().getCategoryId());
                st.executeUpdate();
                commit(conn);
            }
        } catch (FieldsNotCompletedException | SQLException ex) {
            rollback(conn);
            System.out.println(ex.getMessage());
            ex.printStackTrace(System.out);
        } finally {
            closeSources();
        }
    }

    @Override
    public void deleteTask(Task task) {
        try {
            if (taskFound(task)) {
                conn = getConnection();
                String query = "DELETE FROM task WHERE taskID = ?";
                st = conn.prepareStatement(query);
                st.setInt(1, task.getTaskId());
                st.executeQuery();
                commit(conn);
            }
        } catch (TaskNotFoundException | SQLException ex) {
            rollback(conn);
            System.out.println(ex.getMessage());
            ex.printStackTrace(System.out);
        } finally {
            closeSources();
        }
    }

    @Override
    public void updateTask(Task task) {
        try {
            if (taskFound(task)) {
                conn = getConnection();
                String query = "UPDATE task SET TaskName=?, TaskDescription=?, CategoryId=? WHERE TaskID=?";
                st = conn.prepareStatement(query);
                st.setString(1, task.getTaskName());
                st.setString(2, task.getTaskDescription());
                st.setInt(3, task.getCategory().getCategoryId());
                st.setInt(4, task.getTaskId());
                st.executeUpdate();
                commit(conn);
            }
        } catch (TaskNotFoundException | SQLException ex) {
            rollback(conn);
            System.out.println(ex.getMessage());
            ex.printStackTrace(System.out);
        } finally {
            closeSources();
        }
    }

    @Override
    public boolean searchTask(Task task) {
        boolean founded = false;
        try {
            if (taskFound(task)) 
                founded = true;
        } catch (TaskNotFoundException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace(System.out);
        } finally {
            closeSources();
        }
        return founded;
    }
    
    private Connection getConnection() throws SQLException {
        this.conn = Conexion.getConexion();
        this.conn.setAutoCommit(false); 
        return this.conn;
    }
    
    private void closeSources() {
        try {
            if (this.st != null)
                Conexion.close(st);
            
            if (this.rs != null)
                Conexion.close(rs);
            
            if (this.conn != null) {
                this.conn.setAutoCommit(true);
                Conexion.close(this.conn);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    private void commit(Connection conn) {
        try {
            if (conn != null)
                conn.commit();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    private void rollback(Connection conn) {
        try {
            if (conn != null)
                conn.rollback();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    private boolean validateFields(Task task) throws FieldsNotCompletedException {
        boolean validatedFields = true;
        
        if (task.getTaskName() == null || task.getTaskName().isEmpty() 
                || task.getCategory() == null || task.getCategory().getCategoryId() == 0) {
            throw new FieldsNotCompletedException("Campos no completados.");
        }
        return validatedFields;
    }
    
    private boolean taskFound(Task task) throws TaskNotFoundException {
        boolean taskFound = false;
        try {
            conn = getConnection();
            String query = "SELECT * FROM task";
            st = conn.prepareStatement(query);
            rs = st.executeQuery();
            while (rs.next()) {
                if (!rs.getString("TaskName").equals(task.getTaskName())
                        && rs.getInt("CategoryID") != task.getCategory().getCategoryId()) {
                    throw new TaskNotFoundException("Tarea no encontrada.");
                } else
                    taskFound = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return taskFound;
    }
}
