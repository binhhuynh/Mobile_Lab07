package com.iuh.mobile_lab07;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.iuh.mobile_lab07.dao.UserDao;
import com.iuh.mobile_lab07.db.AppDatabase;
import com.iuh.mobile_lab07.entity.User;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private UserDao userDao;
    private AppDatabase db;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Room.databaseBuilder(this, AppDatabase.class, "userDB")
                .allowMainThreadQueries().build();

        userDao = db.userDao();

        listView = findViewById(R.id.listView);
        getDBConnection();
    }

    public void getDBConnection() {
        List<User> users = userDao.getAll();

        ArrayAdapter<User> adapter = new ArrayAdapter<User>(this, android.R.layout.simple_list_item_1, users);

        listView.setAdapter(adapter);
    }

    public void addUser(View view) {
        User user = new User();
        EditText name = findViewById(R.id.etName);
        user.setName(String.valueOf(name.getText()));
        user.setId(new Random().nextInt(1000));
        userDao.insert(user);
        getDBConnection();
    }

    public void deleteUser(View view) {
        EditText name = findViewById(R.id.etName);
        userDao.delete(String.valueOf(name.getText()));
        getDBConnection();
    }

    public void cancelUser(View view) {
        EditText name = (EditText) findViewById(R.id.etName);
        name.getText().clear();
    }
}