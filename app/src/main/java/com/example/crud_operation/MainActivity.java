package com.example.crud_operation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txt_id, txt_name, txt_Lastname, txt_marks;
    Button btn_insert, view_data, update_data, delete_data;
    DatabaseHelper DB;
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_id = findViewById(R.id.edit_id);
        txt_name = findViewById(R.id.edit_name);
        txt_Lastname = findViewById(R.id.last_name);
        txt_marks = findViewById(R.id.marks);
        view_data = findViewById(R.id.btn_view);
        update_data = findViewById(R.id.btn_update);
        delete_data = findViewById(R.id.btn_delete);


        btn_insert = findViewById(R.id.btn_insert);
        DB = new DatabaseHelper(this);

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                boolean insertData = (boolean) DB.insertData(txt_name.getText().toString(), txt_Lastname.getText().toString(),
                        txt_marks.getText().toString());
                if (insertData == true)
                {
                    Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });
        view_data.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Cursor cur=DB.getAllData();
                if (cur.getCount()==0){
                    Toast.makeText(MainActivity.this,"Empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while (cur.moveToNext()){
                    buffer.append("ID"+cur.getString(0)+"\n");
                    buffer.append("Name"+cur.getString(1)+"\n");
                    buffer.append("LastName"+cur.getString(2)+"\n");
                    buffer.append("Marks"+cur.getString(3)+"\n");

                }
                show("Data",buffer.toString());
            }
        });

        update_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                boolean update = DB.Updatedata(txt_id.getText().toString().trim(), txt_name.getText().toString().trim(),
                        txt_Lastname.getText().toString().trim(), txt_marks.getText().toString().trim());

                if (true == update){
                    Toast.makeText(MainActivity.this, "Data Updated", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Data not Updated", Toast.LENGTH_SHORT).show();
                }

            }
        });

        delete_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer delete = DB.deleteData(txt_id.getText().toString().trim());
                if(delete > 0){
                    Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                }
                else {Toast.makeText(MainActivity.this, "Data Not Deleted", Toast.LENGTH_SHORT).show();}
            }
        });
    }

    private void show(String data, String toString) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(data);
        builder.setMessage(toString);
        builder.show();
    }
}