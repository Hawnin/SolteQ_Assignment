package fi.jamk.hawnin.solteqassignmentv1;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;


/**
 * Created by jmprs on 2017. 09. 28..
 */

public class SearchAndReport extends ListActivity implements android.view.View.OnClickListener{
    Button btnClose, btnASC, btnDESC;
    //private DataBase database;
    ArrayList<HashMap<String, String>> employeeList=null;
    boolean name, age, salary, position, report, searchTF;
    String searchAttribute=null, Payment=null;
    TextView editTextViewSalary;

    EmployeeUpdate EmployeeSearch = new EmployeeUpdate(this);

    @Override
    public void onClick(View view) {
        if(view==findViewById(R.id.ButtonClose)){
            finish();
        }
        if (view==findViewById(R.id.ButtonSortASC)){
            employeeList=EmployeeSearch.getSortedEmployeelist(name,age,salary,position,report,"ASC",searchTF,searchAttribute);
            //Orderby();
            if(employeeList.size()!=0) {
                ListView lv = getListView();
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                        Intent objIndent = new Intent(getApplicationContext(),Detail.class);
                        startActivity(objIndent);
                    }
                });
                ListAdapter adapter = new SimpleAdapter( SearchAndReport.this,employeeList, R.layout.view_employee, new String[] { "id","name","salary","position"}, new int[] {R.id.employee_id, R.id.employee_name, R.id.employee_salary, R.id.employee_position});
                setListAdapter(adapter);
            }
            //Payment = getIntent().getStringExtra("SalarySpent");
        }
        if (view==findViewById(R.id.ButtonSortDESC)){
            employeeList=EmployeeSearch.getSortedEmployeelist(name,age,salary,position,report,"DESC",searchTF,searchAttribute);


            if(employeeList.size()!=0) {
                ListView lv = getListView();
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                        Intent objIndent = new Intent(getApplicationContext(),Detail.class);
                        startActivity(objIndent);
                    }
                });
                ListAdapter adapter = new SimpleAdapter( SearchAndReport.this,employeeList, R.layout.view_employee, new String[] { "id","name","salary","position"}, new int[] {R.id.employee_id, R.id.employee_name, R.id.employee_salary, R.id.employee_position});
                setListAdapter(adapter);
            }
            //Payment = getIntent().getStringExtra("SalarySpent");
            //Orderby();
        }

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchandreport);
        //new Layout needed there will be a listview, and a report about the spent money
        btnClose = (Button) findViewById(R.id.ButtonClose);
        btnASC = (Button) findViewById(R.id.ButtonSortASC);
        btnDESC = (Button) findViewById(R.id.ButtonSortDESC);

        btnClose.setOnClickListener(this);
        btnASC.setOnClickListener(this);
        btnDESC.setOnClickListener(this);

        name = getIntent().getBooleanExtra("Name",false);
        age = getIntent().getBooleanExtra("Age",false);
        position = getIntent().getBooleanExtra("Position",false);
        salary = getIntent().getBooleanExtra("Salary",false);
        report = getIntent().getBooleanExtra("Report",false);
        searchTF = getIntent().getBooleanExtra("Search",false);

        if (searchTF){
            searchAttribute = getIntent().getStringExtra("Search Criteria");
        }

    }
}