package com.example.tk_employee.recyclerviewloadimages;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int NO_OF_COLUMNS =5 ;
    private RecyclerView rv;
    MyAdapter adapter =null;
    ArrayList<NameDO> list =new ArrayList<NameDO>();

    //    GridView gv;
    //    MyGridViewAdapter myAdapter =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv =(RecyclerView)findViewById(R.id.rv);
//        gv =(GridView) findViewById(R.id.gv);
        list = getList();
        adapter = new MyAdapter(list);
        rv.setLayoutManager(new GridLayoutManager(this, NO_OF_COLUMNS));
        rv.setAdapter(adapter);
//        rv.setVisibility(View.GONE);
//        myAdapter = new MyGridViewAdapter(list);
//        gv.setAdapter(myAdapter);
//        gv.setVisibility(View.GONE);

    }

    private ArrayList<NameDO> getList()
    {
        NameDO obj =null;
        for(int i = 0 ;i<300;i++)
        {
            obj = new NameDO();
            obj.description =" this is item "+(i+1);
            obj.url = "http://uat.winitsoftware.com/ThemeManager/Data/Products/Images/Product"+(i+1)+"_big.jpeg";
            list.add(obj);

        }
        return list;
    }

    class MyAdapter  extends RecyclerView.Adapter<MyViewHolder>
    {
        ArrayList<NameDO> list = null;
        public MyAdapter(ArrayList<NameDO> list )
        {
            this.list =list;

        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.itemlayout, null, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

            NameDO obj = list.get(position);
            holder.tvDesc.setText(obj.description);
            Glide.with(MainActivity.this)
                    .load(obj.url)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(holder.iv);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        public    ImageView iv;
        public   TextView tvDesc;

        public MyViewHolder(View view) {
            super(view);
            tvDesc = (TextView) view.findViewById(R.id.tvDesc);
            iv = (ImageView) view.findViewById(R.id.iv);
         }
    }
   /* class MyGridViewAdapter extends BaseAdapter
    {
        ArrayList<NameDO> list = null;
        public MyGridViewAdapter(ArrayList<NameDO> list )
        {
            this.list =list;

        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {

            NameDO obj = list.get(position);
            MyViewHolder viewHolder;
            if(convertView==null)
            {
                convertView = getLayoutInflater().inflate(R.layout.itemlayout,null);
                viewHolder = new MyViewHolder(convertView);
                convertView.setTag(viewHolder);


            }
            else
            viewHolder = (MyViewHolder) convertView.getTag();
            viewHolder.tvDesc.setText(obj.description);
            Glide.with(MainActivity.this)
                    .load(obj.url)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(viewHolder.iv);



            return convertView;
        }
        public class MyViewHolder
        {
            public    ImageView iv;
            public   TextView tvDesc;

            public MyViewHolder(View view) {

                tvDesc = (TextView) view.findViewById(R.id.tvDesc);
                iv = (ImageView) view.findViewById(R.id.iv);
            }

        }
    }*/
}
