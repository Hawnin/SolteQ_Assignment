package fi.jamk.hawnin.solteqassignmentv1;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends ListActivity implements android.view.View.OnClickListener{
    Button Add,GetAll,SearchAndReport;
    TextView employee_Id;

    @Override
    public void onClick(View view) {
        if (view== findViewById(R.id.Add)){

            Intent intent = new Intent(this,Detail.class);
            intent.putExtra("employee_Id",0);
            startActivity(intent);

        }else if(view==findViewById(R.id.GetAll)){

            EmployeeUpdate update = new EmployeeUpdate(this);

            final ArrayList<HashMap<String, String>> employeeList =  update.getEmployeeList();
            if(employeeList.size()!=0) {
                ListView lv = getListView();
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                        employee_Id = (TextView) view.findViewById(R.id.employee_id);
                        String employeeid = employee_Id.getText().toString();
                        Intent objIndent = new Intent(getApplicationContext(),Detail.class);
                        objIndent.putExtra("employee_Id", Integer.parseInt( employeeid));
                        startActivity(objIndent);
                    }
                });
                ListAdapter adapter = new SimpleAdapter( MainActivity.this,employeeList, R.layout.view_employee, new String[] { "id","name","salary","position"}, new int[] {R.id.employee_id, R.id.employee_name, R.id.employee_salary, R.id.employee_position});
                setListAdapter(adapter);
            }else{
                Toast.makeText(this,"No employee!",Toast.LENGTH_SHORT).show();
            }

        }else if(view==findViewById(R.id.SearchAndReport)){
            Intent intent = new Intent(this,SearchDialog.class);
            startActivity(intent);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Add = (Button) findViewById(R.id.Add);
        GetAll = (Button) findViewById(R.id.GetAll);
        SearchAndReport = (Button) findViewById(R.id.SearchAndReport);

        Add.setOnClickListener(this);
        GetAll.setOnClickListener(this);
        SearchAndReport.setOnClickListener(this);

    }
}
