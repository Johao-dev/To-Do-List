package models.dao.implement;

import java.sql.*;
import models.dao.interfaces.TaskDao;
import models.database.Conexion;
import models.domain.Task;
import models.exceptions.FieldsNotCompletedException;

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

    }

    @Override
    public void updateTask(Task task) {

    }

    @Override
    public void searchTask(Task task) {

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
            validatedFields = false;
            throw new FieldsNotCompletedException("Campos no completados!");
        }
        return validatedFields;
    }
}
