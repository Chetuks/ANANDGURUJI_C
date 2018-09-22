package com.ananada.addme.neutrinos.anandaguruji;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mahiti on 20/03/18.
 */

class MyBaseAdapter extends BaseAdapter {
    ArrayList myList = new ArrayList();
    LayoutInflater inflater;
    Context context;
    List<NearMe> myNearMeList;


    public MyBaseAdapter(Context context, List<NearMe> myNearMeList) {
        this.myList = myList;
        this.context = context;
        inflater = LayoutInflater.from(this.context);
        this.myNearMeList=myNearMeList;
    }

    @Override
    public int getCount() {
        return myNearMeList.size();
    }

    @Override
    public NearMe getItem(int position) {
        return myNearMeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        MyViewHolder mViewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.near_me_adapter, parent, false);
            mViewHolder = new MyViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

    //    ListData currentListData = getItem(position);

        mViewHolder.name.setText(myNearMeList.get(position).getName());

        mViewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, myNearMeList.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });


        return convertView;
    }

    private class MyViewHolder {
        TextView name;
        LinearLayout parentLayout;
      //  ImageView ivIcon;

        public MyViewHolder(View item) {
            name = (TextView) item.findViewById(R.id.name);
            parentLayout = (LinearLayout) item.findViewById(R.id.parent);
         //   tvDesc = (TextView) item.findViewById(R.id.tvDesc);
          //  ivIcon = (ImageView) item.findViewById(R.id.ivIcon);
        }
    }
}
