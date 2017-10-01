package fi.jamk.hawnin.solteqassignmentv1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by jmprs on 2017. 09. 28..
 */

public class SearchDialog extends Activity implements android.view.View.OnClickListener {
    Button ok,cancell;
    CheckBox checkboxName,checkboxAge,checkboxSalary,checkboxPosition,checkboxReport,checboxSearch;
    EditText editTextSearch;
    boolean Name,Age,Salary,Position,Report,Search;



    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchdialog);
        /*---------------------------------*/
        ok = (Button) findViewById(R.id.buttonOk);
        cancell = (Button) findViewById(R.id.buttonCancel);
        /*---------------------------------*/
        ok.setOnClickListener(this);
        cancell.setOnClickListener(this);
        /*---------------------------------*/
        checkboxName = (CheckBox) findViewById(R.id.checkBoxName);
        checkboxAge = (CheckBox) findViewById(R.id.checkBoxAge);
        checkboxSalary = (CheckBox) findViewById(R.id.checkBoxSalary);
        checkboxPosition = (CheckBox) findViewById(R.id.checkBoxPosition);
        checkboxReport = (CheckBox) findViewById(R.id.checkBoxReport);
        checboxSearch = (CheckBox) findViewById(R.id.checkBoxSearch);
        /*--------------------------------------*/
        editTextSearch = (EditText) findViewById(R.id.editTextSearch);
        /*--------------------------------------*/
        checkboxName.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Name=true;
                }else{
                    Name=false;
                }
            }
        });
        checkboxAge.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Age=true;
                }else{
                    Age=false;
                }
            }
        });
        checkboxSalary.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Salary=true;
                }else{
                    Salary=false;
                }
            }
        });
        checkboxPosition.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Position=true;
                }else{
                    Position=false;
                }
            }
        });
        checkboxReport.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Report=true;
                }else{
                    Report=false;
                }
            }
        });
        checboxSearch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Search=true;
                }else{
                    Search=false;
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        if (view == findViewById(R.id.buttonOk)){
            //how to transfer booleans and string to the next activity?????
            //Order by method needs the booleans and the string in order to be able sort it
            Intent intent = new Intent(getBaseContext(), SearchAndReport.class);
            if (Name){
                intent.putExtra("Name",Name);
            }
            if (Age){
                intent.putExtra("Age",Age);
            }
            if (Salary){
                intent.putExtra("Salary",Salary);
            }
            if (Position){
                intent.putExtra("Position",Position);
            }
            if (Report){
                intent.putExtra("Report",Report);
            }
            if (Search){
                intent.putExtra("Search",Search);
                intent.putExtra("Search Criteria", editTextSearch.getText().toString());
            }
            startActivity(intent);
            
        }
        if (view == findViewById(R.id.buttonCancel)){
            finish();
        }
    }
}

