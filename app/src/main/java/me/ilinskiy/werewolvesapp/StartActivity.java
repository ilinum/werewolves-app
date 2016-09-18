package me.ilinskiy.werewolvesapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class StartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        ListView roleList = (ListView) findViewById(R.id.roleList);
        roleList.setAdapter(new RoleItemAdapter(this, RoleLoader.loadRoles(getAssets())));
    }
}
