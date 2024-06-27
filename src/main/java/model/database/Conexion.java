package model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexion {
    
    private static final String URL = "jdbc:mysql://localhost:3306/todolistapp";
    private static final String USER = "root";
    private static final String PASSWORD = "mSd_:$AA4ds/_s-#";
    
    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    public static void close(ResultSet result) throws SQLException {
        result.close();
    }
    
    public static void close(PreparedStatement prstmt) throws SQLException {
        prstmt.close();
    }
    
    public static void close(Connection conn) throws SQLException {
        conn.close();
    }
}
