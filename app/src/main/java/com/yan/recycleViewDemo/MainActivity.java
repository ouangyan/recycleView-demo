package com.yan.recycleViewDemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;

import com.yan.recycleViewDemo.adapter.CustomAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerViewCustom;
    private CustomAdapter adapterCustom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        initData();
        event();
    }

    private void init(){
        recyclerViewCustom = findViewById(R.id.recycleViewCustom);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        adapterCustom = new CustomAdapter(this);
        recyclerViewCustom.setAdapter(adapterCustom);
        recyclerViewCustom.setLayoutManager(manager);
    }

    private void initData(){
        List<String> listTemp = new ArrayList();
        for(int i=0;i<100;i++){
            String content = "世界很美好，人间很丑陋"+i;
            listTemp.add(content);
        }
        adapterCustom.setData(listTemp);
    }

    private void event(){
        adapterCustom.setItemOnClickListener(new CustomAdapter.ItemOnClickListener() {
            @Override
            public void onClick(int position) {
                adapterCustom.addRow(position+1);
            }
        });

        adapterCustom.setItemOnLongCLickListener(new CustomAdapter.ItemOnLongClickListener() {
            @Override
            public void onLongClick(int position) {
                adapterCustom.removeRow(position);
            }
        });
    }
}