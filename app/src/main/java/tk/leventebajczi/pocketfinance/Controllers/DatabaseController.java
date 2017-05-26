package tk.leventebajczi.pocketfinance.Controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DatabaseController extends SQLiteOpenHelper {
    private static String DB_PATH;
    private static String DB_NAME;

    private SQLiteDatabase myDataBase;
    private final Context myContext;

    public DatabaseController(Context context) {
        super(context, DB_NAME, null, 1);
        DB_PATH = context.getApplicationInfo().dataDir + "/database";
        DB_NAME = "table.sqlite";
        this.myContext = context;
        createDataBase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void createDataBase() {

        boolean dbExist = checkDataBase();

        if (dbExist) {

        } else {
            this.getReadableDatabase();

            try {

                copyDataBase();

            } catch(Exception e) {

                e.printStackTrace();

            }
        }

    }

    private boolean checkDataBase() {

        SQLiteDatabase checkDB = null;

        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        } catch(SQLiteException e) {

        }

        if (checkDB != null) {

            checkDB.close();

        }

        return checkDB != null ? true: false;
    }

    private void copyDataBase() throws IOException {

        InputStream myInput = myContext.getAssets().open(DB_NAME);

        String outFileName = DB_PATH + DB_NAME;

        OutputStream myOutput = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException {

        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);

    }

    @Override
    public synchronized void close() {

        if (myDataBase != null) myDataBase.close();

        super.close();

    }

    public List <String> select(String column, String table, String condition) {
        Cursor c = myDataBase.rawQuery("SELECT " + column + " FROM " + table + " WHERE " + condition + ";", null);
        c.moveToFirst();
        List <String> als = new ArrayList < >();
        for (int i = 0; i < c.getCount(); i++) {
            als.add(c.getString(c.getColumnIndex(column)));
        }
        return als;
    }

    public int update(String table, ContentValues values, String condition){
        return myDataBase.update(table, values, condition,null);
    }

    public int insert(String table, ContentValues values){
        return (int)myDataBase.insert(table, null, values);
    }

    public int delete(String table, String condition){
        return myDataBase.delete(table, condition,null);
    }

}