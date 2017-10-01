package fi.jamk.hawnin.solteqassignmentv1;

/**
 * Created by jmprs on 2017. 09. 25..
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 5;
    private static final String DATABASE_NAME = "SolteQ.db";
    public DataBase(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_EMPLOYEE = "CREATE TABLE " + Employee.TABLE + "(" +
                Employee.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                Employee.KEY_name + " TEXT, " +
                Employee.KEY_email + " TEXT, " +
                Employee.KEY_age + " INTEGER, " +
                Employee.KEY_salary + " INTEGER, " +
                Employee.KEY_position + " TEXT )";
        db.execSQL(CREATE_TABLE_EMPLOYEE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ Employee.TABLE);
        onCreate(db);
    }
}
