package ru.marten.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.json.JSONObject;
import ru.marten.dbmanagment.SQLiteJDBC;
import ru.marten.dbmanagment.SQLiteQuestJDBC;
import ru.marten.utils.ExcelParser;

public class Main {

    private static final String FILE_KEY = "file_key";
    private static final String DB_KEY = "db_key";

    private static String sFilePath;
    private static String sDbPath;

    public static void main(String[] args) {

        Map<String, String> map = initFilesToWork();
        if (map != null) {
            sFilePath = map.get(FILE_KEY);
            sDbPath = map.get(DB_KEY);
        }

        System.out.println("input data = " + sFilePath + ", " + sDbPath);
//        SQLiteJDBC.createDB();
//        HashSet<String[]> res = ExcelParser.parseToArray(sFilePath);
//        for (String[] cell : res) {
//            SQLiteJDBC.insertData(cell);
//        }
        SQLiteQuestJDBC.createDB(sDbPath);
        HashSet<JSONObject> res = ExcelParser.parseToJSON(sFilePath);
        for (JSONObject cell : res) {
            SQLiteQuestJDBC.insertData(cell.toString());
        }

    }

    private static Map initFilesToWork() {

        String tempData;
        Map<String, String> mRes = new HashMap<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter file name to import from:");
        try {
            tempData = reader.readLine();
            if (tempData != null) {
                mRes.put(FILE_KEY, tempData);
            } else {
                System.out.println("file name is empty.");
                return null;
            }
            tempData = null;
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Enter database name to put data:");
        try {
            tempData = reader.readLine();
            if (tempData != null) {
                mRes.put(DB_KEY, tempData);
            } else {
                System.out.println("db name is empty.");
                return null;
            }
            tempData = null;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mRes;
    }

}
