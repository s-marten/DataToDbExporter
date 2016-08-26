package ru.marten.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by marten on 06.08.16.
 */
public class ExcelParser {

    private static final String RIGHT_WORD = "right_word";
    private static final String SECOND_VARIANT = "second_variant";
    private static final String THIRD_VARIANT = "third_variant";

    public static HashSet<String[]> parseToArray(String filePath) {
        HashSet<String[]> result = new HashSet<String[]>();
        InputStream inputStream = null;
        HSSFWorkbook workBook = null;

        try {
            inputStream = new FileInputStream(filePath);
            workBook = new HSSFWorkbook(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Sheet sheet = workBook.getSheetAt(0);
        Iterator<Row> iterator = sheet.iterator();

        while (iterator.hasNext()) {
            Row row = iterator.next();
            String[] res = new String[3];
            int i = 0;
            Iterator<Cell> cells = row.iterator();
            while (cells.hasNext()) {
                Cell cell = cells.next();
                res[i] = cell.getStringCellValue();
                i++;
            }
            if (res != null) {
                result.add(res);
            }
        }


        return result;
    }

    public static HashSet<JSONObject> parseToJSON (String filePath) {
        HashSet<JSONObject> result = new HashSet<>();
        InputStream inputStream = null;
        HSSFWorkbook workbook = null;

        try {
            inputStream = new FileInputStream(filePath);
            workbook = new HSSFWorkbook(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (workbook != null) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();
                String[] res = new String[3];
                int i = 0;
                Iterator<Cell> cells = row.iterator();
                while (cells.hasNext()) {
                    Cell cell = cells.next();
                    res[i] = cell.getStringCellValue();
                    i++;
                }

                try {
                    if(res != null) {
                        JSONObject object = new JSONObject();
                        object.put(RIGHT_WORD, res[0]);
                        object.put(SECOND_VARIANT, res[1]);
                        object.put(THIRD_VARIANT, res[2]);

                        result.add(object);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }


        return result;
    }
}
