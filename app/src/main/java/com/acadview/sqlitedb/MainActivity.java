package com.acadview.sqlitedb;

import android.app.AlertDialog;
import android.app.Dialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DataBaseHelper db;
    EditText editName,editSurname,editMArk,editId;
    Button btnAddData , btnAllData,btnUpdate,btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DataBaseHelper(this);

        editName = findViewById(R.id.name);
        editSurname = findViewById(R.id.surname);
        editMArk = findViewById(R.id.marks);
        btnAddData = findViewById(R.id.addData);
        btnAllData = findViewById(R.id.allData);
        btnUpdate = findViewById(R.id.update);
        editId = findViewById(R.id.id);
        btnDelete = findViewById(R.id.delete);

        AddData();
        AllData();
        updatedData();
        deletedData();
    }



            public void AddData(){
                btnAddData.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       boolean inserted =  db.insertData(editName.getText().toString(),
                                editSurname.getText().toString(),
                                editMArk.getText().toString());

                       if(inserted = true)
                           Toast.makeText(MainActivity.this,"Data inserted",Toast.LENGTH_SHORT).show();
                       else
                           Toast.makeText(MainActivity.this,"Data Not inserted",Toast.LENGTH_SHORT).show();
                    }
                });
            }


            public void AllData(){
                btnAllData.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = db.getAllData();
                        if (res.getCount() == 0) {
                            //                    show error message
                            showMessage("Error", "Nothing found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("ID: " + res.getString(0) + "\n");
                            buffer.append("NAME: " + res.getString(1) + "\n");
                            buffer.append("SURNAME: " + res.getString(2) + "\n");
                            buffer.append("MARKS: " + res.getString(3) + "\n");
                        }
                        showMessage("Data", buffer.toString());
                    }
                });
            }


            public void updatedData(){
                    btnUpdate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                           boolean isUpdated =  db.updateData(editId.getText().toString(),editName.getText().toString(),editSurname.getText().toString(),editMArk.getText().toString());
                            if(isUpdated == true)
                                Toast.makeText(MainActivity.this,"Data updated",Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(MainActivity.this,"Data Not updated",Toast.LENGTH_SHORT).show();
                        }

                    });
            }

            public void deletedData(){

                    btnDelete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                           Integer deleteRow =  db.DeleteData(editId.getText().toString());
                           if(deleteRow > 0)
                               Toast.makeText(MainActivity.this,"Data Deleted",Toast.LENGTH_SHORT).show();
                           else
                               Toast.makeText(MainActivity.this,"Data Not Deleted",Toast.LENGTH_SHORT).show();

                        }
                    });
            }



            public void showMessage(String title,String message){
                AlertDialog.Builder builder  = new AlertDialog.Builder(this);
                builder.setCancelable(true);
                builder.setTitle(title);
                builder.setMessage(message);
                builder.show();
            }


}
