package com.iuh.mobile_lab07;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.iuh.mobile_lab07.dao.LocationDao;
import com.iuh.mobile_lab07.db.TravelDatabase;
import com.iuh.mobile_lab07.entity.Location;

import java.util.ArrayList;
import java.util.List;

public class LocationActivity extends AppCompatActivity {
    private EditText editText;
    private Button btnAdd, btnCancel;
    private RecyclerView recyclerView;

    private List<Location> locationList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    TravelDatabase database;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        editText = findViewById(R.id.edit_text);
        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);
        recyclerView = findViewById(R.id.recycler);

        database = TravelDatabase.getInstance(this);

        locationList = database.locationDao().getAll();

        linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new CustomAdapter(LocationActivity.this, locationList);

        recyclerView.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString().trim();
                if (!name.equals("")) {
                    Location location = new Location();
                    location.setName(name);
                    database.locationDao().insert(location);

                    editText.setText("");

                    locationList.clear();
                    locationList.addAll(database.locationDao().getAll());
                    adapter.notifyDataSetChanged();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.locationDao().reset(locationList);

                locationList.clear();
                locationList.addAll(database.locationDao().getAll());
                adapter.notifyDataSetChanged();
            }
        });
    }
}