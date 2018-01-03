package com.kbs.practical12;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DBHelper dbHelper;
    ListView lvusers;
    UserAdapter mAdapter;
    ArrayList<User> aluser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvusers=(ListView)findViewById(R.id.lvusers);

        dbHelper=new DBHelper(MainActivity.this);
        aluser=dbHelper.getAllUsers();

        mAdapter=new UserAdapter(MainActivity.this,aluser);
        //ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,aluser);
        //lvusers.setAdapter(adapter);
        lvusers.setAdapter(mAdapter);
        lvusers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),i+"",Toast.LENGTH_SHORT).show();
                int userid=aluser.get(i).getId();
                Bundle bundle=new Bundle();
                bundle.putInt("id",userid);
                Intent intent=new Intent(MainActivity.this,AddUserActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
        lvusers.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                //delete the user

                dbHelper.deleteUser(mAdapter.mUserList.get(i).getId()); //remove from database
                mAdapter.mUserList.remove(mAdapter.mUserList.get(i)); //remove from arraylist
                mAdapter.notifyDataSetChanged(); //tell the adapter that your list is updated
                Toast.makeText(MainActivity.this, "User Deleted", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
    public void btnadduser_click(View v){
        //start activity adduser
        Intent myintent=new Intent(MainActivity.this,AddUserActivity.class);
        startActivity(myintent);

    }
}
