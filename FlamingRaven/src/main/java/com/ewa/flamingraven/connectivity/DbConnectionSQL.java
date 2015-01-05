package com.ewa.flamingraven.connectivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Team V
 */
public class DbConnectionSQL {

    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String DBURL = "jdbc:mysql://localhost:3306/firefalcon";
    public static final String DBUSER = "root";
    public static final String DBPASS = "root";

    private ResultSet result = null;
    private int affectedRows = -1;
    Connection conn = null;

    /**
     *
     */
    public DbConnectionSQL() {
    }

    /**
     * this starts the database connection
     */
    public void startConnection() {
        try {

            Class.forName(DRIVER);
            DriverManager.setLoginTimeout(5);
            conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * this closes the database connection
     */
    public void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        conn = null;
    }

    /**
     *
     * @param prdstmt
     * @return the result of the query
     * @throws SQLException
     */
    public ResultSet performSelect(PreparedStatement prdstmt) throws SQLException {
        result = prdstmt.executeQuery();

        return result;
    }

    /**
     *
     * @param prdstmt
     * @return the amount of effected rows
     * @throws SQLException
     */
    public int performUpdate(PreparedStatement prdstmt) throws SQLException {

        affectedRows = prdstmt.executeUpdate();

        return affectedRows;
    }

    /**
     *
     * @return the connection
     */
    public Connection getConnection() {
        return conn;
    }

}
