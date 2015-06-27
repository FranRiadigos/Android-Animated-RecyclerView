package com.example.kikogonzalez.material_linearrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static List<ViewModel> items = new ArrayList<>();

    static {
        for (int i = 1; i <= 300; i++) {
            items.add(new ViewModel(i));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewAnimatedAdapter adapter = new RecyclerViewAnimatedAdapter(items);
        recyclerView.setAdapter(adapter);
    }

}
