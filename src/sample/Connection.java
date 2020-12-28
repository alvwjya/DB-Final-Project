package sample;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Connection {
    public java.sql.Connection connection;
    private String url = "jdbc:mysql://localhost:3306/FinalProjectDB?serverTimezone=GMT";
    private String username = "root";
    private String password = "";

    // Class constructor of Connection class
    public Connection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Function that return prepStat
    public PreparedStatement getPrepStat(String query) {
        try {
            return connection.prepareStatement(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
