package com.bd.ehaquesoft.custombarchart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    ArrayList<Data> listData = new ArrayList<>();
    BarChartAdapter barChartAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv_bar_chart);

        listData.add(new Data(19.3f));
        listData.add(new Data(21.0f));
        listData.add(new Data(3.6f));

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        barChartAdapter = new BarChartAdapter(listData);
        recyclerView.setAdapter(barChartAdapter);
    }

    public class BarChartAdapter extends RecyclerView.Adapter<BarChartAdapter.MyViewHolder> {

        ArrayList<Data> listData = new ArrayList<>();

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView textView;
            public MyViewHolder(View v) {
                super(v);
                textView = v.findViewById(R.id.tv_chart_value);
            }
        }

        public BarChartAdapter(ArrayList<Data> listData) {
            this.listData = listData;
        }


        @Override
        public BarChartAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
            View v = (View) LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.barchart_layout, parent, false);
            MyViewHolder vh = new MyViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.textView.setText(listData.get(position).value+"");

            Display display = getWindowManager().getDefaultDisplay();
            int width = display.getWidth();
            width = width - (100/width)*80;

            LinearLayout layout = (LinearLayout) findViewById(R.id.lo_dynamic_view_container);
            LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);

            TextView tv = new TextView(MainActivity.this);
            tv.setLayoutParams(lparams);
            tv.setWidth(width);
            float redious [] = { 0, 0, 8.3f, 8.5f, 8.2f, 8.9f, 0, 0 };
            ShapeDrawable shape = new ShapeDrawable (new RoundRectShape(redious,null,null));
            shape.getPaint().setColor(Color.GREEN);
            tv.setBackground(shape);
            layout.addView(tv);

//            TextView tv2 = new TextView(MainActivity.this);
//            tv2.setLayoutParams(lparams);
//            tv2.setWidth(width-300);
//            float redious2 [] = { 0, 0, 8.3f, 8.5f, 8.2f, 8.9f, 0, 0 };
//            ShapeDrawable shape2 = new ShapeDrawable (new RoundRectShape(redious2,null,null));
//            shape2.getPaint().setColor(Color.GREEN);
//            tv2.setBackground(shape2);
//            layout.addView(tv2);

        }

        @Override
        public int getItemCount() {
            return listData.size();
        }
    }


    class Data{
        float value;

        public Data(float value) {
            this.value = value;
        }
    }
}
