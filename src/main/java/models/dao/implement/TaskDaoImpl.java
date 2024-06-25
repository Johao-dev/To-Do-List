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
}
