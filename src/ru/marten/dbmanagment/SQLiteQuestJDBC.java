package ru.marten.dbmanagment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Created by marten on 25.07.16.
 */
public class SQLiteQuestJDBC {

    public static void createDB(String filePath) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");

//            c = DriverManager.getConnection("jdbc:sqlite:" + CommonConstants.DATABASE_NAME);
              c = DriverManager.getConnection("jdbc:sqlite:" + filePath);

            stmt = c.createStatement();
            String sql = "CREATE TABLE " + CommonConstants.TABLE_COMMON + " "+
                    "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                     CommonConstants.QUEST_COLUMN +  " TEXT NOT NULL)";

            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Database created successfully");
    }

    public static synchronized void insertData(String data) {
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
                    " ("+ CommonConstants.QUEST_COLUMN +
                     ") " +
                    "VALUES ('" + data + "');";
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
