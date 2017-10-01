package fi.jamk.hawnin.solteqassignmentv1;

/**
 * Created by jmprs on 2017. 09. 25..
 */
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.HashMap;

public class EmployeeUpdate extends Activity {

    long SalarySpent=0;

    private DataBase database;
    public EmployeeUpdate(Context context){
        database = new DataBase(context);
    }

    public int insert(Employee employee) {

        SQLiteDatabase db = database.getWritableDatabase(); //opened for writing
        ContentValues values = new ContentValues();
        values.put(Employee.KEY_name, employee.getName());
        values.put(Employee.KEY_email,employee.getEmail());
        values.put(Employee.KEY_age, employee.getAge());
        values.put(Employee.KEY_salary, employee.getSalary());
        values.put(Employee.KEY_position, employee.getPosition());

        long employee_id = db.insert(Employee.TABLE, null, values);
        db.close();
        return (int) employee_id;
    }

    public void delete(String Name) {
        SQLiteDatabase db = database.getWritableDatabase();
        db.delete(Employee.TABLE, Employee.KEY_name + "= ?",new String[]{Name});
        db.close();
    }

    public void update(Employee employee) {

        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Employee.KEY_name, employee.getName());
        values.put(Employee.KEY_email,employee.getEmail());
        values.put(Employee.KEY_age, employee.getAge());
        values.put(Employee.KEY_salary, employee.getSalary());
        values.put(Employee.KEY_position, employee.getPosition());
        db.update(Employee.TABLE, values, Employee.KEY_name + "= ?",
                new String[] { employee.getName()});
        db.close();
    }

    public ArrayList<HashMap<String, String>> getEmployeeList() {
        //Open connection to read only
        SQLiteDatabase db = database.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Employee.KEY_ID + "," +
                Employee.KEY_name + "," +
                Employee.KEY_email + "," +
                Employee.KEY_age + "," +
                Employee.KEY_salary + "," +
                Employee.KEY_position +
                " FROM " + Employee.TABLE;

        ArrayList<HashMap<String, String>> employeeList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> employee = new HashMap<String, String>();
                employee.put("id", cursor.getString(cursor.getColumnIndex(Employee.KEY_ID)));
                employee.put("name", cursor.getString(cursor.getColumnIndex(Employee.KEY_name)));
                employee.put("age", cursor.getString(cursor.getColumnIndex(Employee.KEY_age)));
                employee.put("salary", cursor.getString(cursor.getColumnIndex(Employee.KEY_salary)));
                employee.put("position", cursor.getString(cursor.getColumnIndex(Employee.KEY_position)));
                employeeList.add(employee);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return employeeList;

    }
    /*
    public void SalarySpent(){
        SalarySpent=0;
        String SQSalary="SELECT salary FROM Employee";
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor SQCursor = db.rawQuery(SQSalary, null);
        if (SQCursor.moveToFirst()) {
            do {
                SalarySpent+=Long.parseLong(SQCursor.getString(SQCursor.getColumnIndex(Employee.KEY_salary)));
            } while (SQCursor.moveToNext());
        }
        Intent SLRY = new Intent(getApplicationContext(),SearchAndReport.class);
        SLRY.putExtra("SalarySpent",String.valueOf(SalarySpent));
        SQCursor.close();
        db.close();

    }
*/

    public ArrayList<HashMap<String, String>> getSortedEmployeelist(boolean name, boolean age, boolean salary,
        boolean position,boolean report, String ASCDESC, boolean searchTF, String searchAttribute){

        String sql = "SELECT  id,name,age,salary,position FROM Employee";
        SalarySpent=0;
        /*----------------------------*/
        if (searchTF && searchAttribute != null) {
            sql += " WHERE name =? ";
            //sql += searchAttribute;
        }
        if (name || age || salary || position) {
            sql += " ORDER BY ";

            if (name) {
                sql += "name";
            }else
            if (age) {
                sql += "age";
            }else
            if (salary) {
                sql += "salary";
            }else
            if (position) {
                sql += "position";
            }
            if (ASCDESC=="ASC"){
                sql +=" ASC";
            }else{
                sql +=" DESC";
            }

        }
        if (report){
            //SalarySpent();
        }
        /*-----------------------------------------------*/
        SQLiteDatabase db = database.getReadableDatabase();
        /*
            String selectQuery = "SELECT  " +
                Employee.KEY_ID + "," +
                Employee.KEY_name + "," +
                Employee.KEY_email + "," +
                Employee.KEY_age + "," +
                Employee.KEY_salary + "," +
                Employee.KEY_position +
                " FROM " + Employee.TABLE;
            */
        ArrayList<HashMap<String, String>> employeeList = new ArrayList<HashMap<String, String>>();
        Cursor cursor = db.rawQuery(sql, null);


        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> employee = new HashMap<String, String>();
                employee.put("id", cursor.getString(cursor.getColumnIndex(Employee.KEY_ID)));
                employee.put("name", cursor.getString(cursor.getColumnIndex(Employee.KEY_name)));
                employee.put("age", cursor.getString(cursor.getColumnIndex(Employee.KEY_age)));
                employee.put("salary", cursor.getString(cursor.getColumnIndex(Employee.KEY_salary)));
                //SalarySpent+=Long.parseLong(employee.put("salary", cursor.getString(cursor.getColumnIndex(Employee.KEY_salary))));
                employee.put("position", cursor.getString(cursor.getColumnIndex(Employee.KEY_position)));
                employeeList.add(employee);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return employeeList;
    }
}
