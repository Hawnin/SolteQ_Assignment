package fi.jamk.hawnin.solteqassignmentv1;

import android.app.ActionBar;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by jmprs on 2017. 09. 25..
 */

public class Detail extends ActionBarActivity implements android.view.View.OnClickListener{

    Button btnSave ,  btnDelete, btnModify;
    Button btnClose;

    EditText editTextName;
    EditText editTextEmail;
    EditText editTextAge;
    EditText editTextSalary;
    EditText editTextPosition;

    private int _Employee_Id=0;

    EmployeeUpdate Update = new EmployeeUpdate(this);
    Employee employee = new Employee();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        btnSave = (Button) findViewById(R.id.Save);
        btnDelete = (Button) findViewById(R.id.Delete);
        btnClose = (Button) findViewById(R.id.Close);
        btnModify = (Button) findViewById(R.id.Modify);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextAge = (EditText) findViewById(R.id.editTextAge);
        editTextSalary = (EditText) findViewById(R.id.editTextSalary);
        editTextPosition = (EditText) findViewById(R.id.editTextPosition);

        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnClose.setOnClickListener(this);
        btnModify.setOnClickListener(this);


        //_Employee_Id =0;
        Intent intent = getIntent();
        _Employee_Id =intent.getIntExtra("Employee_Id", 0);//_Employee_id?
        Toast.makeText(this,String.valueOf(_Employee_Id), Toast.LENGTH_SHORT);
        editTextName.setText("");
        editTextEmail.setText("");
        editTextAge.setText((""));
        editTextSalary.setText((""));
        editTextPosition.setText("");
    }

    public boolean check(){
        if ((editTextAge.getText().toString()).length()!=0 && (editTextSalary.getText().toString()).length()!=0
                && editTextName.getText().toString().length()!=0 && editTextEmail.getText().toString().length()!=0
                && editTextPosition.getText().toString().length()!=0) {
            employee.setEmployee_id(_Employee_Id);
            employee.setName(editTextName.getText().toString());
            employee.setEmail(editTextEmail.getText().toString());
            employee.setAge(Integer.parseInt(editTextAge.getText().toString()));
            employee.setSalary(Integer.parseInt(editTextSalary.getText().toString()));
            employee.setPosition(editTextPosition.getText().toString());
            return true;
        }else{
            return false;
        }
    }

    public void onClick(View view) {
        check();

        if (view == findViewById(R.id.Save) && check()){
            //Toast.makeText(this, String.valueOf(_Employee_Id), Toast.LENGTH_LONG).show();
            if (_Employee_Id==0){
                _Employee_Id = Update.insert(employee);
                finish();
            }
        }else if (view == findViewById(R.id.Modify) && check()){
                Update.update(employee);
                finish();
        }else if (view==findViewById(R.id.Delete) && check()){
            Update.delete(employee.getName());
            finish();
        }else if (view== findViewById(R.id.Close)){
            finish();
        } else{
                Toast.makeText(this, "Please Complete The Form", Toast.LENGTH_LONG).show();
        }
    }
}
