package com.example.db_class2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SQLiteHelper myDB;
    EditText etName, etMarks, etEntryId;
    Button btnAddMarks, btnViewMarks, btnUpdate, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB = new SQLiteHelper(this);
        etName = (EditText)findViewById(R.id.etName);
        etMarks = (EditText)findViewById(R.id.etMarks);
        etEntryId = (EditText)findViewById(R.id.etEntryId);
        btnAddMarks = (Button) findViewById(R.id.btnAddMarks);
        btnViewMarks = (Button) findViewById(R.id.btnViewMarks);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        addMarks();
        viewMarks();
        deleteMarks();
        updateMarks();
    }
    public void addMarks(){
        btnAddMarks.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isInserted = myDB.insertData(etName.getText().toString(), etMarks.getText().toString());
                        if(isInserted == true)
                            Toast.makeText(MainActivity.this, "Marks Inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Marks Not Inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public void viewMarks(){
        btnViewMarks.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor res = myDB.viewAllData();
                        if(res.getCount() == 0){
                            showMessage("Error","No data in db");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()){
                            buffer.append("ID :" + res.getString(0) + "\n");
                            buffer.append("Name :" + res.getString(1) + "\n");
                            buffer.append("Marks :" + res.getString(2) + "\n");
                        }
                        showMessage("Data", buffer.toString());
                    }
                }
        );
    }
    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
    public void deleteMarks(){
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Integer deleteRows = myDB.deleteMarks(etEntryId.getText().toString());
                        if(deleteRows > 0)
                            Toast.makeText(MainActivity.this, "Delete", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "NO Data Available", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public void updateMarks(){
        btnUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isUpdate = myDB.updateMarks(etEntryId.getText().toString(), etName.getText().toString(), etMarks.getText().toString());
                        if(isUpdate == true)
                            Toast.makeText(MainActivity.this, "Record Updated", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Record Not Updated", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}