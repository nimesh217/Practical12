package com.kbs.practical12;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Nimesh on 18-01-2017.
 */

public class UserAdapter extends BaseAdapter {


    Context context;
    ArrayList<User> mUserList;


    public UserAdapter(Context context,ArrayList<User> userlist)
    {
        this.context=context;
        this.mUserList=userlist;
    }

    @Override
    public int getCount() {
        return mUserList.size();
    }

    @Override
    public Object getItem(int position) {
        return mUserList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Holder holder;
        LayoutInflater inflater;
        if(view==null)
        {
            inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.user_list_item,null);
            holder=new Holder();
            holder.txtuserid=(TextView)view.findViewById(R.id.txtuserid);
            holder.txtusername=(TextView)view.findViewById(R.id.txtusername);
            holder.txtuserage=(TextView)view.findViewById(R.id.txtuserage);
            view.setTag(holder);
        }
        else
        {
            holder=(Holder) view.getTag();
        }
        holder.txtuserid.setText(mUserList.get(i).id+"");
        holder.txtusername.setText(mUserList.get(i).name);
        holder.txtuserage.setText(mUserList.get(i).age+"");
        return view;
    }

    public class Holder{
        TextView txtuserid;
        TextView txtusername;
        TextView txtuserage;
    }
}
