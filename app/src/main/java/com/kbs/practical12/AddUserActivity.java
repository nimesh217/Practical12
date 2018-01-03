package com.kbs.practical12;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class AddUserActivity extends AppCompatActivity {

    EditText etusername,etage;
    int editId;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        etusername=(EditText)findViewById(R.id.etuname);
        etage=(EditText)findViewById(R.id.etage);
        dbHelper=new DBHelper(this);
        ///dbHelper.insertUser("nimeshj",12);
        Bundle extras=getIntent().getExtras();
        if(extras!=null)
        {
            int value=extras.getInt("id");

            if(value>0)
            {
                User currentUser=new User();
                currentUser=dbHelper.getUser(value);
                editId=currentUser.getId();
                etusername.setText(currentUser.getName());
                etage.setText(currentUser.getAge()+"");
            }
        }
    }

    public void btnadduser_click(View v)
    {
        if(dbHelper.insertUser(etusername.getText().toString(),Integer.parseInt(etage.getText().toString())))
        {
            Toast.makeText(getApplicationContext(),"User Inserted",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Error in Insert Done",Toast.LENGTH_LONG).show();
        }
        Intent intent=new Intent(AddUserActivity.this,MainActivity.class);
        startActivity(intent);
    }
    public void btnupdateuser_click(View v)
    {
        if(dbHelper.updateUser(editId,etusername.getText().toString(),Integer.parseInt(etage.getText().toString())))
        {
            Toast.makeText(getApplicationContext(),"User Updated",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Error in Update",Toast.LENGTH_LONG).show();
        }
        Intent intent=new Intent(AddUserActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
