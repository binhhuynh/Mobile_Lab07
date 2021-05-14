package com.iuh.mobile_lab07;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.iuh.mobile_lab07.dao.LocationDao;
import com.iuh.mobile_lab07.db.TravelDatabase;
import com.iuh.mobile_lab07.entity.Location;

import java.util.List;

public class LocationActivity extends AppCompatActivity {
    private LocationDao locationDao;
    private TravelDatabase db;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        db = Room.databaseBuilder(this, TravelDatabase.class, "travelDB")
                .allowMainThreadQueries().build();

        locationDao = db.locationDao();

        recyclerView = findViewById(R.id.recycler);

        getDBConnection();
    }

    public void getDBConnection() {
        List<Location> locations = locationDao.getAll();

        CustomAdapter adapter = new CustomAdapter(locations, locationDao);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void addLocation(View view) {
        Location location = new Location();
        EditText name = (EditText) findViewById(R.id.etName);
        location.setName(String.valueOf(name.getText()));
        locationDao.insert(location);
        getDBConnection();
    }

    public void cancelLocation(View view) {
        EditText name = (EditText) findViewById(R.id.etName);
        name.getText().clear();
    }
}