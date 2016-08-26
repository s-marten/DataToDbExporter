package ru.marten.dbmanagment;

import java.sql.*;

/**
 * Created by marten on 25.07.16.
 */
public class SQLiteJDBC {

    public static void createDB() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + CommonConstants.DATABASE_NAME);

            stmt = c.createStatement();
            String sql = "CREATE TABLE " + CommonConstants.TABLE_COMMON + " "+
                    "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                     CommonConstants.RIGHT_ANSWER_COLUMN +  " TEXT NOT NULL, " +
                    CommonConstants.SECOND_VARIANT_COLUMN + " TEXT NOT NULL, " +
                    CommonConstants.THIRD_VARIANT_COLUMN  + " TEXT NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Database created successfully");
    }

    public static synchronized void insertData(String[] data) {
        Connection c = null;
        Statement stmt = null;

        if (data == null) {
            return;
        }

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + CommonConstants.DATABASE_NAME);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "INSERT INTO " + CommonConstants.TABLE_COMMON +
                    " ("+ CommonConstants.RIGHT_ANSWER_COLUMN + ", "+
                          CommonConstants.SECOND_VARIANT_COLUMN + ", "+
                          CommonConstants.THIRD_VARIANT_COLUMN + ") " +
                    "VALUES ('" + data[0]+ "', '"+ data[1]+ "', '" + data[2] + "');";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Record created successfully");
    }
}
